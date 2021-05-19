import kotlin.jvm.JvmInline

@ExperimentalUnsignedTypes
@JvmInline
value class NegativeBigInt(private val opposite: PositiveBigInt) : BigInt {

    override fun plus(other: PositiveBigInt): BigInt = -(-this + -other)
    override fun plus(other: NegativeBigInt): NegativeBigInt = -(-this + -other)
    override fun plus(other: Zero): NegativeBigInt = this

    override fun minus(other: PositiveBigInt): NegativeBigInt = -(-this - -other)
    override fun minus(other: Zero): NegativeBigInt = this
    override fun minus(other: NegativeBigInt): BigInt = -(-this - -other)

    override fun times(other: PositiveBigInt): NegativeBigInt = - (-this * other)
    override fun times(other: NegativeBigInt): PositiveBigInt = -this * -other

    operator fun div(other: PositiveBigInt): BigInt = - (-this / other).toBigInt()
    operator fun div(other: NegativeBigInt): UBigInt  = (-this) / (-other)

    override fun unaryPlus(): NegativeBigInt = this
    override fun unaryMinus(): PositiveBigInt = opposite

    override val absoluteValue: UBigInt
        get() = opposite.absoluteValue
    override val sign: BigInt.Sign
        get() = BigInt.Sign.MINUS

    override fun compareTo(other: BigInt): Int = when (other) {
        is PositiveBigInt, Zero -> -1
        is NegativeBigInt -> -(-this).compareTo(-other)
    }
}