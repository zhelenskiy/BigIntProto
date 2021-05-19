import kotlin.jvm.JvmInline

@ExperimentalUnsignedTypes
@JvmInline
value class UBigInt(private val positiveValue: PositiveBigInt?) : Comparable<UBigInt> {

    operator fun plus(other: UBigInt): UBigInt = other.positiveValue?.let { this + it } ?: this
    operator fun plus(other: PositiveBigInt): UBigInt = UBigInt(this.positiveValue?.plus(other) ?: other)
    operator fun plus(other: Zero): UBigInt = this

    operator fun minus(other: Zero): UBigInt = this
    operator fun minus(other: NegativeBigInt): UBigInt = this + -other
    operator fun minus(other: UBigInt): UBigInt = (this.toBigInt() - other.toBigInt()).toUBigIntOrThrow()

    operator fun times(other: UBigInt): UBigInt = other.positiveValue?.let { this * it } ?: zero
    operator fun times(other: Zero): UBigInt = zero
    operator fun times(other: PositiveBigInt): UBigInt = this.positiveValue?.times(other)?.toUBigInt() ?: zero

    operator fun div(other: UBigInt): UBigInt = other.positiveValue?.let { this / it } ?: div(Zero)
    operator fun div(other: Zero): Nothing = this.toBigInt().div(other)
    operator fun div(other: PositiveBigInt): UBigInt = this.positiveValue?.div(other) ?: zero

    fun toPositiveOrNull(): PositiveBigInt? = positiveValue
    fun toBigInt(): BigInt = positiveValue ?: Zero

    operator fun unaryPlus(): UBigInt = this

    override fun compareTo(other: UBigInt): Int = when (positiveValue) {
        null -> if (other.positiveValue == null) 0 else -1
        else -> if (other.positiveValue == null) 1 else positiveValue.compareTo(other.positiveValue)
    }
}

@ExperimentalUnsignedTypes
val zero = UBigInt(null)

@ExperimentalUnsignedTypes
fun PositiveBigInt.toUBigInt(): UBigInt = UBigInt(this)

@ExperimentalUnsignedTypes
fun BigInt.toUBigIntOrNull(): UBigInt? = when (this) {
    is PositiveBigInt -> toUBigInt()
    is Zero -> zero
    is NegativeBigInt -> null
}

@ExperimentalUnsignedTypes
fun BigInt.toUBigIntOrThrow(): UBigInt = when (this) {
    is PositiveBigInt -> toUBigInt()
    is Zero -> zero
    is NegativeBigInt -> throw IllegalArgumentException("$this cannot be converted to UBigInt")
}