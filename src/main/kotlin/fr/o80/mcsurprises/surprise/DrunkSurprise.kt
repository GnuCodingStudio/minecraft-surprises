package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.getRandomPlayers
import net.minecraft.server.command.ServerCommandSource
import kotlin.time.Duration

class DrunkSurprise(
    private val duration: Duration
) : Surprise {

    override val worldMessage: String = "Qui sera Sam ce soir ?"

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val players = context.getRandomPlayers()
        players.drop(1).forEach { player ->
            context.executeAsServer("effect give ${player.name.string} nausea ${duration.inWholeSeconds} 1")
        }
    }
}