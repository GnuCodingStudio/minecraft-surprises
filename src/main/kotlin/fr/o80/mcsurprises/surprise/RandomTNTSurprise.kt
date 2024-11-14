package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.getRandomPlayers
import fr.o80.mcsurprises.sayAsServer
import net.minecraft.server.command.ServerCommandSource

class RandomTNTSurprise(
    private val seconds: Int,
    private val chance: Double
) : Surprise {
    override fun execute(context: CommandContext<ServerCommandSource>) {
        val ticks = seconds * 20
        context.sayAsServer("Tic Tac Tic Tac")
        context.getRandomPlayers(chance).forEach { player ->
            context.executeAsServer("summon tnt ${player.x} ${player.y} ${player.z} {Fuse:$ticks}")
        }
    }
}
