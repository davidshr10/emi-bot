package dev.davidsth.bot

import net.dv8tion.jda.core.JDABuilder
import dev.davidsth.bot.config.BotConfig
import dev.davidsth.bot.config.ConfigUtils

class EmiBot private constructor() {
    private val logger = ConfigUtils.getLogger()

    val name = config.name
    val prefix = config.prefix

    init {
        logger.config("$config")
    }

    companion object Factory {
        private val config: BotConfig = ConfigUtils.load()
        fun getInstance(): EmiBot = EmiBot()
    }

    fun run() {

        val jdaBuilder = JDABuilder(config.token).build()
        jdaBuilder.addEventListener(ReadyListener())
        jdaBuilder.addEventListener(MessageHandlerListener())
    }
}