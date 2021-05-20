import kotlin.jvm.JvmInline

@ExperimentalUnsignedTypes
@JvmInline
value class NBigInt(private val opposite: UBigInt) : BigInt {
    init {
        require(opposite != zero) { "0 must be UBigInt" }
    }

    operator fun plus(other: NBigInt): NBigInt = NBigInt(-this + -other)
    override operator fun plus(other: BigInt): BigInt = when (other) {
        is NBigInt -> plus(other)
        is UBigInt -> other - -this
    }

    operator fun minus(other: UBigInt): NBigInt = if (other == zero) this else this + NBigInt(other)
    override operator fun minus(other: BigInt): BigInt = when (other) {
        is NBigInt -> -(-this + -other)
        is UBigInt -> minus(other)
    }

    operator fun times(other: NBigInt): UBigInt = this.opposite * other.opposite

    override operator fun times(other: BigInt): BigInt = when (other) {
        is NBigInt -> times(other)
        is UBigInt -> -(-this * other)
    }

    operator fun div(other: NBigInt): UBigInt = this.opposite / other.opposite
    override operator fun div(other: BigInt): BigInt = when (other){
        is NBigInt -> div(other)
        is UBigInt -> -(-this / other)
    }

    override operator fun unaryPlus(): NBigInt = this
    override operator fun unaryMinus(): UBigInt = opposite

    override val absoluteValue: UBigInt
        get() = opposite.absoluteValue
    override val sign: BigInt.Sign
        get() = BigInt.Sign.MINUS

    override fun compareTo(other: BigInt): Int = when (other) {
        is UBigInt -> -1
        is NBigInt -> -(-this).compareTo(-other)
    }

    override fun toString(): String = "-${this.opposite}"
}