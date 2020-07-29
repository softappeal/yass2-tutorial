package ch.softappeal.yass2.tutorial.contract.generated

@Suppress("UNCHECKED_CAST", "RemoveRedundantQualifierName", "SpellCheckingInspection")
fun generatedBinarySerializer(
    baseEncoders: List<ch.softappeal.yass2.serialize.binary.BaseEncoder<*>>
) = ch.softappeal.yass2.serialize.binary.BinarySerializer(baseEncoders + listOf(
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.remote.Request::class, // 7
        { w, i ->
            w.writeNoIdRequired(3, i.serviceId)
            w.writeNoIdRequired(3, i.functionId)
            w.writeNoIdRequired(1, i.parameters)
        },
        { r ->
            val i = r.created(ch.softappeal.yass2.remote.Request(
                r.readNoIdRequired(3) as kotlin.Int,
                r.readNoIdRequired(3) as kotlin.Int,
                r.readNoIdRequired(1) as kotlin.collections.List<kotlin.Any?>
            ))
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.remote.ValueReply::class, // 8
        { w, i ->
            w.writeWithId(i.value)
        },
        { r ->
            val i = r.created(ch.softappeal.yass2.remote.ValueReply(
                r.readWithId()
            ))
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.remote.ExceptionReply::class, // 9
        { w, i ->
            w.writeWithId(i.exception)
        },
        { r ->
            val i = r.created(ch.softappeal.yass2.remote.ExceptionReply(
                r.readWithId() as kotlin.Exception /* = java.lang.Exception */
            ))
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.remote.session.Packet::class, // 10
        { w, i ->
            w.writeNoIdRequired(3, i.requestNumber)
            w.writeWithId(i.message)
        },
        { r ->
            val i = r.created(ch.softappeal.yass2.remote.session.Packet(
                r.readNoIdRequired(3) as kotlin.Int,
                r.readWithId() as ch.softappeal.yass2.remote.Message
            ))
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.tutorial.contract.Address::class, // 11
        { w, i ->
            w.writeNoIdRequired(4, i.street)
            w.writeNoIdOptional(3, i.number)
        },
        { r ->
            val i = r.created(ch.softappeal.yass2.tutorial.contract.Address(
                r.readNoIdRequired(4) as kotlin.String
            ))
            i.number = r.readNoIdOptional(3) as kotlin.Int?
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.tutorial.contract.Person::class, // 12
        { w, i ->
            w.writeNoIdRequired(4, i.name)
            w.writeNoIdRequired(5, i.gender)
            w.writeNoIdRequired(1, i.addresses)
        },
        { r ->
            val i = r.created(ch.softappeal.yass2.tutorial.contract.Person(
                r.readNoIdRequired(4) as kotlin.String,
                r.readNoIdRequired(5) as ch.softappeal.yass2.tutorial.contract.Gender,
                r.readNoIdRequired(1) as kotlin.collections.List<ch.softappeal.yass2.tutorial.contract.Address>
            ))
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.tutorial.contract.DivideByZeroException::class, // 13
        { _, _ -> },
        { r ->
            val i = r.created(ch.softappeal.yass2.tutorial.contract.DivideByZeroException(
            ))
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.tutorial.contract.SubClass::class, // 14
        { w, i ->
            w.writeNoIdRequired(4, i.baseClassProperty)
            w.writeNoIdRequired(4, i.subClassProperty)
        },
        { r ->
            val i = r.created(ch.softappeal.yass2.tutorial.contract.SubClass(
                r.readNoIdRequired(4) as kotlin.String,
                r.readNoIdRequired(4) as kotlin.String
            ))
            i
        }
    )
))
