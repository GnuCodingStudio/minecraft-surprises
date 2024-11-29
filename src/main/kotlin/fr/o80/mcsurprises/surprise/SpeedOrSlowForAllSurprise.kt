package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.getRandomPlayers
import net.minecraft.server.command.ServerCommandSource
import kotlin.random.Random
import kotlin.time.Duration

class SpeedOrSlowForAllSurprise(
    private val duration: Duration,
    private val speed: Int = 2,
    private val slowness: Int = 1
) : Surprise {

    override val worldMessage: String = "Serez-vous le lièvre ou la tortue ?"

    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.getRandomPlayers().forEach { player ->
            if (Random.nextInt(10) < 3) {
                context.executeAsServer("effect give ${player.name.string} slowness ${duration.inWholeSeconds} $slowness")
            } else {
                context.executeAsServer("effect give ${player.name.string} speed ${duration.inWholeSeconds} $speed")
            }
        }
    }
}
