package com.github.ya0igoddess.dbsync.database

import kotlinx.coroutines.flow.Flow
import org.ufoss.kotysa.*
import org.ufoss.kotysa.core.r2dbc.transaction.R2dbcTransaction
import java.math.BigDecimal

class UpdatableSqlClient(
    var r2dbcSqlClient: PostgresqlR2dbcSqlClient
): PostgresqlR2dbcSqlClient {
    override suspend fun <T : Any> createTable(table: Table<T>) {
        r2dbcSqlClient.createTable(table)
    }

    override suspend fun <T : Any> createTableIfNotExists(table: Table<T>) {
        r2dbcSqlClient.createTableIfNotExists(table)
    }

    override fun <T : Any> deleteFrom(table: Table<T>): CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T> {
        return r2dbcSqlClient.deleteFrom(table)
    }

    override suspend fun <T : Any> insert(row: T) {
        r2dbcSqlClient.insert(row)
    }

    override suspend fun <T : Any> insert(vararg rows: T) {
        r2dbcSqlClient.insert(*rows)
    }

    override suspend fun <T : Any> insertAndReturn(row: T): T {
        return r2dbcSqlClient.insertAndReturn(row)
    }

    override fun <T : Any> insertAndReturn(vararg rows: T): Flow<T> {
        return r2dbcSqlClient.insertAndReturn(*rows)
    }

    override fun <T : Any, U : Any> select(column: Column<T, U>): CoroutinesSqlClientSelect.FirstSelect<U> {
        return r2dbcSqlClient.select(column)
    }

    override fun <T : Any> select(dsl: SqlClientSubQuery.Scope.() -> SqlClientSubQuery.Return<T>): CoroutinesSqlClientSelect.FirstSelect<T> {
        return r2dbcSqlClient.select(dsl)
    }

    override fun <T : Any> select(table: Table<T>): CoroutinesSqlClientSelect.FirstSelect<T> {
        return r2dbcSqlClient.select(table)
    }

    override fun <T : Any> selectAndBuild(dsl: (ValueProvider) -> T): CoroutinesSqlClientSelect.Fromable<T> {
        return r2dbcSqlClient.selectAndBuild(dsl)
    }

    override fun <T : Any, U : Any> selectAvg(column: NumericColumn<T, U>): CoroutinesSqlClientSelect.FirstSelect<BigDecimal> {
        return r2dbcSqlClient.selectAvg(column)
    }

    override fun <T : Any> selectCaseWhenExists(dsl: SqlClientSubQuery.SingleScope.() -> SqlClientSubQuery.Return<T>): CoroutinesSqlClientSelect.SelectCaseWhenExistsFirst<T> {
        return r2dbcSqlClient.selectCaseWhenExists(dsl)
    }

    override fun selectCount(): CoroutinesSqlClientSelect.Fromable<Long> {
        return r2dbcSqlClient.selectCount()
    }

    override fun <T : Any> selectCount(column: Column<*, T>): CoroutinesSqlClientSelect.FirstSelect<Long> {
        return r2dbcSqlClient.selectCount(column)
    }

    override fun <T : Any, U : Any> selectDistinct(column: Column<T, U>): CoroutinesSqlClientSelect.FirstSelect<U> {
        return r2dbcSqlClient.selectDistinct(column)
    }

    override fun <T : Any, U : Any> selectMax(column: MinMaxColumn<T, U>): CoroutinesSqlClientSelect.FirstSelect<U> {
        return r2dbcSqlClient.selectDistinct(column)
    }

    override fun <T : Any, U : Any> selectMin(column: MinMaxColumn<T, U>): CoroutinesSqlClientSelect.FirstSelect<U> {
        return r2dbcSqlClient.selectMin(column)
    }

    override fun <T : Any> selectStarFrom(dsl: SqlClientSubQuery.Scope.() -> SqlClientSubQuery.Return<T>): CoroutinesSqlClientSelect.From<T> {
        return r2dbcSqlClient.selectStarFrom(dsl)
    }

    override fun <T : Any, U : Any> selectSum(column: WholeNumberColumn<T, U>): CoroutinesSqlClientSelect.FirstSelect<Long> {
        return r2dbcSqlClient.selectSum(column)
    }

    override fun <T : Any> update(table: Table<T>): CoroutinesSqlClientDeleteOrUpdate.Update<T> {
        return r2dbcSqlClient.update(table)
    }

    override suspend fun <U> transactional(block: suspend (R2dbcTransaction) -> U): U? {
        return r2dbcSqlClient.transactional(block)
    }
}