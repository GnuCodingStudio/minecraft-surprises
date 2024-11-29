package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import net.minecraft.server.command.ServerCommandSource
import kotlin.time.Duration

class LowGravityForAllSurprise(
    private val duration: Duration,
    private val levitation: Int = 1
) : Surprise {

    override val worldMessage: String = "Ne prenez pas le jeu à la légère !"

    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.executeAsServer("effect give @a levitation ${duration.inWholeSeconds} $levitation")
    }
}
