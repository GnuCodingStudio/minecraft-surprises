package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.sayAsServer
import net.minecraft.server.command.ServerCommandSource

class LowGravityForAllSurprise(
    private val seconds: Int,
    private val levitation: Int = 1
) : Surprise {
    override fun execute(context: CommandContext<ServerCommandSource>) {
        context.sayAsServer("Ne prenez pas le jeu à la légère !")
        Thread.sleep(3000)
        context.executeAsServer("effect give @a levitation $seconds $levitation")
    }
}
