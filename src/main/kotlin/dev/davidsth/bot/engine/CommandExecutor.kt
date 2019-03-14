package dev.davidsth.bot.engine

import dev.davidsth.bot.EmiBot
import dev.davidsth.bot.commands.PingCommand
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.User


object CommandExecutor {
    val commands: CommandMap
        get() {
            return mapOf("ping" to PingCommand())
        }

    fun handleMessage(message: Message) {
        val author = message.author
        val channel = message.channel
        val prefix = message.contentStripped.substring(0, 1)
        val trigger = message.contentStripped.substringBefore(" ").substring(1)
        val content = commandArguments(message.contentStripped)

        if (shouldHandleMessage(author, prefix, trigger)) {
            val commandHandler: CommandHandler = commands.getValue(trigger)
            commandHandler.handleMessage(author, channel, content)
        }
    }

    fun shouldHandleMessage(author: User, prefix: String, command: String): Boolean {
        return when {
            author.isNotBot &&
                prefix == EmiBot.prefix &&
                hasCommand(command) -> true
            else -> false
        }
    }

    private fun commandArguments(contentStripped: String?): String = when (contentStripped) {
            null -> ""
            else -> contentStripped.substringAfter(" ")
        }

    fun hasCommand(command: String): Boolean {
        return commands.keys.contains(command)
    }

}
