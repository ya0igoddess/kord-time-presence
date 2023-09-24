package com.example.repositories

import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import com.example.database.SampleTable
import org.ufoss.kotysa.R2dbcSqlClient


interface ISampleRepoService : CRUDService<SampleModel, Long>

class SampleCRUDRepo(
    sqlClient: R2dbcSqlClient
): KotysaLongCRUDRepository<SampleModel>(SampleTable, SampleTable.id, sqlClient)

class DiscordConnectionPeriodRepoService(
    private val sampleCRUDRepo: SampleCRUDRepo
) : CRUDService<SampleModel, Long> by sampleCRUDRepo, ISampleRepoService