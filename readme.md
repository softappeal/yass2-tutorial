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
        )#1
        Address(
            street = "Hollywood Boulevard"
        )#2
    ]
    gender = Female
    name = "Guru"
)#0

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
        )#1
        Address(
            street = "Hollywood Boulevard"
        )#2
    ]
    gender = Female
    name = "Guru"
)#0

*** useSerializer ***
hello

*** useInterceptor ***
calling function 'add'
1 + 2 = 3

*** useKtorRemoting ***
1 + 2 = 3
value: 1
value: 2
value: 3
NewsListener.notify: News 1
1 + 2 = 3
NewsListener.notify: News 2
value: 1
value: 2
value: 3
initiatorSessionFactory closed: null
acceptorSessionFactory closed: null

*** useKtorRemoting ***
1 + 2 = 3
value: 1
value: 2
value: 3
NewsListener.notify: News 1
1 + 2 = 3
NewsListener.notify: News 2
value: 1
value: 2
value: 3
initiatorSessionFactory closed: null
acceptorSessionFactory closed: null
```
