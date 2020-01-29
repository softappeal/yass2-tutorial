package ch.softappeal.yass2.tutorial.contract.generated

@Suppress("UNCHECKED_CAST")
val GeneratedDumperProperties = ch.softappeal.yass2.dumperProperties(
    ch.softappeal.yass2.remote.Request::class to listOf(
        ch.softappeal.yass2.remote.Request::functionId as kotlin.reflect.KProperty1<Any, Any?>,
        ch.softappeal.yass2.remote.Request::parameters as kotlin.reflect.KProperty1<Any, Any?>,
        ch.softappeal.yass2.remote.Request::serviceId as kotlin.reflect.KProperty1<Any, Any?>
    ),
    ch.softappeal.yass2.remote.ValueReply::class to listOf(
        ch.softappeal.yass2.remote.ValueReply::value as kotlin.reflect.KProperty1<Any, Any?>
    ),
    ch.softappeal.yass2.remote.ExceptionReply::class to listOf(
        ch.softappeal.yass2.remote.ExceptionReply::exception as kotlin.reflect.KProperty1<Any, Any?>
    ),
    ch.softappeal.yass2.remote.session.Packet::class to listOf(
        ch.softappeal.yass2.remote.session.Packet::message as kotlin.reflect.KProperty1<Any, Any?>,
        ch.softappeal.yass2.remote.session.Packet::requestNumber as kotlin.reflect.KProperty1<Any, Any?>
    ),
    ch.softappeal.yass2.tutorial.contract.Address::class to listOf(
        ch.softappeal.yass2.tutorial.contract.Address::number as kotlin.reflect.KProperty1<Any, Any?>,
        ch.softappeal.yass2.tutorial.contract.Address::street as kotlin.reflect.KProperty1<Any, Any?>
    ),
    ch.softappeal.yass2.tutorial.contract.Person::class to listOf(
        ch.softappeal.yass2.tutorial.contract.Person::addresses as kotlin.reflect.KProperty1<Any, Any?>,
        ch.softappeal.yass2.tutorial.contract.Person::gender as kotlin.reflect.KProperty1<Any, Any?>,
        ch.softappeal.yass2.tutorial.contract.Person::name as kotlin.reflect.KProperty1<Any, Any?>
    ),
    ch.softappeal.yass2.tutorial.contract.DivideByZeroException::class to listOf(
    ),
    ch.softappeal.yass2.tutorial.contract.SubClass::class to listOf(
        ch.softappeal.yass2.tutorial.contract.SubClass::baseClassProperty as kotlin.reflect.KProperty1<Any, Any?>,
        ch.softappeal.yass2.tutorial.contract.SubClass::subClassProperty as kotlin.reflect.KProperty1<Any, Any?>
    )
)
