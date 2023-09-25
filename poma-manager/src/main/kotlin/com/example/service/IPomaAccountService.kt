package com.example.service

import com.example.model.Poma
import com.example.model.PomaAccount
import com.github.ya0igoddess.dbsync.model.discord.DsMember
import kotlinx.coroutines.flow.Flow

interface IPomaAccountService {
    suspend fun createPomaAccount(member: DsMember): PomaAccount

    suspend fun getMemberByAccount(pomaAccount: PomaAccount): DsMember?
    suspend fun getAccountPomas(pomaAccount: PomaAccount): Flow<Poma>

    suspend fun rollNewPoma(pomaAccount: PomaAccount): Poma

    /**
     * @param money Money delta value, may be negative.
     */
    fun changeMoney(pomaAccount: PomaAccount, money: Long)
}