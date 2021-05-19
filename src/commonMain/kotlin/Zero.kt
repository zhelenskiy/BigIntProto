@ExperimentalUnsignedTypes
object Zero: BigInt {
    override operator fun plus(other: PositiveBigInt): PositiveBigInt = other
    override operator fun plus(other: Zero): Zero = other
    override operator fun plus(other: NegativeBigInt): NegativeBigInt = other

    override operator fun minus(other: PositiveBigInt): NegativeBigInt = -other
    override operator fun minus(other: NegativeBigInt): PositiveBigInt = -other

    override fun times(other: PositiveBigInt): Zero = Zero
    override fun times(other: NegativeBigInt): Zero = Zero

    override fun div(other: PositiveBigInt): Zero = Zero

    override fun div(other: NegativeBigInt): Zero = Zero

    override operator fun minus(other: Zero): Zero = Zero

    override fun unaryPlus(): Zero = Zero

    override fun unaryMinus(): Zero = Zero

    override val absoluteValue: BigInt
        get() = Zero
    override val sign: BigInt.Sign
        get() = BigInt.Sign.ZERO

    override fun compareTo(other: BigInt): Int = when (other) {
        is NegativeBigInt -> 1
        is PositiveBigInt -> -1
        Zero -> 0
    }

    override fun toString(): String = "0x0"
}