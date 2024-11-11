package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.executeAsServer
import fr.o80.mcsurprises.getRandomPlayerName
import net.minecraft.server.command.ServerCommandSource
import kotlin.random.Random

class GiveOrTakeExperienceSurprise : Surprise {

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val player = context.getRandomPlayerName()
        val giveExperience = Random.nextBoolean()
        val quantity = Random.nextInt(100, 200)

        if (giveExperience) {
            logger.info("Give $quantity experience points to $player")
            context.executeAsServer("experience add $player $quantity")
        } else {
            logger.info("Take $quantity experience points from $player")
            context.executeAsServer("experience add $player -$quantity")
        }
    }
}
