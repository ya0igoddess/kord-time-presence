package com.mquiz.database

import com.github.ya0igoddess.dbsync.database.Members
import com.mquiz.extension.MusicQuizExtension
import com.mquiz.model.SampleModel
import org.ufoss.kotysa.postgresql.PostgresqlTable

object SampleTable : PostgresqlTable<SampleModel>(
        "${MusicQuizExtension.code}.sample"
) {
    val id = bigSerial(SampleModel::id).primaryKey()
    val memberId = bigInt(SampleModel::memberId, "member_id")
            .foreignKey(Members.id)
}

val musicQuizModule = listOf(SampleTable)