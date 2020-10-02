@file:OptIn(KtorExperimentalAPI::class)

package ch.softappeal.yass2.tutorial

import ch.softappeal.yass2.reflect.*
import ch.softappeal.yass2.remote.*
import ch.softappeal.yass2.remote.reflect.*
import ch.softappeal.yass2.serialize.binary.reflect.*
import ch.softappeal.yass2.transport.ktor.*
import ch.softappeal.yass2.tutorial.contract.*
import ch.softappeal.yass2.tutorial.contract.generated.*
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.util.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import java.util.concurrent.*

fun main() = runBlocking {
    showReflectionUsage()
    showGeneratedUsage()
    useKtorRemoting(::reflectionRemoteProxyFactoryCreator, ::reflectionInvoker)
    useKtorRemoting(::generatedRemoteProxyFactoryCreator, ::generatedInvoker)
}

private suspend fun showReflectionUsage() {
    useDumper(reflectionDumper(BaseDumper))
    useSerializer(reflectionBinarySerializer(BaseEncoders, ConcreteClasses))
    useInterceptor(ReflectionProxyFactory)
}

const val Host = "localhost"
const val Port = 28947
private const val Path = "/yass"

fun createKtorEngine(invoker: Invoker): ApplicationEngine = embeddedServer(io.ktor.server.cio.CIO, Port) {
    install(io.ktor.websocket.WebSockets)
    routing {
        static {
            // serves static content needed for js tutorial
            files("./")
            files("./src/jsMain/resources")
            files("./build/js/node_modules")
        }

        // shows server-side unidirectional remoting with Http
        route(MessageConfig, Path, invoker.tunnel(Services))

        // shows server-side session based bidirectional remoting with WebSocket
        webSocket(Path) { receiveLoop(PacketConfig, acceptorSessionFactory()) }
    }
}

private suspend fun useKtorRemoting(remoteProxyFactoryCreator: RemoteProxyFactoryCreator, invoker: Invoker) {
    println("*** useKtorRemoting ***")
    val engine = createKtorEngine(invoker)
    engine.start()
    try {
        HttpClient(io.ktor.client.engine.cio.CIO) {
            install(io.ktor.client.features.websocket.WebSockets)
        }.use { client ->
            // shows client-side unidirectional remoting with Http
            useServices(client.tunnel(MessageConfig, "http://$Host:$Port$Path"), remoteProxyFactoryCreator)

            // shows client-side session based bidirectional remoting with WebSocket
            client.ws(HttpMethod.Get, Host, Port, Path) { receiveLoop(PacketConfig, initiatorSessionFactory()) }
        }
    } finally {
        engine.stop(0, 0, TimeUnit.SECONDS)
    }
    delay(2_000) // needed for graceful shutdown
    println()
}
