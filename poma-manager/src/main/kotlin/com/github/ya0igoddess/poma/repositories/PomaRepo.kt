package com.github.ya0igoddess.poma.repositories

import com.github.ya0igoddess.poma.database.Pomas
import com.github.ya0igoddess.poma.model.Poma
import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import kotlinx.coroutines.flow.Flow
import org.ufoss.kotysa.R2dbcSqlClient

interface IPomaRepoService : CRUDService<Poma, Long> {
    fun getByAccountId(id: Long): Flow<Poma>
}

class PomaCRUDRepo(
    sqlClient: R2dbcSqlClient
): KotysaLongCRUDRepository<Poma>(Pomas, Pomas.id, sqlClient) {
    fun getByAccountId(accountId: Long): Flow<Poma> {
        return (sqlClient select Pomas from Pomas where Pomas.accountId eq accountId).fetchAll()
    }
}

class PomaRepoService(
    private val PomaCRUDRepo: PomaCRUDRepo
) : CRUDService<Poma, Long> by PomaCRUDRepo, IPomaRepoService {
    override fun getByAccountId(id: Long): Flow<Poma> {
        return PomaCRUDRepo.getByAccountId(id)
    }
}