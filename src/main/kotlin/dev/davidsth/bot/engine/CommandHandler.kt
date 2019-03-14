package dev.davidsth.bot.engine

import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.entities.User

interface CommandHandler {
    fun handleMessage(author: User, channel: MessageChannel, message: String)

    fun sendMessage(channel: MessageChannel, message: String) {
        channel.sendMessage(message).queue()
    }
}

typealias CommandMap = Map<String, CommandHandler>

