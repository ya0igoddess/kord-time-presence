package com.github.ya0igoddess.poma.contexts

import com.github.ya0igoddess.poma.model.PomaAccount

interface PomaManagerContext {
    val account: PomaAccount
}