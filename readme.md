### yass2-tutorial

* [yass2](https://github.com/softappeal/yass2/)

* [Common Module](src/commonMain/kotlin)

* [Jvm Module](src/jvmMain/kotlin)

* [Js Module](src/jsMain/kotlin)

Output

```
*** useDumper ***
Person(
    addresses = [
        Address(
            number = 1
            street = "Infinity Drive"
        )
        Address(
            street = "Hollywood Boulevard"
        )
    ]
    gender = Female
    name = "Guru"
)

*** useSerializer ***
hello

*** useInterceptor ***
calling function 'add'
1 + 2 = 3

*** useDumper ***
Person(
    addresses = [
        Address(
            number = 1
            street = "Infinity Drive"
        )
        Address(
            street = "Hollywood Boulevard"
        )
    ]
    gender = Female
    name = "Guru"
)

*** useSerializer ***
hello

*** useInterceptor ***
calling function 'add'
1 + 2 = 3

*** useKtorRemoting ***
1 + 2 = 3
NewsListener.notify: News 1
1 + 2 = 3
NewsListener.notify: News 2
initiatorSessionFactory closed: null
acceptorSessionFactory closed: null

*** useKtorRemoting ***
1 + 2 = 3
NewsListener.notify: News 1
1 + 2 = 3
NewsListener.notify: News 2
initiatorSessionFactory closed: null
acceptorSessionFactory closed: null
```
