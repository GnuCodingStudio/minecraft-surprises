package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.getRandomPlayer
import fr.o80.mcsurprises.sayAsServer
import net.minecraft.server.command.ServerCommandSource

class TeleportEverybodySurprise : Surprise {
    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.getRandomPlayer()?.let { player ->
            context.sayAsServer("${player.name} est vraiment une source d'inspiration pour toutes et tous")
            Thread.sleep(3000)
            context.executeAsServer("spreadplayers ${player.x} ${player.y} 10 50 false @a")
        }
    }
}
