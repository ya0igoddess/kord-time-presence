package com.example.config

import com.example.service.ISampleService
import com.example.service.SampleService
import org.koin.dsl.module

val serviceModule = module {
    includes(repoServiceModule)

    single<ISampleService> { SampleService(get()) }
}