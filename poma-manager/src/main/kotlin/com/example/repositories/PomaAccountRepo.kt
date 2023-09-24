package com.example.repositories

import com.example.database.PomaAccounts
import com.example.model.PomaAccount
import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient

interface IPomaAccountRepoService : CRUDService<PomaAccount, Long>

class PomaAccountCRUDRepo(
    sqlClient: R2dbcSqlClient
): KotysaLongCRUDRepository<PomaAccount>(PomaAccounts, PomaAccounts.id, sqlClient)

class PomaAccountRepoService(
    private val pomaAccountCRUDRepo: PomaAccountCRUDRepo
) : CRUDService<PomaAccount, Long> by pomaAccountCRUDRepo, IPomaAccountRepoService