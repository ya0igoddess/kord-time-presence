package com.example.service

import com.example.model.Poma
import com.example.model.PomaAccount
import com.example.repositories.PomaAccountRepoService
import com.example.repositories.PomaRepoService
import com.github.ya0igoddess.dbsync.model.discord.DsMember
import com.github.ya0igoddess.dbsync.repositories.DiscordMemberRepoService
import kotlinx.coroutines.flow.Flow

class PomaAccountService(
    private val pomaAccountRepoService: PomaAccountRepoService,
    private val discordMemberRepoService: DiscordMemberRepoService,
    private val pomaRepoService: PomaRepoService
) : IPomaAccountService {
    override suspend fun createPomaAccount(member: DsMember): PomaAccount {
        val account = PomaAccount(
            id = member.id,
            userId = member.id,
            money = PomaAccount.DEFAULT_MONEY
        )
        return pomaAccountRepoService.save(account)
    }

    override suspend fun getMemberByAccount(pomaAccount: PomaAccount): DsMember? {
        return discordMemberRepoService.getById(pomaAccount.id!!)
    }

    override suspend fun getAccountPomas(pomaAccount: PomaAccount): Flow<Poma> {
        return pomaRepoService.getByAccountId(pomaAccount.id!!)
    }

    override fun changeMoney(pomaAccount: PomaAccount, money: Long) {
        TODO("Not yet implemented")
    }
}