package com.github.ya0igoddess.presencemonitoring.handlers

import com.github.ya0igoddess.dbsync.model.discord.lvalue
import com.github.ya0igoddess.presencemonitoring.service.IVoiceConnectionRegistry
import dev.kord.common.entity.Snowflake
import dev.kord.common.entity.optional.OptionalSnowflake
import dev.kord.core.cache.data.VoiceStateData
import dev.kord.core.entity.VoiceState
import dev.kord.core.event.user.VoiceStateUpdateEvent
import io.kotest.core.spec.style.FunSpec
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify

class VoiceStatusChangeHandlerTest : FunSpec() {
    private fun createBaseVoice(data: VoiceStateData) = VoiceState(
        data = data,
        mockk(),
        mockk()
    )

    init {
        val registry = mockk<IVoiceConnectionRegistry>(relaxed = true)
        val handler = VoiceStatusChangeHandler(registry)

        //It's hard to make data class stub :(
        val baseData = VoiceStateData(
            channelId = mockk(),
            memberId = mockk(),
            userId = mockk(),
            guildId = mockk(),
            deaf = false,
            mute = false,
            requestToSpeakTimestamp = mockk(),
            selfDeaf = false,
            selfMute = false,
            selfStream = mockk(),
            selfVideo = false,
            sessionId = "123",
            suppress = false,
        )

        val defaultMemberId = mockk<OptionalSnowflake>(relaxed = true)
        val defaultChannelId = mockk<Snowflake>(relaxed = true)
        val sessionId = "123"
        val expectedMember = defaultMemberId.value!!.lvalue
        val expectedChannel = defaultChannelId.lvalue

        test("Reacts on channel enter") {
            val event = VoiceStateUpdateEvent(
                old = null,
                state = createBaseVoice(
                    baseData.copy(
                        sessionId = sessionId,
                        memberId = defaultMemberId,
                        channelId = defaultChannelId,
                    )
                ),
                0,
                mockk()
            )
            handler.handle(event)
            verify { registry.registerNewConnection(sessionId, expectedMember, expectedChannel) }
            confirmVerified(registry)
        }

        test("React on channel leave") {
            val event = VoiceStateUpdateEvent(
                old = createBaseVoice(
                    baseData.copy(
                        sessionId = sessionId,
                        memberId = defaultMemberId,
                        channelId = defaultChannelId
                    )
                ),
                state = createBaseVoice(
                    baseData.copy(
                        memberId = defaultMemberId,
                        channelId = null
                    )
                ),
                0,
                mockk()
            )
            handler.handle(event)
            coVerify { registry.closeConnection(sessionId) }
            confirmVerified(registry)
        }
    }
}
