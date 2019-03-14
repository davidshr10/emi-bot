package dev.davidsth.bot.engine

import org.reflections.Reflections
import kotlin.reflect.full.createInstance

class CommandBuilder {

    fun load(): Map<String, CommandHandler> {
        val reflections = Reflections("dev.davidsth.bot")
        val classes = reflections.getTypesAnnotatedWith(Command::class.java)
        return classes.associate {
            val annot = it.getAnnotation(Command::class.java)
            annot.name to (it.kotlin.createInstance() as CommandHandler)
        }
    }
}
