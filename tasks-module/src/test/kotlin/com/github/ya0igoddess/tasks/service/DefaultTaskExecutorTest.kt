package com.github.ya0igoddess.tasks.service

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import kotlinx.coroutines.delay
import java.util.*

class DefaultTaskExecutorTest : FunSpec({

    val executor = DefaultTaskExecutor()
    val name = "test"



    test("submits task") {
        val set = Collections.synchronizedSet(mutableSetOf<Int>())
        for (i in 1..3) {
            executor.submit(name, mockk()) {
                delay(100)
                set.add(i)
            }
        }
        delay(400)
        set.size shouldBe 3
    }

    test("returns currently running tasks") {
        executor.submit(name, mockk()) {
            delay(1000)
        }
        executor.getRunningTasks().size shouldBe 1
    }
})
