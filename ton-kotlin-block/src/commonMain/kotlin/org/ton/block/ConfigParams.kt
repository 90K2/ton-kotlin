package org.ton.block

import kotlinx.serialization.Serializable
import org.ton.bitstring.BitString
import org.ton.cell.*
import org.ton.hashmap.HashMapEdge
import org.ton.tlb.TlbConstructor
import org.ton.tlb.constructor.tlbCodec
import org.ton.tlb.loadTlb
import org.ton.tlb.providers.TlbConstructorProvider
import org.ton.tlb.storeTlb

@Serializable
data class ConfigParams(
    val config_addr: BitString,
    val config: HashMapEdge<Cell>
) {
    init {
        require(config_addr.size == 256) { "required: config_addr.size == 256, actual: ${config_addr.size}" }
    }

    companion object : TlbConstructorProvider<ConfigParams> by ConfigParamsTlbConstructor
}

private object ConfigParamsTlbConstructor : TlbConstructor<ConfigParams>(
    schema = "_ config_addr:bits256 config:^(Hashmap 32 ^Cell) = ConfigParams;"
) {
    val hashmap = HashMapEdge.tlbCodec(32, Cell.tlbCodec())

    override fun storeTlb(
        cellBuilder: CellBuilder,
        value: ConfigParams
    ) = cellBuilder {
        storeBits(value.config_addr)
        storeRef { storeTlb(hashmap, value.config) }
    }

    override fun loadTlb(
        cellSlice: CellSlice
    ): ConfigParams = cellSlice {
        val configAddr = loadBits(256)
        val config = loadRef { loadTlb(hashmap) }
        ConfigParams(configAddr, config)
    }
}
