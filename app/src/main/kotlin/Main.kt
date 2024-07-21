
import com.example.extension.SampleExtension
import com.github.ya0igoddess.analytics.extension.AnalyticsExtension
import com.github.ya0igoddess.dbsync.extensions.DBSyncExtension
import com.github.ya0igoddess.presencemonitoring.extension.PresenceMonitorExtension
import com.github.ya0igoddess.tasks.extension.TasksExtension
import com.github.ya0igoddess.toxicityanalyzer.extension.ToxicityAnalyzerExtension
import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.mquiz.extension.MusicQuizExtension
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent

@OptIn(PrivilegedIntent::class)
suspend fun main() {
    val token = System.getenv("SKAARD_TOKEN")
    //val defaultGuildId = System.getenv("TEST_GUILD_ID")

    val bot = ExtensibleBot(token) {
        extensions {
            add(::DBSyncExtension)
            add(::SampleExtension)
            add(::PresenceMonitorExtension)
            add(::ToxicityAnalyzerExtension)
            add(::MusicQuizExtension)
            add(::TasksExtension)
            add(::AnalyticsExtension)
        }

        intents {
            +Intent.GuildMembers
            +Intent.GuildPresences
        }
    }
    bot.start()
}