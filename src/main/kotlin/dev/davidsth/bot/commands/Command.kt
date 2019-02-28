package dev.davidsth.bot.commands

import dev.davidsth.bot.EmiBot
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class Command : ListenerAdapter(), CommandInterface {

    val EMIBOT = EmiBot.getInstance()

    override fun onMessageReceived(e: MessageReceivedEvent) {
        handleMessageReceived(e.message)
    }

    fun handleMessageReceived(message: Message) {
        val prefix = message.contentDisplay.substring(0, 1)

        val author = message.author
        if (shouldHandleMessage(prefix, author)) {
            handleMessage()
        }
    }

    override fun handleMessage() {

    }

    private fun shouldHandleMessage(prefix: String, author: User): Boolean {
        return when {
            ( author.isNotBot && prefix == EMIBOT.prefix ) -> true
            else -> false
        }
    }

}
