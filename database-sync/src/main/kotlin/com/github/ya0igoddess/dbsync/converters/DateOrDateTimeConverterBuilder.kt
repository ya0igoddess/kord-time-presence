package com.github.ya0igoddess.dbsync.converters

import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.converters.SingleConverter
import com.kotlindiscord.kord.extensions.commands.converters.builders.ConverterBuilder
import kotlinx.datetime.LocalDateTime

class DateOrDateTimeConverterBuilder : ConverterBuilder<LocalDateTime>() {
    override fun build(arguments: Arguments): SingleConverter<LocalDateTime> {
        return arguments.arg(
            displayName = name,
            description = description,
            converter = DateOrDateTimeConverter().withBuilder(this)
        )
    }
}

fun Arguments.dateOrDateTime(
    body: DateOrDateTimeConverterBuilder.() -> Unit
): SingleConverter<LocalDateTime> {
    val builder = DateOrDateTimeConverterBuilder()
    body(builder)
    builder.validateArgument()
    return builder.build(this)
}