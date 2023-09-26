package com.github.ya0igoddess.dbsync.config.settings

import com.github.ya0igoddess.dbsync.config.repoServiceModule
import com.github.ya0igoddess.dbsync.contextual.member.MemberContextSupplier
import org.koin.dsl.module

val contextSuppliersModule = module {
    includes(repoServiceModule)

    single { MemberContextSupplier(get()) }
}