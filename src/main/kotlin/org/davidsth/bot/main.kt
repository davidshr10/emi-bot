package org.davidsth.bot

fun main(args: Array<String>) {

    val emiBot = EmiBot()

    println("-----------------------------")
    println("\tInitializing ${emiBot.name}")
    println("-----------------------------")
    emiBot.run()
}