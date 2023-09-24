package com.example.database

import com.github.ya0igoddess.dbsync.database.Members
import com.example.extension.PresenceMonitorExtension
import com.example.model.SampleModel
import org.ufoss.kotysa.postgresql.PostgresqlTable

object SampleTable: PostgresqlTable<SampleModel>(
    "${PresenceMonitorExtension.code}.sample"
) {
    val id = bigSerial(SampleModel::id).primaryKey()
    val memberId = bigInt(SampleModel::memberId, "member_id")
        .foreignKey(Members.id)
}

val sampleTables = listOf(SampleTable)