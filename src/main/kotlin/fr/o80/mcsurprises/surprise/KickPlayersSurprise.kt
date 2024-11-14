package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.getRandomPlayers
import net.minecraft.server.command.ServerCommandSource
import kotlin.random.Random

class KickPlayersSurprise(
    private val maxPlayersToKick: Int
) : Surprise {

    override val worldMessage: String = "On n'a pas besoin de tout le monde"

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val players = context.getRandomPlayers()
        val maxIndex = Random.nextInt(1, maxPlayersToKick).coerceAtMost(players.size)

        players.subList(0, maxIndex).forEachIndexed { index, player ->
            logger.info("Kicking ${player.name.string}")
            context.executeAsServer("kick ${player.name.string} On n'a pas de job pour toi")
        }
    }
}
