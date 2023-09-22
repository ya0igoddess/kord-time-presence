package com.github.ya0igoddess.presencemonitoring.repositories

import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import com.github.ya0igoddess.presencemonitoring.database.ConnectionPeriods
import com.github.ya0igoddess.presencemonitoring.model.VoiceConnectionPeriod
import org.ufoss.kotysa.R2dbcSqlClient


interface IDiscordConnectionPeriodRepoService : CRUDService<VoiceConnectionPeriod, Long>

class VoiceConnectionPeriodCRUDRepo(
    sqlClient: R2dbcSqlClient
): KotysaLongCRUDRepository<VoiceConnectionPeriod>(ConnectionPeriods, ConnectionPeriods.id, sqlClient)

class DiscordConnectionPeriodRepoService(
    private val connectionCRUDRepo: VoiceConnectionPeriodCRUDRepo
) : CRUDService<VoiceConnectionPeriod, Long> by connectionCRUDRepo, IDiscordConnectionPeriodRepoService