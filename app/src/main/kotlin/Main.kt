import com.github.ya0igoddess.dbsync.extensions.DBSyncExtension
import com.kotlindiscord.kord.extensions.ExtensibleBot

suspend fun main(args: Array<String>) {
    val token = System.getenv("SKAARD_TOKEN")
    val defaultGuildId = System.getenv("TEST_GUILD_ID")

    val bot = ExtensibleBot(token) {
        extensions {
            add(::DBSyncExtension)
        }

        applicationCommands {
            defaultGuild(defaultGuildId)
        }
    }
    bot.start()
}