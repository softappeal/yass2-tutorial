package ch.softappeal.yass2.tutorial.contract.generated

@Suppress("UNCHECKED_CAST", "RemoveRedundantQualifierName", "SpellCheckingInspection")
fun generatedBinarySerializer(
    baseEncoders: List<ch.softappeal.yass2.serialize.binary.BaseEncoder<*>>
) = ch.softappeal.yass2.serialize.binary.BinarySerializer(baseEncoders + listOf(
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.remote.Request::class, // 6
        { w, i ->
            w.writeNoIdRequired(2, i.functionId)
            w.writeNoIdRequired(1, i.parameters)
            w.writeNoIdRequired(2, i.serviceId)
        },
        { r ->
            val pfunctionId = r.readNoIdRequired(2) as kotlin.Int
            val pparameters = r.readNoIdRequired(1) as kotlin.collections.List<kotlin.Any?>
            val pserviceId = r.readNoIdRequired(2) as kotlin.Int
            val i = ch.softappeal.yass2.remote.Request(pserviceId, pfunctionId, pparameters)
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.remote.ValueReply::class, // 7
        { w, i ->
            w.writeWithId(i.value)
        },
        { r ->
            val pvalue = r.readWithId()
            val i = ch.softappeal.yass2.remote.ValueReply(pvalue)
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.remote.ExceptionReply::class, // 8
        { w, i ->
            w.writeWithId(i.exception)
        },
        { r ->
            val pexception = r.readWithId() as kotlin.Exception /* = java.lang.Exception */
            val i = ch.softappeal.yass2.remote.ExceptionReply(pexception)
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.remote.session.Packet::class, // 9
        { w, i ->
            w.writeWithId(i.message)
            w.writeNoIdRequired(2, i.requestNumber)
        },
        { r ->
            val pmessage = r.readWithId() as ch.softappeal.yass2.remote.Message
            val prequestNumber = r.readNoIdRequired(2) as kotlin.Int
            val i = ch.softappeal.yass2.remote.session.Packet(prequestNumber, pmessage)
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.tutorial.contract.Address::class, // 10
        { w, i ->
            w.writeNoIdOptional(2, i.number)
            w.writeNoIdRequired(3, i.street)
        },
        { r ->
            val pnumber = r.readNoIdOptional(2) as kotlin.Int?
            val pstreet = r.readNoIdRequired(3) as kotlin.String
            val i = ch.softappeal.yass2.tutorial.contract.Address(pstreet)
            i.number = pnumber
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.tutorial.contract.Person::class, // 11
        { w, i ->
            w.writeNoIdRequired(1, i.addresses)
            w.writeNoIdRequired(4, i.gender)
            w.writeNoIdRequired(3, i.name)
        },
        { r ->
            val paddresses = r.readNoIdRequired(1) as kotlin.collections.List<ch.softappeal.yass2.tutorial.contract.Address>
            val pgender = r.readNoIdRequired(4) as ch.softappeal.yass2.tutorial.contract.Gender
            val pname = r.readNoIdRequired(3) as kotlin.String
            val i = ch.softappeal.yass2.tutorial.contract.Person(pname, pgender, paddresses)
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.tutorial.contract.DivideByZeroException::class, // 12
        { _, _ -> },
        {
            val i = ch.softappeal.yass2.tutorial.contract.DivideByZeroException()
            i
        }
    ),
    ch.softappeal.yass2.serialize.binary.ClassEncoder(ch.softappeal.yass2.tutorial.contract.SubClass::class, // 13
        { w, i ->
            w.writeNoIdRequired(3, i.baseClassProperty)
            w.writeNoIdRequired(3, i.subClassProperty)
        },
        { r ->
            val pbaseClassProperty = r.readNoIdRequired(3) as kotlin.String
            val psubClassProperty = r.readNoIdRequired(3) as kotlin.String
            val i = ch.softappeal.yass2.tutorial.contract.SubClass(pbaseClassProperty, psubClassProperty)
            i
        }
    )
))
