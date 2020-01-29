package ch.softappeal.yass2.tutorial.js

import ch.softappeal.yass2.tutorial.*
import ch.softappeal.yass2.tutorial.contract.generated.*
import io.ktor.util.*

@KtorExperimentalAPI // TODO
fun main() {
    println("http://localhost:$Port/index.html")
    createKtorEngine(::generatedInvoker)
        .start(wait = true)
}
