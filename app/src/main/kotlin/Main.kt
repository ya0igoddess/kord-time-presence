import com.example.extension.PomaManagerExt
import com.github.ya0igoddess.dbsync.extensions.DBSyncExtension
import com.github.ya0igoddess.presencemonitoring.extension.PresenceMonitorExtension
import com.kotlindiscord.kord.extensions.ExtensibleBot
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent

@OptIn(PrivilegedIntent::class)
suspend fun main(args: Array<String>) {
    val token = System.getenv("SKAARD_TOKEN")
    val defaultGuildId = System.getenv("TEST_GUILD_ID")

    val bot = ExtensibleBot(token) {
        extensions {
            add(::DBSyncExtension)
            add(::PresenceMonitorExtension)
            add(::PomaManagerExt)
        }

        applicationCommands {
            defaultGuild(defaultGuildId)
        }

        intents {
            +Intent.GuildMembers
            +Intent.GuildPresences
        }
    }
    bot.start()
}