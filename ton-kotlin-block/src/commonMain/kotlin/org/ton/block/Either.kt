@file:Suppress("OPT_IN_USAGE")

package org.ton.block

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import org.ton.bitstring.BitString
import org.ton.cell.CellBuilder
import org.ton.cell.CellSlice
import org.ton.cell.invoke
import org.ton.tlb.*
import kotlin.jvm.JvmStatic

@Suppress("NOTHING_TO_INLINE")
inline fun <X, Y> Pair<X?, Y?>.toEither(): Either<X, Y> = Either.of(first, second)

@JsonClassDiscriminator("@type")
@Serializable
sealed interface Either<X, Y> {
    val x: X?
    val y: Y?

    fun toPair(): Pair<X?, Y?> = x to y

    @SerialName("left")
    @Serializable
    class Left<X, Y>(
        val value: X
    ) : Either<X, Y> {
        override val x: X? = value
        override val y: Y? = null

        operator fun component1() = x
        operator fun component2() = y

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Left<*, *>) return false
            if (value != other.value) return false
            return true
        }

        override fun hashCode(): Int = value.hashCode()

        override fun toString(): String = "(left\nvalue:$value)"
    }

    @SerialName("right")
    @Serializable
    class Right<X, Y>(
        val value: Y
    ) : Either<X, Y> {
        override val x: X? = null
        override val y: Y? = value

        operator fun component1() = x
        operator fun component2() = y

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Right<*, *>) return false
            if (value != other.value) return false
            return true
        }

        override fun hashCode(): Int = value.hashCode()

        override fun toString(): String = "(right\nvalue:$value)"
    }

    companion object {
        @JvmStatic
        fun <X, Y> of(left: X?, right: Y?): Either<X, Y> {
            if (left != null) {
                return Left(left)
            }
            if (right != null) {
                return Right(right)
            }
            throw IllegalArgumentException("first & second == null; At least one element must be non-null")
        }

        @Suppress("UNCHECKED_CAST")
        @JvmStatic
        fun <X, Y> tlbCodec(x: TlbCodec<X>, y: TlbCodec<Y>): TlbCodec<Either<X, Y>> =
            EitherTlbCombinator(x, y) as TlbCodec<Either<X, Y>>
    }
}

operator fun <X, Y> Either.Companion.invoke(x: TlbCodec<X>, y: TlbCodec<Y>) = tlbCodec(x, y)

private class EitherTlbCombinator<X, Y>(x: TlbCodec<X>, y: TlbCodec<Y>) : TlbCombinator<Either<*, *>>(
    Either::class,
    Either.Left::class to LeftTlbConstructor<X, Y>(x),
    Either.Right::class to RightTlbConstructor<X, Y>(y)
)

private class LeftTlbConstructor<X, Y>(val x: TlbCodec<X>) : TlbConstructor<Either.Left<X, Y>>(
    schema = "left\$0 {X:Type} {Y:Type} value:X = Either X Y;",
    id = ID
) {
    override fun storeTlb(
        cellBuilder: CellBuilder, value: Either.Left<X, Y>
    ) = cellBuilder {
        storeTlb(x, value.value)
    }

    override fun loadTlb(
        cellSlice: CellSlice
    ): Either.Left<X, Y> = cellSlice {
        val value = loadTlb(x)
        Either.Left(value)
    }

    companion object {
        val ID = BitString(false)
    }
}

private class RightTlbConstructor<X, Y>(val y: TlbCodec<Y>) : TlbConstructor<Either.Right<X, Y>>(
    schema = "right\$1 {X:Type} {Y:Type} value:Y = Either X Y;",
    id = ID
) {
    override fun storeTlb(
        cellBuilder: CellBuilder,
        value: Either.Right<X, Y>
    ) = cellBuilder {
        storeTlb(y, value.value)
    }

    override fun loadTlb(
        cellSlice: CellSlice
    ): Either.Right<X, Y> = cellSlice {
        val value = loadTlb(y)
        Either.Right(value)
    }

    companion object {
        val ID = BitString(true)
    }
}
