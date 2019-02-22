package dev.davidsth.bot.commands

import net.dv8tion.jda.core.entities.Message

interface CommandInterface {

    fun handleMessage()
}
