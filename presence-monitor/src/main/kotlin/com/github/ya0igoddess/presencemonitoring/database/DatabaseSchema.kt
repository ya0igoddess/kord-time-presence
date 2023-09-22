package com.github.ya0igoddess.presencemonitoring.database

import com.github.ya0igoddess.dbsync.database.Channels
import com.github.ya0igoddess.dbsync.database.Members
import com.github.ya0igoddess.presencemonitoring.extension.PresenceMonitorExtension
import com.github.ya0igoddess.presencemonitoring.model.VoiceConnectionPeriod
import org.ufoss.kotysa.postgresql.PostgresqlTable

object ConnectionPeriods: PostgresqlTable<VoiceConnectionPeriod>(
    "${PresenceMonitorExtension.code}.connection_period"
) {
    val id = bigSerial(VoiceConnectionPeriod::id).primaryKey()
    val memberId = bigInt(VoiceConnectionPeriod::memberId, "member")
        .foreignKey(Members.id)
    val guildId = bigInt(VoiceConnectionPeriod::channelId, "channel")
        .foreignKey(Channels.id)
    val start = timestamp(VoiceConnectionPeriod::start)
    val end = timestamp(VoiceConnectionPeriod::end)
}

val presenceMonitoringTables = listOf(ConnectionPeriods)