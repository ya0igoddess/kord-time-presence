package com.mquiz.service

import com.mquiz.repositories.ISampleRepoService
import dev.kord.core.kordLogger

class SampleService(
        private val repo: ISampleRepoService
) : ISampleService {
    override suspend fun doSomething() {
        kordLogger.info { "Something happened!" }
    }
}