@file:UseSerializers(HexByteArraySerializer::class)
@file:Suppress("PropertyName", "NOTHING_TO_INLINE")

package org.ton.lite.api.liteserver

import io.ktor.util.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.UseSerializers
import org.ton.api.tonnode.TonNodeBlockIdExt
import org.ton.api.tonnode.TonNodeZeroStateIdExt
import org.ton.crypto.HexByteArraySerializer
import org.ton.tl.*

public data class LiteServerMasterchainInfo(
    public val last: TonNodeBlockIdExt,
    public val stateRootHash: Bits256,
    public val init: TonNodeZeroStateIdExt
) {

    public companion object : TlCodec<LiteServerMasterchainInfo> by LiteServerMasterchainInfoTlbConstructor
}

private object LiteServerMasterchainInfoTlbConstructor : TlConstructor<LiteServerMasterchainInfo>(
    schema = "liteServer.masterchainInfo last:tonNode.blockIdExt state_root_hash:int256 init:tonNode.zeroStateIdExt = liteServer.MasterchainInfo"
) {
    override fun decode(reader: TlReader): LiteServerMasterchainInfo {
        val last = reader.read(TonNodeBlockIdExt)
        val stateRootHash = reader.readBits256()
        val init = reader.read(TonNodeZeroStateIdExt)
        return LiteServerMasterchainInfo(last, stateRootHash, init)
    }

    override fun encode(writer: TlWriter, value: LiteServerMasterchainInfo) {
        writer.write(TonNodeBlockIdExt, value.last)
        writer.writeBits256(value.stateRootHash)
        writer.write(TonNodeZeroStateIdExt, value.init)
    }
}
