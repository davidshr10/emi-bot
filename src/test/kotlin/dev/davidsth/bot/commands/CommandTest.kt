package dev.davidsth.bot.commands

import dev.davidsth.bot.EmiBot
import io.mockk.*
import net.dv8tion.jda.core.entities.impl.DataMessage
import net.dv8tion.jda.core.entities.impl.UserImpl
import org.mockito.ArgumentMatchers.any
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


object CommandSpek: Spek({
    val command = spyk(Command())

    describe("onMessageReceived") {
        val emiBot = mockk<EmiBot>()
        every { emiBot.prefix } returns "!"

        it("ignores messages without prefix") {
            val author = spyk(UserImpl(1, any()))
            val message = mockk<DataMessage>()
            every { message.contentDisplay } returns "content"
            every { message.author } returns author

            command.handleMessageReceived(message)

            verify(exactly = 0) { command.handleMessage() }
        }

        it("ignores messages from bot users") {

            val author = spyk(UserImpl(1, any()))
            every { author.isBot } returns true

            val message = mockk<DataMessage>()
            every { message.contentDisplay } returns "content"
            every { message.author } returns author

            command.handleMessageReceived(message)

            verify(exactly = 0) { command.handleMessage() }
        }

        it("handles message with prefix and when user is not bot") {
            val author = spyk(UserImpl(1, any()))
            every { author.isBot } returns false

            val message = mockk<DataMessage>()
            every { message.contentDisplay } returns "!content"
            every { message.author } returns author

            command.handleMessageReceived(message)

            verify(exactly = 1) { command.handleMessage() }
        }
    }
})
