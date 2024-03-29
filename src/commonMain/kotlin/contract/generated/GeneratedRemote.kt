package ch.softappeal.yass2.tutorial.contract.generated

@Suppress("UNCHECKED_CAST", "PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RemoveRedundantQualifierName", "SpellCheckingInspection", "RedundantVisibilityModifier")
public fun generatedRemoteProxyFactoryCreator(
    tunnel: ch.softappeal.yass2.remote.Tunnel,
): ch.softappeal.yass2.remote.RemoteProxyFactory =
    object : ch.softappeal.yass2.remote.RemoteProxyFactory {
        suspend operator fun ch.softappeal.yass2.remote.Tunnel.invoke(serviceId: Int, functionId: Int, vararg parameters: Any?): Any? =
            this(ch.softappeal.yass2.remote.Request(serviceId, functionId, listOf(*parameters))).process()

        override fun <S : Any> create(serviceId: ch.softappeal.yass2.remote.ServiceId<S>): S = when (serviceId.id) {
            1 -> object : ch.softappeal.yass2.tutorial.contract.Calculator {
                override suspend fun add(p1: kotlin.Int, p2: kotlin.Int) = tunnel(1, 0, p1, p2) as kotlin.Int

                override suspend fun divide(p1: kotlin.Int, p2: kotlin.Int) = tunnel(1, 1, p1, p2) as kotlin.Int
            } as S
            2 -> object : ch.softappeal.yass2.tutorial.contract.NewsListener {
                override suspend fun notify(p1: kotlin.String) {
                    tunnel(2, 0, p1)
                }
            } as S
            else -> error("no service id ${serviceId.id}")
        }
    }

@Suppress("RemoveRedundantQualifierName", "SpellCheckingInspection", "RedundantVisibilityModifier")
public suspend fun generatedInvoker(
    request: ch.softappeal.yass2.remote.Request, service: ch.softappeal.yass2.remote.Service,
): Any? {
    val p = request.parameters
    return when (request.serviceId) {
        1 -> {
            val i = service.implementation as ch.softappeal.yass2.tutorial.contract.Calculator
            when (request.functionId) {
                0 -> i.add(p[0] as kotlin.Int, p[1] as kotlin.Int)
                1 -> i.divide(p[0] as kotlin.Int, p[1] as kotlin.Int)
                else -> ch.softappeal.yass2.remote.missingFunction(request)
            }
        }
        2 -> {
            val i = service.implementation as ch.softappeal.yass2.tutorial.contract.NewsListener
            when (request.functionId) {
                0 -> i.notify(p[0] as kotlin.String)
                else -> ch.softappeal.yass2.remote.missingFunction(request)
            }
        }
        else -> error("no service id ${request.serviceId}")
    }
}
