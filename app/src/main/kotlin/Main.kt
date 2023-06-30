import com.github.ya0igoddess.dbsync.DBSyncExtension
import com.kotlindiscord.kord.extensions.ExtensibleBot

suspend fun main(args: Array<String>) {
    val token = System.getenv("SKAARD_TOKEN")
    val bot = ExtensibleBot(token) {
        extensions {
            add(::DBSyncExtension)
        }
    }
    bot.start()
}