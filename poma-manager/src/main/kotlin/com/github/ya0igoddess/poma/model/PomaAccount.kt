package com.github.ya0igoddess.poma.model

data class PomaAccount(
    val id: Long?,
    val money: Long,
    val userId: Long
) {
    companion object {
        val DEFAULT_MONEY = 100L
    }
}
