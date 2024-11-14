package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.sayAsServer
import net.minecraft.server.command.ServerCommandSource
import kotlin.time.Duration

class HardDifficultySurprise(
    private val duration: Duration
) : Surprise {
    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.sayAsServer("Tu vois... c'est pas si dur")
        context.executeAsServer("difficulty hard")
        Thread.sleep(duration.inWholeMilliseconds)
        context.executeAsServer("difficulty normal")
    }
}
