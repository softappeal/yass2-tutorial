package ch.softappeal.yass2.tutorial

import kotlinx.coroutines.*
import kotlin.test.*

class TutorialTest {
    @Test
    fun test() = GlobalScope.launch {
        showGeneratedUsage()
    }
}
