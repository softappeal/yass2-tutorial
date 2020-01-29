@file:Suppress("unused")

package ch.softappeal.yass2.tutorial

import ch.softappeal.yass2.transport.*
import ch.softappeal.yass2.transport.js.*
import ch.softappeal.yass2.tutorial.contract.*
import ch.softappeal.yass2.tutorial.contract.generated.*
import kotlinx.coroutines.*
import kotlin.js.Promise

@JsName("callSuspendFunction")
fun <T> callSuspendFunction(block: suspend () -> T): Promise<T> = GlobalScope.promise { block() }

private val Config = TransportConfig(GeneratedSerializer, 100)

suspend fun suspendFunction(): String {
    showGeneratedUsage()

    // shows client-side unidirectional remoting with Http
    useCalculator(Config.tunnel("/yass"), ::generatedRemoteProxyFactoryCreator)

    // shows client-side session based bidirectional remoting with WebSocket
    val initiatorSessionFactory = CoroutineScope(Job()).initiatorSessionFactory()
    Config.connect("ws://localhost:28947/yass", initiatorSessionFactory)

    return "done"
}
