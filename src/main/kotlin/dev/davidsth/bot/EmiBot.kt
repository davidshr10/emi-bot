package dev.davidsth.bot

import dev.davidsth.bot.config.BotConfig
import dev.davidsth.bot.config.ConfigUtils
import dev.davidsth.bot.listeners.CommandListener
import dev.davidsth.bot.listeners.ReadyListener
import net.dv8tion.jda.core.JDABuilder

object EmiBot {
    private val logger = ConfigUtils.getLogger()

    private val config: BotConfig = ConfigUtils.load()
    val name = config.name
    val prefix = config.prefix

    init {
        logger.config("$config")
    }

    fun run() {
        val jdaBuilder = JDABuilder(config.token).build()
        jdaBuilder.addEventListener(ReadyListener())
        jdaBuilder.addEventListener(CommandListener())
    }
}