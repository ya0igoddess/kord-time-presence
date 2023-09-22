package com.github.ya0igoddess.dbsync.database

import io.r2dbc.spi.ConnectionFactory

class UpdatableConnectionFactory(
    var factory: ConnectionFactory
): ConnectionFactory by factory