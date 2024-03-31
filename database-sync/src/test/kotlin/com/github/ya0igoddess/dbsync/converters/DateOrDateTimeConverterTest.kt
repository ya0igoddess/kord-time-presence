package com.github.ya0igoddess.dbsync.converters

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import kotlinx.datetime.LocalDateTime

class DateOrDateTimeConverterTest : FunSpec({
    val converter = DateOrDateTimeConverter()

    test("Parses timestamp as usual") {
        val dttm = "2024-03-31T11:01:12Z"
        val result = converter.parseStrToLocalDateTime(dttm)
        result shouldBeEqual LocalDateTime(2024, 3, 31, 11, 1, 12)
    }

    test("Parses date as LocalDateTime in start of day") {
        val dttm = "2024-03-31"
        val result = converter.parseStrToLocalDateTime(dttm)
        result shouldBeEqual LocalDateTime(2024, 3, 31, 0, 0, 0)
    }
})
