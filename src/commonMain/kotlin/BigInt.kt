@ExperimentalUnsignedTypes
sealed interface BigInt : Comparable<BigInt> {

    operator fun plus(other: BigInt): BigInt

    operator fun minus(other: BigInt): BigInt

    operator fun times(other: BigInt): BigInt

    operator fun div(other: BigInt): BigInt

    //todo rem and in uint
    //todo mod

    operator fun unaryPlus(): BigInt
    operator fun unaryMinus(): BigInt

    val absoluteValue: UBigInt

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
internal fun BigInt.div0(): Nothing = throw ArithmeticException("$this / $zero")