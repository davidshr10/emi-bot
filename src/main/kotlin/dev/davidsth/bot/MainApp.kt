package dev.davidsth.bot

fun main(args: Array<String>) {

    val emiBot = EmiBot.getInstance()

    println("-----------------------------")
    println("\tInitializing ${emiBot.name}")
    println("-----------------------------")
    emiBot.run()
}