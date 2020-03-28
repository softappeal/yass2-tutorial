package ch.softappeal.yass2.tutorial

import ch.softappeal.yass2.*
import ch.softappeal.yass2.remote.*
import ch.softappeal.yass2.remote.session.*
import ch.softappeal.yass2.serialize.*
import ch.softappeal.yass2.transport.*
import ch.softappeal.yass2.tutorial.contract.*
import ch.softappeal.yass2.tutorial.contract.generated.*
import kotlinx.coroutines.*

suspend fun showGeneratedUsage() {
    val generatedDumper = dumper(GeneratedDumperProperties, BaseDumper)
    useDumper(generatedDumper)
    useSerializer(GeneratedSerializer)
    useInterceptor(GeneratedProxyFactory)
}

fun useDumper(dumper: Dumper) {
    println("*** useDumper ***")
    val person = Person(
        "Guru",
        Gender.Female,
        listOf(
            Address("Infinity Drive").apply { number = 1 },
            Address("Hollywood Boulevard")
        )
    )
    println(StringBuilder().dumper(person))
    println()
}

fun useSerializer(serializer: Serializer) {
    println("*** useSerializer ***")
    val writer = BytesWriter(100)
    serializer.write(writer, "hello")
    val reader = BytesReader(writer.buffer)
    val value = serializer.read(reader)
    println(value)
    println()
}

val CalculatorImpl = object : Calculator {
    override suspend fun add(a: Int, b: Int) = a + b
    override suspend fun divide(a: Int, b: Int) = if (b == 0) throw DivideByZeroException() else a / b
}

val NewsListenerImpl = object : NewsListener {
    override suspend fun notify(news: String) {
        println("NewsListener.notify: $news")
    }
}

private suspend fun useCalculator(calculator: Calculator) {
    println("1 + 2 = ${calculator.add(1, 2)}")
}

suspend fun useInterceptor(proxyFactory: ProxyFactory) {
    println("*** useInterceptor ***")
    val interceptor: SuspendInterceptor = { function, _, invocation ->
        println("calling function '${function.name}'")
        invocation()
    }
    val calculator = proxyFactory(CalculatorImpl, interceptor)
    useCalculator(calculator)
    println()
}

suspend fun useCalculator(tunnel: Tunnel, remoteProxyFactoryCreator: RemoteProxyFactoryCreator) {
    val remoteProxyFactory = remoteProxyFactoryCreator(tunnel)
    val calculator = remoteProxyFactory(CalculatorId)
    useCalculator(calculator)
}

// The following code is only needed if you use session based bidirectional remoting.

fun CoroutineScope.initiatorSessionFactory(): SessionFactory = {
    object : Session() {
        override val serverTunnel = ::generatedInvoker.tunnel(listOf(
            NewsListenerId(NewsListenerImpl) // register NewsListener service
        ))

        override fun opened() {
            launch {
                useCalculator(clientTunnel, ::generatedRemoteProxyFactoryCreator)
                delay(100) // give the server some time to send news
                close()
            }
        }

        override suspend fun closed(e: Exception?) {
            println("initiatorSessionFactory closed: $e")
        }
    }
}

fun CoroutineScope.acceptorSessionFactory(): SessionFactory = {
    object : Session() {
        override val serverTunnel = ::generatedInvoker.tunnel(listOf(
            CalculatorId(CalculatorImpl) // register Calculator service
        ))

        override fun opened() {
            launch {
                val remoteProxyFactory = generatedRemoteProxyFactoryCreator(clientTunnel)
                val newsListener = remoteProxyFactory(NewsListenerId)
                newsListener.notify("News 1")
                newsListener.notify("News 2")
            }
        }

        override suspend fun closed(e: Exception?) {
            println("acceptorSessionFactory closed: $e")
        }
    }
}
