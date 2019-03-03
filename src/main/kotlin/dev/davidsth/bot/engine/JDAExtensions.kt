package dev.davidsth.bot.engine

import net.dv8tion.jda.core.entities.User

val User.isNotBot: Boolean
    get() = !isBot