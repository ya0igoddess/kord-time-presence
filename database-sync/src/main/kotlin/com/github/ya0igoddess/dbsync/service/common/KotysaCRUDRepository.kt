package com.github.ya0igoddess.dbsync.service.common

import kotlinx.coroutines.flow.Flow
import org.ufoss.kotysa.*
import org.ufoss.kotysa.columns.LongDbBigIntColumnNotNull
import org.ufoss.kotysa.columns.LongDbBigSerialColumnNotNull

abstract class KotysaAbstractCRUDRepository<T : Any, ID : Any>(
        open val table: AbstractTable<T>,
        open val tableIdColumn: Column<T, ID>,
        protected val sqlClient: R2dbcSqlClient,
) : CRUDService<T, ID> {
    override suspend fun getAll(): Flow<T> = sqlClient selectAllFrom table

    override suspend fun save(entity: T): T = sqlClient insertAndReturn entity

    override suspend fun update(entity: T): T {
        throw NotImplementedError()
    }
}

abstract class KotysaLongCRUDRepository<T: Any>(
        table: AbstractTable<T>,
        override val tableIdColumn: LongColumnNotNull<T>,
        sqlClient: R2dbcSqlClient,
): KotysaAbstractCRUDRepository<T, Long>(table, tableIdColumn, sqlClient) {
    override suspend fun getById(id: Long): T? = (sqlClient select table from table where tableIdColumn eq id).fetchFirstOrNull()

    override suspend fun save(entity: T): T = sqlClient insertAndReturn entity

    override suspend fun deleteById(id: Long): Long = (sqlClient deleteFrom table where tableIdColumn eq id).execute()

    override suspend fun getAllByIds(ids: List<Long>): Flow<T> = (sqlClient
            select table from table
            where tableIdColumn `in` ids)
            .fetchAll()

    override suspend fun delete(entity: T): Long = deleteById(requireNotNull(tableIdColumn.entityGetter.invoke(entity)))
}