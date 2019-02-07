package org.davidsth.bot

import net.dv8tion.jda.core.JDABuilder

data class Config(val name: String, val token: String)

@Target(AnnotationTarget.CLASS)
annotation class ConfigProperties {

}


class EmiBot(val config: Config) {

    init {
        config
    }

    val prefix: String = "!emi"

    fun run() {
        val jdaBuilder = JDABuilder(config.token).build()

        jdaBuilder.addEventListener(ReadyListener())
        jdaBuilder.addEventListener(MessageHandlerListener())
    }

}