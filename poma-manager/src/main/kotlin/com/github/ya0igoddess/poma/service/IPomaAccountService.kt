package com.github.ya0igoddess.poma.service

import com.github.ya0igoddess.poma.model.Poma
import com.github.ya0igoddess.poma.model.PomaAccount
import com.github.ya0igoddess.dbsync.model.discord.DsMember
import kotlinx.coroutines.flow.Flow

interface IPomaAccountService {
    suspend fun createPomaAccount(member: DsMember): PomaAccount

    suspend fun getMemberByAccount(pomaAccount: PomaAccount): DsMember?
    fun getAccountPomas(pomaAccount: PomaAccount): Flow<Poma>

    suspend fun rollNewPoma(pomaAccount: PomaAccount): Poma

    /**
     * @param money Money delta value, may be negative.
     */
    fun changeMoney(pomaAccount: PomaAccount, money: Long)
}