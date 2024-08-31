package fr.o80.testmc.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import fr.o80.testmc.actions.FakeHelloSurpriseAction
import net.minecraft.server.command.ServerCommandSource
import org.slf4j.LoggerFactory

class TestCommand : Command<ServerCommandSource> {

    val logger = LoggerFactory.getLogger(TestCommand::class.java)

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        logger.info("Go Go Go!")
        FakeHelloSurpriseAction().execute(context)

        return 1
    }
}