package org.ton.contract.wallet.v1

import org.ton.api.pk.PrivateKeyEd25519
import org.ton.cell.Cell
import org.ton.contract.wallet.SeqnoContract
import org.ton.lite.api.LiteApi

/**
 * Wallet v1 revision 2
 *
 * In this revision additional `seqno` get-method was introduced:
 *
 * [Fift-ASM source-code](https://github.com/ton-blockchain/ton/blob/47814dca3d4d7d253f0dcbb2ef176f45aafc6871/crypto/smartcont/new-wallet.fif)
 */
class ContractV1R2(
    liteApi: LiteApi,
    privateKey: PrivateKeyEd25519,
    workchainId: Int = 0
) : AbstractWalletV1(liteApi, privateKey, workchainId), SeqnoContract {
    override val name: String = "v1r2"
    override val code: Cell = CODE

    companion object {
        val CODE: Cell =
            Cell("FF0020DD2082014C97BA9730ED44D0D70B1FE0A4F260810200D71820D70B1FED44D0D31FD3FFD15112BAF2A122F901541044F910F2A2F80001D31F3120D74A96D307D402FB00DED1A4C8CB1FCBFFC9ED54")
    }
}
