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
    val memberId = bigInt(VoiceConnectionPeriod::memberId, "member_id")
        .foreignKey(Members.id)
    val guildId = bigInt(VoiceConnectionPeriod::channelId, "channel_id")
        .foreignKey(Channels.id)
    val start = timestamp(VoiceConnectionPeriod::start, "start_dttm")
    val end = timestamp(VoiceConnectionPeriod::end, "end_dttm")
}

val presenceMonitoringTables = listOf(ConnectionPeriods)