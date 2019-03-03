package dev.davidsth.bot.listeners

import dev.davidsth.bot.engine.CommandExecutor
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class CommandListener : ListenerAdapter() {

    override fun onMessageReceived(e: MessageReceivedEvent) {
        CommandExecutor.handleMessage(e.message)
    }
}
