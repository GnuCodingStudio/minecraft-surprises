package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsPlayer
import fr.o80.mcsurprises.getRandomPlayer
import fr.o80.mcsurprises.getRandomPlayerName
import net.minecraft.server.command.ServerCommandSource

class FakeHelloSurprise : Surprise {

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
