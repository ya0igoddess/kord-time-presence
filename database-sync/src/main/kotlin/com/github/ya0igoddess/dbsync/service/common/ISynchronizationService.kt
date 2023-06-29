package com.github.ya0igoddess.dbsync.service.common

interface ISynchronizationService<INT, EXT> {
    suspend fun getByExternalEntity(externalEntity: EXT): INT?

    suspend fun createFromExternalEntity(externalEntity: EXT): INT

    suspend fun getOrCreateFromExternal(externalEntity: EXT): INT {
        return getByExternalEntity(externalEntity) ?: createFromExternalEntity(externalEntity)
    }

    suspend fun updateWithExternalEntity(internalEntity: INT, externalEntity: EXT): INT
}