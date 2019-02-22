package dev.davidsth.bot

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class MessageHandlerListener : ListenerAdapter() {

    override fun onMessageReceived(e: MessageReceivedEvent) {
        if (e.message.contentDisplay.startsWith("!emi")) {
        }
    }
}