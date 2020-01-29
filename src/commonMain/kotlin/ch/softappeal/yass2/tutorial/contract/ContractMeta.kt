package ch.softappeal.yass2.tutorial.contract

import ch.softappeal.yass2.*
import ch.softappeal.yass2.remote.*
import ch.softappeal.yass2.remote.session.*
import ch.softappeal.yass2.serialize.binary.*
import ch.softappeal.yass2.tutorial.contract.generated.*

// This file describes the needed contract metadata.

/** Shows how to implement an own base type encoder. */
private val MyDateEncoder = BaseEncoder(MyDate::class,
    { writer, value -> writer.writeLong(value.currentTimeMillis) },
    { reader -> MyDate(reader.readLong()) }
)

/**
 * Define all the base encoders needed by the contract (including enumerations and own base types).
 * [IntEncoder] is always needed because of [ConcreteClasses] below.
 */
val BaseEncoders = listOf(
    IntEncoder,
    StringEncoder,
    enumEncoder<Gender>(),
    MyDateEncoder
)

/**
 * Define all the concrete classes needed by the contract.
 * [Request], [ValueReply] and [ExceptionReply] are always needed (these need [IntEncoder]).
 * [Packet] is only needed when we need [Session].
 */
val ConcreteClasses = listOf(
    Request::class, ValueReply::class, ExceptionReply::class,
    Packet::class,
    Address::class, Person::class, DivideByZeroException::class, SubClass::class
)

val GeneratedSerializer = generatedBinarySerializer(BaseEncoders)

/** Define the [ServiceId] for each contract interface. */
val CalculatorId = serviceId<Calculator>(1)
val NewsListenerId = serviceId<NewsListener>(2)

/** Define all used [ServiceId]. */
val ServiceIds = listOf(CalculatorId, NewsListenerId)

/**
 * Define [BaseDumper] for base types.
 * [Boolean], [Number] and [CharSequence] are handled by default.
 */
val BaseDumper: BaseDumper = { value ->
    when (value) {
        is Gender -> append(value.name)
        is MyDate -> append("MyDate(${value.currentTimeMillis})")
    }
}

@Suppress("unused")
private val GeneratedDumper = dumper(GeneratedDumperProperties, BaseDumper)
// fun generatedDumper() = GeneratedDumper // TODO: only works on JVM; probably a compiler bug

fun generatedDumper() = dumper(GeneratedDumperProperties, BaseDumper)
