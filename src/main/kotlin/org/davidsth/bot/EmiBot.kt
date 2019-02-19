package org.davidsth.bot

import net.dv8tion.jda.core.JDABuilder
import org.davidsth.bot.config.BotConfig
import org.davidsth.bot.config.ConfigUtils

class EmiBot {
    private val logger = ConfigUtils.getLogger()

    private val config: BotConfig = ConfigUtils.load()
    val name = config.name

    init {
        logger.config("${this.config}")
    }

    fun run() {

        val jdaBuilder = JDABuilder(config.token).build()
        jdaBuilder.addEventListener(ReadyListener())
        jdaBuilder.addEventListener(MessageHandlerListener())

    }

}