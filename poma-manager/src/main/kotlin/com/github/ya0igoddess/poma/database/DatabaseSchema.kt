package com.github.ya0igoddess.poma.database

import com.github.ya0igoddess.poma.extension.PomaManagerExt
import com.github.ya0igoddess.poma.model.Poma
import com.github.ya0igoddess.poma.model.PomaAccount
import com.github.ya0igoddess.dbsync.database.Members
import org.ufoss.kotysa.postgresql.PostgresqlTable

object PomaAccounts: PostgresqlTable<PomaAccount>("${PomaManagerExt.code}.poma_account") {
    val id = bigSerial(PomaAccount::id)
        .primaryKey()
    val money = bigInt(PomaAccount::money)
    val memberId = bigInt(PomaAccount::userId, "member_id")
        .foreignKey(Members.id)
}

object Pomas: PostgresqlTable<Poma>("${PomaManagerExt.code}.poma") {
    val id = bigSerial(Poma::id)
        .primaryKey()
    val accountId = bigInt(Poma::accountId, "account_id")
        .foreignKey(PomaAccounts.id)
    val name = varchar(Poma::name)
    val level = integer(Poma::level)
    val rarity = integer(Poma::rarity)
    val strength = integer(Poma::strength)
    val agility = integer(Poma::agility)
    val intelligence = integer(Poma::intelligence)
}

val tables = listOf(PomaAccounts, Pomas)