package dev.davidsth.bot.commands

import dev.davidsth.bot.engine.Command
import dev.davidsth.bot.engine.CommandHandler
import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.entities.User

@Command("github")
class GithubCommand: CommandHandler {
    override fun handleMessage(author: User, channel: MessageChannel, message: String) {

    }

}