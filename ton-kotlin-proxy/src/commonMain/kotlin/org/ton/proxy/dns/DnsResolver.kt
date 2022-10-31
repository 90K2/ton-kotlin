package org.ton.proxy.dns

import io.github.reactivecircus.cache4k.Cache
import kotlinx.coroutines.*
import org.ton.api.tonnode.TonNodeBlockIdExt
import org.ton.bitstring.BitString
import org.ton.block.AddrStd
import org.ton.block.DnsNextResolver
import org.ton.block.DnsRecord
import org.ton.block.VmStackValue
import org.ton.cell.Cell
import org.ton.cell.CellBuilder
import org.ton.crypto.hex
import org.ton.hashmap.HashMapEdge
import org.ton.lite.api.liteserver.LiteServerAccountId
import org.ton.lite.client.LiteClient
import org.ton.tlb.constructor.tlbCodec
import org.ton.tlb.parse
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class DnsResolver constructor(
    val liteClient: LiteClient,
    val resolverAddress: LiteServerAccountId,
    cacheTtl: Duration = 5.minutes
) : CoroutineScope {
    override val coroutineContext: CoroutineContext =
        Dispatchers.Default + CoroutineName("DnsResolver: $resolverAddress")

    constructor(liteClient: LiteClient) : this(liteClient, MAINNET_ADDRESS, 24.hours)

    private val dnsCache = Cache.Builder()
        .expireAfterWrite(cacheTtl)
        .build<String, Deferred<Map<BitString, DnsRecord>?>>()
    private val dnsResolversCache = Cache.Builder()
        .expireAfterWrite(cacheTtl)
        .build<LiteServerAccountId, DnsResolver>()
    private val blockIdCache = Cache.Builder()
        .expireAfterWrite(5.seconds)
        .build<Unit, TonNodeBlockIdExt>()

    suspend fun resolveAll(host: String, block: TonNodeBlockIdExt? = null): Map<DnsCategory, DnsRecord> =
        DnsCategory.values().map { category ->
            coroutineScope {
                async {
                    resolve(
                        host,
                        category,
                        block
                    )?.let { category to it }
                }
            }
        }.awaitAll().asSequence().filterNotNull().toMap()

    suspend fun resolve(host: String, category: DnsCategory, block: TonNodeBlockIdExt? = null): DnsRecord? {
        val encodedHost = encodeHostname(host)
        val currentBlock = block ?: blockIdCache.get(Unit) {
            println("get last block...")
            liteClient.getLastBlockId()
        }
        return resolveCache(encodedHost, category, currentBlock)
    }

    private suspend fun resolveCache(encodedHost: String, category: DnsCategory, block: TonNodeBlockIdExt): DnsRecord? {
        val domains = encodedHost.splitToSequence(DNS_NAME_DELIMITER).map { it + DNS_NAME_DELIMITER }.toMutableList()
        val first = domains.removeFirst()
        val records = dnsCache.get(first) {
            coroutineScope {
                async(coroutineContext) {
                    resolveOrNull(first, block)
                }
            }
        }.await()
        if (records == null) return null
        else {
            val nextResolverAddress = (records[DNS_NEXT_RESOLVER_ID] as? DnsNextResolver)?.let {
                it.resolver as? AddrStd
            }?.let {
                LiteServerAccountId(it)
            }
            return if (nextResolverAddress != null) {
                val dnsResolver = dnsResolversCache.get(nextResolverAddress) {
                    DnsResolver(liteClient, nextResolverAddress)
                }
                dnsResolver.resolveCache(domains.joinToString(""), category, block)
            } else {
                records[BitString(category.value.toByteArray())]
            }
        }
    }

    private suspend fun resolveOrNull(encodedHost: String, block: TonNodeBlockIdExt): Map<BitString, DnsRecord>? {
        println("EXECUTE $encodedHost - $resolverAddress")
        val vmStack = liteClient.runSmcMethod(
            resolverAddress,
            block,
            "dnsresolve",
            VmStackValue(CellBuilder.createCell {
                storeBytes(encodedHost.toByteArray())
            }.beginParse()),
            VmStackValue(0)
        ).toMutableVmStack()
        vmStack.popTinyInt()
        val result = vmStack.popCell()

        return try {
            val dnsRecord = result.parse(DnsRecord) as DnsNextResolver
            mapOf(DNS_NEXT_RESOLVER_ID to dnsRecord)
        } catch (e: Exception) {
            result.parse(DNS_RECORDS_CODEC).nodes().toMap()
        }
    }

    private fun split(encodedHost: String) = encodedHost.split(DNS_NAME_DELIMITER)

    companion object {
        private const val MAX_DEFAULT_MAX_NAME_SIZE = 128
        private const val DNS_NAME_DELIMITER = 0.toChar().toString()
        private val DNS_NEXT_RESOLVER_ID = DnsNextResolver.tlbConstructor().id
        private val MAINNET_ADDRESS =
            LiteServerAccountId(-1, hex("8E25DD08174C9CD3192181A022AEAD7659AC2A9D4A12B84F5160EDD2ECC706EA"))
        private val DNS_RECORDS_CODEC = HashMapEdge.tlbCodec(256, Cell.tlbCodec(DnsRecord))

        @JvmStatic
        fun encodeHostname(host: String?): String {
            if (host.isNullOrEmpty() || host == ".") {
                return DNS_NAME_DELIMITER
            }
            val delimiter = 0.toChar().toString()
            return host.lowercase().split('.').asReversed().joinToString(DNS_NAME_DELIMITER, postfix = delimiter)
        }

        @JvmStatic
        fun decodeHostname(host: String): String {
            return host.split(DNS_NAME_DELIMITER).asReversed().joinToString(".")
        }
    }
}
