package com.github.ya0igoddess.dbsync.service.common

import kotlinx.coroutines.flow.Flow

interface CRUDService<T, ID> {

    suspend fun getById(id: ID): T

    suspend fun save(entity: T): T

    suspend fun update(entity: T): T

    suspend fun deleteById(id: ID): Long

    suspend fun delete(entity: T): Long

    suspend fun getAll(): Flow<T>

    suspend fun getAllByIds(ids: List<ID>): Flow<T>
}