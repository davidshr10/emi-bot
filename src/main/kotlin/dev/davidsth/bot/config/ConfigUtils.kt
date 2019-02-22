package dev.davidsth.bot.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import dev.davidsth.bot.EmiBot
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.Level
import java.util.logging.Logger


data class BotConfig(
    val name: String? = "EmiBot",
    val prefix: String? = "!",
    val token: String? = ""
)

object ConfigUtils {
    private val profileType = System.getProperty("profiles.active", "dev")

    private fun getFilePath(): Path? {

        val configFileName = when (profileType) {
            "prod" -> "application-prod.yml"
            else -> "application-default.yml"
        }

        val classLoader = javaClass.classLoader
        val appDefaultPath = File(classLoader.getResource(configFileName)!!.file)
        return appDefaultPath.toPath()
    }

    @JvmStatic
    fun load(): BotConfig {
        val path = getFilePath()
        val mapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing
        mapper.registerModule(KotlinModule()) // Enable Kotlin support

        return Files.newBufferedReader(path).use {

            mapper.readValue(it, BotConfig::class.java)
        }
    }

    @JvmStatic
    fun getLogger(): Logger {
        val level = System.getProperty("log.level", "info")

        val logger = Logger.getLogger(EmiBot::class.java.name)
        logger.level = when (level) {
            "config" -> Level.CONFIG
            else -> Level.INFO
        }
        return logger
    }
}

