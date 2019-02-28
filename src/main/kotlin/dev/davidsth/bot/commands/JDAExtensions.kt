package dev.davidsth.bot.commands

import net.dv8tion.jda.core.entities.User

val User.isNotBot: Boolean
    get() = !isBot