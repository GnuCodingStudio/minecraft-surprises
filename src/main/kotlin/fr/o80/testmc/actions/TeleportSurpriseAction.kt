package fr.o80.testmc.actions

import com.mojang.brigadier.context.CommandContext
import fr.o80.testmc.SurpriseAction
import fr.o80.testmc.executeAsPlayer
import fr.o80.testmc.executeAsServer
import fr.o80.testmc.getRandomPlayer
import fr.o80.testmc.getRandomPlayerName
import fr.o80.testmc.getRandomPlayers
import fr.o80.testmc.sayAsServer
import net.minecraft.server.command.ServerCommandSource

class TeleportSurpriseAction : SurpriseAction() {
    override fun execute(context: CommandContext<ServerCommandSource>) {
        val players = context.getRandomPlayers()
        val first = players.getOrNull(0)
        val second = players.getOrNull(1)

        if (first != null && second != null) {
            context.executeAsServer(
                "tp ${first.name.string} ${second.name.string}"
            )
        } else {
            val missingPlayers = 2 - players.size
            context.sayAsServer("Il manque $missingPlayers joueur(s) sur ce serveur !")
        }
    }
}