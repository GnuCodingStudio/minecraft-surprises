package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.getRandomPlayers
import fr.o80.mcsurprises.sayAsServer
import net.minecraft.server.command.ServerCommandSource

class RandomHungerSurprise(
    private val chance: Double
) : Surprise {
    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.sayAsServer("Quelqu'un a commandé à manger ?")
        context.getRandomPlayers(chance).forEach { player ->
            context.executeAsServer("effect give ${player.name.string} hunger 60 1")
        }
    }
}
