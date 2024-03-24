package com.github.ya0igoddess.dbsync.extensions

import com.kotlindiscord.kord.extensions.extensions.Extension
import org.kodein.di.conf.ConfigurableDI

abstract class KodeinExtension: Extension() {
    companion object {
        val di = ConfigurableDI(mutable = true)
    }
}