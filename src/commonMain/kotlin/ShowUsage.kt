package ch.softappeal.yass2.tutorial

import ch.softappeal.yass2.*
import ch.softappeal.yass2.remote.*
import ch.softappeal.yass2.remote.coroutines.*
import ch.softappeal.yass2.remote.coroutines.session.*
import ch.softappeal.yass2.serialize.*
import ch.softappeal.yass2.transport.*
import ch.softappeal.yass2.tutorial.contract.*
import ch.softappeal.yass2.tutorial.contract.generated.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

suspend fun showGeneratedUsage() {
    val generatedDumper = dumper(GeneratedDumperProperties, ValueDumper, compact = false)
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

val FlowServiceImpl = flowService { flowId -> (1..(flowId as Int)).asFlow() }

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

suspend fun useServices(tunnel: Tunnel, remoteProxyFactoryCreator: RemoteProxyFactoryCreator) {
    val remoteProxyFactory = remoteProxyFactoryCreator(tunnel)
    val calculator = remoteProxyFactory(CalculatorId)
    val flowService = remoteProxyFactory(FlowServiceId)
    useCalculator(calculator)
    val flow = flowService.createFlow<Int>(3)
    flow.collect { println("value: $it") }
}

val Services = listOf( // register services
    CalculatorId(CalculatorImpl),
    FlowServiceId(FlowServiceImpl)
)

// The following code is only needed if you use session based bidirectional remoting.

fun CoroutineScope.initiatorSessionFactory(): SessionFactory = {
    object : Session() {
        override val serverTunnel = ::generatedInvoker.tunnel(listOf(
            NewsListenerId(NewsListenerImpl) // register service
        ))

        override fun opened() {
            launch {
                useServices(clientTunnel, ::generatedRemoteProxyFactoryCreator)
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
        override val serverTunnel = ::generatedInvoker.tunnel(Services)

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
