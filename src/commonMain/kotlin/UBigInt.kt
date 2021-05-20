import kotlin.jvm.JvmInline

@ExperimentalUnsignedTypes
private val emptyUIntArray = UIntArray(0)

@ExperimentalUnsignedTypes
@JvmInline
value class UBigInt(private val magnitude: UIntArray) : BigInt {
    init {
        require(magnitude.lastOrNull() != 0U) { "Magnitude must be without leading zeroes" }
    }

    constructor() : this(emptyUIntArray)

    override operator fun plus(other: BigInt): BigInt = when (other) {
        is NBigInt -> this - -other
        is UBigInt -> plus(other)
    }
    operator fun plus(other: UBigInt): UBigInt {
        TODO("Not yet implemented")
    }

    operator fun minus(other: NBigInt): UBigInt = this + -other
    override operator fun minus(other: BigInt): BigInt {
        return when (other) {
            is NBigInt -> minus(other)
            is UBigInt -> {
                val cmp = this.compareTo(other)
                if (cmp == 0) return zero
                val big = if (cmp > 0) this else other
                val small = if (cmp > 0) other else this
                TODO("Not yet implemented")
            }
        }
    }

    override operator fun times(other: BigInt): BigInt = when (other) {
        is NBigInt -> -times(-other)
        is UBigInt -> times(other)
    }
    operator fun times(other: UBigInt): UBigInt {
        TODO("Not yet implemented")
    }

    operator fun div(other: UBigInt): UBigInt {
        // can be 0 if this is less than other
        TODO("Not yet implemented")
    }
    override operator fun div(other: BigInt): BigInt = when (other) {
        is NBigInt -> -div(-other)
        is UBigInt -> div(other)
    }

    override fun unaryPlus(): UBigInt = this

    override fun unaryMinus(): BigInt = if (this == zero) zero else NBigInt(this)

    override val absoluteValue: UBigInt
        get() = this
    override val sign: BigInt.Sign
        get() = if (this == zero) BigInt.Sign.ZERO else BigInt.Sign.PLUS

    override fun compareTo(other: BigInt): Int = when (other) {
        is NBigInt -> 1
        is UBigInt -> TODO("Not yet implemented")
    }

    override fun toString(): String {
        TODO("Not yet implemented")
    }
}

@ExperimentalUnsignedTypes
val zero = UBigInt()