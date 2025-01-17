package org.ton.block

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.ton.cell.*
import org.ton.tlb.TlbConstructor
import org.ton.tlb.loadTlb
import org.ton.tlb.providers.TlbConstructorProvider
import org.ton.tlb.storeTlb

@Serializable
@SerialName("msg_export_deq_imm")
data class MsgExportDeqImm(
    val out_msg: MsgEnvelope,
    val reimport: InMsg,
) : OutMsg {
    companion object : TlbConstructorProvider<MsgExportDeqImm> by MsgExportDeqImmTlbConstructor
}

private object MsgExportDeqImmTlbConstructor : TlbConstructor<MsgExportDeqImm>(
    schema = "msg_export_deq_imm\$100 out_msg:^MsgEnvelope reimport:^InMsg = OutMsg;"
) {
    override fun storeTlb(
        cellBuilder: CellBuilder,
        value: MsgExportDeqImm
    ) = cellBuilder {
        storeRef { storeTlb(MsgEnvelope, value.out_msg) }
        storeRef { storeTlb(InMsg, value.reimport) }
    }

    override fun loadTlb(
        cellSlice: CellSlice
    ): MsgExportDeqImm = cellSlice {
        val outMsg = loadRef { loadTlb(MsgEnvelope) }
        val reimport = loadRef { loadTlb(InMsg) }
        MsgExportDeqImm(outMsg, reimport)
    }
}
