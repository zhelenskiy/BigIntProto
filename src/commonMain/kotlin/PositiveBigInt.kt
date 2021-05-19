import kotlin.jvm.JvmInline

@ExperimentalUnsignedTypes
@JvmInline
value class PositiveBigInt constructor(private val magnitude: UIntArray) : BigInt {
    init {
        require(magnitude.isNotEmpty()) { "Positive numbers cannot have empty magnitude" }
        require(magnitude.last() != 0U) { "Magnitude must be without leading zeroes" }
    }

    override operator fun plus(other: Zero): PositiveBigInt = this
    override operator fun plus(other: NegativeBigInt): BigInt = this - (-other)
    override operator fun plus(other: PositiveBigInt): PositiveBigInt {
        TODO("Not yet implemented")
    }

    override operator fun minus(other: NegativeBigInt): PositiveBigInt = this + (-other)
    override operator fun minus(other: Zero): PositiveBigInt = this
    override operator fun minus(other: PositiveBigInt): BigInt {
        val cmp = this.compareTo(other)
        if (cmp == 0) return Zero
        val big = if (cmp > 0) this else other
        val small = if (cmp > 0) other else this
        TODO("Not yet implemented")
    }

    override operator fun times(other: NegativeBigInt): NegativeBigInt = -(this * -other)
    override operator fun times(other: PositiveBigInt): PositiveBigInt {
        TODO("Not yet implemented")
    }

    operator fun div(other: NegativeBigInt): BigInt /* todo NBigInt */ = -(this / -other).toBigInt()
    operator fun div(other: PositiveBigInt): UBigInt {
        // can be 0 if this is less than other
        TODO("Not yet implemented")
    }

    override fun unaryPlus(): PositiveBigInt = this

    override fun unaryMinus(): NegativeBigInt = NegativeBigInt(this)

    override val absoluteValue: UBigInt
        get() = this.toUBigInt()
    override val sign: BigInt.Sign
        get() = BigInt.Sign.PLUS

    override fun compareTo(other: BigInt): Int = when (other) {
        is Zero, is NegativeBigInt -> 1
        is PositiveBigInt -> TODO("Not yet implemented")
    }

    override fun toString(): String {
        TODO("Not yet implemented")
    }
}