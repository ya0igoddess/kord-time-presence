package com.example.service

import com.example.repositories.ISampleRepoService
import dev.kord.core.kordLogger

class SampleService(
    private val repo: ISampleRepoService
) : ISampleService {
    override suspend fun doSomething() {
        kordLogger.info { "Something happened!" }
    }
}