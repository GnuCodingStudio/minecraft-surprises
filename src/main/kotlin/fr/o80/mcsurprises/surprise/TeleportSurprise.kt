package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.getRandomPlayers
import fr.o80.mcsurprises.sayAsServer
import net.minecraft.server.command.ServerCommandSource

class TeleportSurprise : Surprise {

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
