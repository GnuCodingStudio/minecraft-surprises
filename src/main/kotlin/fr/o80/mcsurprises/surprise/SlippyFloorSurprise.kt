package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import net.minecraft.server.command.ServerCommandSource
import kotlin.time.Duration

class SlippyFloorSurprise(
    private val duration: Duration
): Surprise {

    override val worldMessage: String = "Ne pas faire un faux pas..."

    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.executeAsServer("effect give @a minecraft:slowness ${duration.inWholeSeconds} 100 true")
    }
}