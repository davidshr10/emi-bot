package org.davidsth.bot

fun main(args: Array<String>) {

    val config = Config("EmiBot", "")

    val emiBot = EmiBot(config)

    println("-----------------------------")
    println("\tInitializing ${config.name}")
    println("-----------------------------")
    emiBot.run()
}