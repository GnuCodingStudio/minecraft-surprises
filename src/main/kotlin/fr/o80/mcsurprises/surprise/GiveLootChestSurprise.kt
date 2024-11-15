package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import net.minecraft.server.command.ServerCommandSource

class GiveLootChestSurprise: Surprise {

    override val worldMessage: String = "Vous donnez des cadeaux sur votre stand ?"

    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.executeAsServer("give @r minecraft:chest 1")
    }
}