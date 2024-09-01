package fr.o80.testmc.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.testmc.Surprise
import fr.o80.testmc.executeAsServer
import fr.o80.testmc.getRandomPlayers
import fr.o80.testmc.sayAsServer
import net.minecraft.server.command.ServerCommandSource
import org.slf4j.LoggerFactory

class TeleportSurprise : Surprise {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val players = context.getRandomPlayers()
        val first = players.getOrNull(0)
        val second = players.getOrNull(1)

        if (first != null && second != null) {
            logger.info("Teleporting ${first.name} to ${second.name}")
            context.executeAsServer("tp ${first.name.string} ${second.name.string}")
        } else {
            val missingPlayers = 2 - players.size
            context.sayAsServer("Il manque $missingPlayers joueur(s) sur ce serveur !")
        }
    }
}