package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.getRandomPlayers
import net.minecraft.server.command.ServerCommandSource

class FakeDeathSurprise(
    private val playersCount: Int
): Surprise {

    override val worldMessage: String = "Faut pas creuver comme ça..."

    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.getRandomPlayers().take(playersCount).forEach { player ->
            context.executeAsServer("effect give ${player.name.string} minecraft:wither 2 1")
        }
    }
}