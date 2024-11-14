package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import net.minecraft.server.command.ServerCommandSource
import kotlin.time.Duration

class HardDifficultySurprise(
    private val duration: Duration
) : Surprise {

    override val worldMessage: String = "Tu vois... c'est pas si dur"

    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.executeAsServer("difficulty hard")
        Thread.sleep(duration.inWholeMilliseconds)
        context.executeAsServer("difficulty normal")
    }
}
