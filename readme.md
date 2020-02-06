### yass2-tutorial

* [yass2](https://github.com/softappeal/yass2/)

* [Common Module](src/commonMain/kotlin/ch/softappeal/yass2/tutorial)

* [Jvm Module](src/jvmMain/kotlin/ch/softappeal/yass2/tutorial)

* [Js Module](src/jsMain)

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
NewsListener.notify: News 2
1 + 2 = 3
initiatorSessionFactory closed: null
acceptorSessionFactory closed: null
```
