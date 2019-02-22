package dev.davidsth.bot

import net.dv8tion.jda.core.events.ReadyEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class ReadyListener: ListenerAdapter() {

    override fun onReady(e: ReadyEvent) {
        val botName = e.jda.selfUser.name
        val discriminator = e.jda.selfUser.discriminator
        println("************************")
        println("$botName#$discriminator is now Online")
    }
}