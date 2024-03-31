package com.github.ya0igoddess.dbsync.converters

import com.kotlindiscord.kord.extensions.DiscordRelayedException
import com.kotlindiscord.kord.extensions.commands.Argument
import com.kotlindiscord.kord.extensions.commands.CommandContext
import com.kotlindiscord.kord.extensions.commands.converters.SingleConverter
import com.kotlindiscord.kord.extensions.parser.StringParser
import dev.kord.core.entity.interaction.OptionValue
import dev.kord.core.entity.interaction.StringOptionValue
import dev.kord.rest.builder.interaction.OptionsBuilder
import dev.kord.rest.builder.interaction.StringChoiceBuilder
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.char
import kotlinx.datetime.format.optional
import kotlinx.datetime.toLocalDateTime

class DateOrDateTimeConverter(
    override val signatureTypeString: String = "dbsync.localdatetime.defaulting"
) : SingleConverter<LocalDateTime>() {
    override suspend fun parse(parser: StringParser?, context: CommandContext, named: String?): Boolean {
        val arg: String = named ?: parser?.parseNext()?.data ?: return false
        parsed = parseStrToLocalDateTime(arg)
        return true
    }

    override suspend fun parseOption(context: CommandContext, option: OptionValue<*>): Boolean {
        val optionValue = (option as? StringOptionValue)?.value ?: return false
        parsed = parseStrToLocalDateTime(optionValue)
        return true
    }

    override suspend fun toSlashOption(arg: Argument<*>): OptionsBuilder {
        return StringChoiceBuilder(arg.displayName, arg.description).apply {
            maxLength = 40
            minLength = 8
        }
    }

    fun parseStrToLocalDateTime(string: String): LocalDateTime {
        val timeZone = TimeZone.currentSystemDefault()
        return DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET.parseOrNull(string)?.toLocalDateTime()
            ?: DATE_PATTERN.parseOrNull(string)?.toLocalDate()?.atStartOfDayIn(timeZone)?.toLocalDateTime(timeZone)
            ?: throw DiscordRelayedException("Failed to parse $string as Date or DateTime")
    }

    private companion object {
        val DATE_PATTERN = DateTimeComponents.Format {
            year(); optional { char('-') }; monthNumber(); optional { char('-') }; dayOfMonth()
        }
    }

}