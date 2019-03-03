package dev.davidsth.bot.commands

import dev.davidsth.bot.engine.Command
import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.entities.User

class PingCommand : Command {

    override fun handleMessage(author: User, channel: MessageChannel, message: String) {
        sendMessage(channel, "pong")
    }

}
