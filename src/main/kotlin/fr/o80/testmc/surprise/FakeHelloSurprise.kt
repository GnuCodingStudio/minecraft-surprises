package fr.o80.testmc.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.testmc.Surprise
import fr.o80.testmc.executeAsPlayer
import fr.o80.testmc.getRandomPlayer
import fr.o80.testmc.getRandomPlayerName
import net.minecraft.server.command.ServerCommandSource
import org.slf4j.LoggerFactory

class FakeHelloSurprise : Surprise {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val firstPlayer = context.getRandomPlayer()
        val secondPlayer = context.getRandomPlayerName()

        if (firstPlayer != null && secondPlayer != null) {
            logger.info("Faking hello of $firstPlayer to $secondPlayer")
            context.executeAsPlayer(
                firstPlayer,
                "tell $secondPlayer Wesh gros"
            )
        }
    }
}