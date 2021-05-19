@ExperimentalUnsignedTypes
sealed interface BigInt : Comparable<BigInt> {

    operator fun plus(other: PositiveBigInt): BigInt
    operator fun plus(other: NegativeBigInt): BigInt
    operator fun plus(other: Zero): BigInt

    operator fun minus(other: PositiveBigInt): BigInt
    operator fun minus(other: Zero): BigInt
    operator fun minus(other: NegativeBigInt): BigInt

    operator fun times(other: PositiveBigInt): BigInt
    operator fun times(other: NegativeBigInt): BigInt

    operator fun div(other: PositiveBigInt): BigInt
    operator fun div(other: NegativeBigInt): BigInt

    //todo rem
    //todo mod

    operator fun unaryPlus(): BigInt
    operator fun unaryMinus(): BigInt

    val absoluteValue: BigInt //todo UBigInt

    enum class Sign {
        MINUS, ZERO, PLUS;
        fun toInt(): Int = when (this) {
            MINUS -> -1
            ZERO -> 0
            PLUS -> 1
        }
    }

    val sign: Sign
}

@ExperimentalUnsignedTypes
operator fun BigInt.plus(other: BigInt): BigInt = when (other) {
    is NegativeBigInt -> plus(other)
    is PositiveBigInt -> plus(other)
    is Zero -> plus(other)
}

@ExperimentalUnsignedTypes
operator fun BigInt.minus(other: BigInt): BigInt = when (other) {
    is NegativeBigInt -> minus(other)
    is PositiveBigInt -> minus(other)
    is Zero -> minus(other)
}

@ExperimentalUnsignedTypes
inline operator fun BigInt.times(other: Zero): Zero = Zero

@ExperimentalUnsignedTypes
operator fun BigInt.times(other: BigInt): BigInt = when (other) {
    is NegativeBigInt -> times(other)
    is PositiveBigInt -> times(other)
    is Zero -> times(other)
}


@ExperimentalUnsignedTypes
operator fun BigInt.div(other: Zero): Nothing = throw ArithmeticException("$this / $other")

@ExperimentalUnsignedTypes
operator fun BigInt.div(other: BigInt): BigInt = when (other) {
    is NegativeBigInt -> div(other)
    is PositiveBigInt -> div(other)
    is Zero -> div(other)
}