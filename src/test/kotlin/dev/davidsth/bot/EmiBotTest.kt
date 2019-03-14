package dev.davidsth.bot

import dev.davidsth.bot.commands.GithubCommand
import dev.davidsth.bot.commands.PingCommand
import net.oddpoet.expect.expect
import net.oddpoet.expect.extension.contain
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object EmiBotTest: Spek({
    describe("COMMANDS") {
        it("loads all the classes in commands package") {
            val commandNames = EmiBot.COMMANDS.values.map { it::class.simpleName }

            val availableCommands = listOf(PingCommand::class.simpleName, GithubCommand::class.simpleName)

            availableCommands.forEach {
                expect(commandNames).to.contain(it)
            }
        }
    }
})