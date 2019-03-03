package dev.davidsth.bot.engine

import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.entities.User

interface Command {
    fun handleMessage(author: User, channel: MessageChannel, message: String)
}

typealias CommandMap = Map<String, Command>

