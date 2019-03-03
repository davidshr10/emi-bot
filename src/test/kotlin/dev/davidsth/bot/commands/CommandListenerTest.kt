package dev.davidsth.bot.commands

import dev.davidsth.bot.engine.CommandExecutor
import dev.davidsth.bot.listeners.CommandListener
import io.mockk.*
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.entities.impl.DataMessage
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import org.mockito.ArgumentMatchers.any
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


object CommandListenerTest : Spek({
    describe("onMessageReceived") {
        val commandListener = CommandListener()
        val api = mockk<JDABuilder>()
        val message = mockk<DataMessage>()
        every { api.build() } returns any()
        every { message.idLong } returns 1
        every { message.contentStripped } returns "message"
        every { message.channel } returns any()

        mockkObject(CommandExecutor)
        every { CommandExecutor.handleMessage(message) } just Runs

        val event = spyk(MessageReceivedEvent(any(), 1, message))
        it("calls handleMessage on CommandExecutor") {
            commandListener.onMessageReceived(event)

            verify(exactly = 1) { CommandExecutor.handleMessage(any()) }
        }
    }
})
