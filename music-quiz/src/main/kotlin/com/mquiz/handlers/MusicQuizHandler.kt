package com.mquiz.handlers

import com.kotlindiscord.kord.extensions.commands.events.ChatCommandInvocationEvent
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.ephemeralSlashCommand
import com.mquiz.service.ISampleService
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import dev.kord.common.annotation.KordVoice
import dev.kord.core.behavior.channel.connect
import dev.kord.voice.AudioFrame
import kotlinx.coroutines.delay
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.time.Duration.Companion.seconds

val lavaPlayerManager = DefaultAudioPlayerManager().also {
    AudioSourceManagers.registerRemoteSources(it)
}

@OptIn(KordVoice::class)
suspend fun Extension.RegisterPlay() = ephemeralSlashCommand {
    name = "play_smth"
    description = "plays some music"

    action {
        val channel = member?.getVoiceStateOrNull()?.getChannelOrNull() ?: return@action
        val player = lavaPlayerManager.createPlayer()
        val query = "ytsearch: "
        val track = lavaPlayerManager.playTrack(query, player)

        val connection = channel.connect {
            audioProvider { AudioFrame.fromData(player.provide()?.data) }
        }

        respond { content = "Now playing" }

        delay(15.seconds)
        //connection.shutdown()
    }
}

class MusicQuizHandler(
        private val service: ISampleService
) {
    suspend fun handle(event: ChatCommandInvocationEvent) {
        service.doSomething()
    }
}

suspend fun DefaultAudioPlayerManager.playTrack(query: String, player: AudioPlayer): AudioTrack {
    val track = suspendCoroutine<AudioTrack> {
        this.loadItem(query, object : AudioLoadResultHandler {
            override fun trackLoaded(track: AudioTrack) {
                it.resume(track)
            }

            override fun playlistLoaded(playlist: AudioPlaylist) {
                it.resume(playlist.tracks.first())
            }

            override fun noMatches() {
                TODO()
            }

            override fun loadFailed(exception: FriendlyException?) {
                TODO()
            }
        })
    }

    player.playTrack(track)

    return track
}