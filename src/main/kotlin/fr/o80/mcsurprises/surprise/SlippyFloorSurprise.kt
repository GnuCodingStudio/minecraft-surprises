package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import net.minecraft.server.command.ServerCommandSource

class SlippyFloorSurprise(
    private val seconds: Int
): Surprise {

    override val worldMessage: String = "Ne pas faire un faux pas..."

    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.executeAsServer("effect give @a minecraft:slowness $seconds 100 true")
    }
}