package fr.o80.testmc.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.testmc.Surprise
import fr.o80.testmc.executeAsServer
import fr.o80.testmc.getRandomPlayers
import net.minecraft.server.command.ServerCommandSource
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.random.Random

class KickPlayersSurprise(
    private val maxPlayersToKick: Int
) : Surprise {

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val players = context.getRandomPlayers()
        val maxIndex = Random.nextInt(1, maxPlayersToKick).coerceAtMost(players.size)

        players.subList(0, maxIndex).forEachIndexed { index, player ->
            Timer().schedule(2000) {
                logger.info("Kicking ${player.name.string}")
                context.executeAsServer("kick ${player.name.string} On n'a pas de mission pour toi")
            }
        }
    }
}