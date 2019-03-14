package dev.davidsth.bot.engine

import net.oddpoet.expect.expect
import net.oddpoet.expect.extension.contain
import org.reflections.Reflections
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CommandBuilderTest: Spek({
    describe("load") {
        it("returns mapping of annotated classes in commands package") {
            val classesInPackage = Reflections("dev.davidsth.bot").getSubTypesOf(CommandHandler::class.java).map { it.simpleName }
            val commandMap = CommandBuilder().load().values.map { it::class.simpleName }

            classesInPackage.forEach {
                expect(commandMap).to.contain(it)
            }
        }
    }
})