package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.getRandomPlayers
import fr.o80.mcsurprises.sayAsServer
import net.minecraft.server.command.ServerCommandSource
import kotlin.random.Random

class SpeedOrSlowForAllSurprise(
    private val seconds: Int,
    private val speed: Int = 2,
    private val slowness: Int = 1
) : Surprise {
    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.sayAsServer("Serez-vous le lièvre ou la tortue ?")
        Thread.sleep(3000)
        context.getRandomPlayers().forEach { player ->
            if (Random.nextInt(10) < 3) {
                context.executeAsServer("effect give ${player.name} slowness $seconds $slowness")
            } else {
                context.executeAsServer("effect give ${player.name} speed $seconds $speed")
            }
        }
    }
}
