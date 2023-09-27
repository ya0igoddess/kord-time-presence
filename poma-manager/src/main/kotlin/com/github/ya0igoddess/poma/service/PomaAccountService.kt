package com.github.ya0igoddess.poma.service

import com.github.ya0igoddess.poma.model.Poma
import com.github.ya0igoddess.poma.model.PomaAccount
import com.github.ya0igoddess.poma.repositories.IPomaAccountRepoService
import com.github.ya0igoddess.poma.repositories.IPomaRepoService
import com.github.ya0igoddess.poma.repositories.PomaAccountRepoService
import com.github.ya0igoddess.poma.repositories.PomaRepoService
import com.github.ya0igoddess.dbsync.model.discord.DsMember
import com.github.ya0igoddess.dbsync.repositories.DiscordMemberRepoService
import com.github.ya0igoddess.dbsync.repositories.IDiscordMemberRepoService
import com.github.ya0igoddess.poma.pomagenerator.PomaGenerator
import com.kotlindiscord.kord.extensions.DiscordRelayedException
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

class PomaAccountService(
    private val pomaAccountRepoService: IPomaAccountRepoService,
    private val discordMemberRepoService: IDiscordMemberRepoService,
    private val pomaRepoService: IPomaRepoService
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

    override fun getAccountPomas(pomaAccount: PomaAccount): Flow<Poma> {
        return pomaRepoService.getByAccountId(pomaAccount.id!!)
    }

    override suspend fun rollNewPoma(pomaAccount: PomaAccount): Poma {
        val poma = generateRandPoma().copy(accountId = pomaAccount.id!!)

        return pomaRepoService.save(poma)
    }

    override fun changeMoney(pomaAccount: PomaAccount, money: Long) {
        TODO("Not yet implemented")
    }

    val pomaGenerator = PomaGenerator()

    private fun generateRandPoma(): Poma {
        val randomValue = Random.nextInt(0, 100)
        return if (randomValue < 74) {
            pomaGenerator.generateRandomPoma(1)
        } else if (randomValue < 94) {
            pomaGenerator.generateRandomPoma(2)
        } else {
            val poma =pomaGenerator.generateRandomPoma(3).copy(

            )
            return poma.copy(name = "al-${poma.name}")
        }
    }
}