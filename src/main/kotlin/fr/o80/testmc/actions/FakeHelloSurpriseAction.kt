package fr.o80.testmc.actions

import com.mojang.brigadier.context.CommandContext
import fr.o80.testmc.SurpriseAction
import fr.o80.testmc.executeAsPlayer
import fr.o80.testmc.getRandomPlayer
import fr.o80.testmc.getRandomPlayerName
import net.minecraft.server.command.ServerCommandSource

class FakeHelloSurpriseAction : SurpriseAction() {
    override fun execute(context: CommandContext<ServerCommandSource>) {
        val firstPlayer = context.getRandomPlayer()
        val secondPlayer = context.getRandomPlayerName()

        if (firstPlayer != null && secondPlayer != null) {
            context.executeAsPlayer(
                firstPlayer,
                "tell $secondPlayer Wesh gros"
            )
        }
    }
}