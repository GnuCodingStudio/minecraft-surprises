package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.getRandomPlayer
import net.minecraft.server.command.ServerCommandSource

class ArrowRainSurprise(
    private val waves: Int
) : Surprise {

    override val worldMessage: String = "Le ciel se couvre, il va pleuvoir ?"

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val player = context.getRandomPlayer() ?: return

        repeat(waves) {
            context.executeAsServer("execute at ${player.name.string} run summon arrow ~ ~10 ~ {Motion:[0.0,-5.0,0.0]}")
            Thread.sleep(2000)
        }
    }
}