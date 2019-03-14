package dev.davidsth.bot.commands

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.entities.impl.UserImpl
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object PingCommandSpek : Spek({
    describe("handleMessage") {
        it("responds with pong") {
            val pingCommand = spyk<PingCommand>()
            val channel = mockk< TextChannel>()
            every { pingCommand.sendMessage(channel, any()) } returns Unit
            val author = mockk<UserImpl>()

            pingCommand.handleMessage(author,channel, "ping")

            verify{ pingCommand.sendMessage(channel, "pong") }
        }
    }
})