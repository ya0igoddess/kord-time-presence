package com.example.repositories

import com.example.database.Pomas
import com.example.model.Poma
import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient

interface IPomaRepoService : CRUDService<Poma, Long>

class PomaCRUDRepo(
    sqlClient: R2dbcSqlClient
): KotysaLongCRUDRepository<Poma>(Pomas, Pomas.id, sqlClient)

class PomaRepoService(
    private val PomaCRUDRepo: PomaCRUDRepo
) : CRUDService<Poma, Long> by PomaCRUDRepo, IPomaRepoService