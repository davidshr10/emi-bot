package dev.davidsth.bot.engine

import dev.davidsth.bot.EmiBot
import io.mockk.*
import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.entities.impl.DataMessage
import net.dv8tion.jda.core.entities.impl.UserImpl
import net.oddpoet.expect.expect
import net.oddpoet.expect.extension.be
import org.mockito.ArgumentMatchers
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class MockCommand : Command {
    override fun handleMessage(author: User, channel: MessageChannel, message: String) {}
}

object CommandExecutorTest : Spek({
    var command = spyk<MockCommand>()

    val author = spyk(UserImpl(1, ArgumentMatchers.any()))
    val channel = mockk<MessageChannel>()
    val message = mockk<DataMessage>()

    beforeEachTest {
        command = spyk()
        mockkObject(CommandExecutor)
        mockkObject(EmiBot)
        every { CommandExecutor.commands } returns mapOf("content" to command)
        every { message.contentStripped } returns "!content"
        every { message.author } returns author
        every { message.channel } returns channel
        every { EmiBot.prefix } returns "!"
    }


    describe("handleMessage") {

        context("when shouldHandleMessage returns true") {
            it("calls handleMessage for command") {
                every { CommandExecutor.shouldHandleMessage(any(), any(), any()) } returns true

                CommandExecutor.handleMessage(message)

                verify(exactly = 1) { command.handleMessage(author, channel, any()) }
            }
        }

        context("when shouldHandleMessage returns false") {

            it("does not call handleMessage for command") {
                every { CommandExecutor.shouldHandleMessage(any(), any(), any()) } returns false

                CommandExecutor.handleMessage(message)

                verify(exactly = 0) { command.handleMessage(any(), any(), any()) }
            }
        }
    }

    describe("shouldHandleMessage") {
        mockkObject(EmiBot)
        every { EmiBot.prefix } returns "!"

        context("for bot user") {
            before {
                every { author.isBot } returns true
            }

            it("returns false") {
                val result = CommandExecutor.shouldHandleMessage(author, "!", "content")

                expect(result).to.be(false)
            }
        }

        context("for real user") {
            before {
                every { author.isBot } returns false
            }

            it("returns false when prefix does not match") {
                val result = CommandExecutor.shouldHandleMessage(author, "content", "content")

                expect(result).to.be(false)
            }

            it("returns false when hasCommand returns false") {
                every { CommandExecutor.hasCommand(any()) } returns false
                val result = CommandExecutor.shouldHandleMessage(author, "!content", "content")

                expect(result).to.be(false)
            }

            it("returns true when prefix matches and hasCommand is true") {
                every { CommandExecutor.hasCommand(any()) } returns true
                every { EmiBot.prefix } returns "!"

                val result = CommandExecutor.shouldHandleMessage(author, "!", "content")

                expect(result).to.be(true)
            }
        }
    }
})