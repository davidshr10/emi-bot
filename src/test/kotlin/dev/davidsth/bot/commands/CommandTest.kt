package dev.davidsth.bot.commands

import com.nhaarman.mockitokotlin2.*
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


object CommandTest: Spek({
    describe("onMessageReceived") {
        it("ignores messages without prefix") {
            val author = spy<User>()

            val message = spy<Message>()
            val event = mock<MessageReceivedEvent>()
            whenever(event.message).thenReturn(message)
            whenever(event.message.contentDisplay).thenReturn("content")
            whenever(event.message.author).thenReturn(author)

            val command = mock<Command>()

            command.onMessageReceived(event)

            verify(command, atLeastOnce()).handleMessage()
        }
    }
})
