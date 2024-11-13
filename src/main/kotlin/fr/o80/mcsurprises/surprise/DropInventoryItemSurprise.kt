package fr.o80.mcsurprises.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.Surprise
import fr.o80.mcsurprises.getRandomPlayer
import fr.o80.mcsurprises.sayAsServer
import net.minecraft.item.ItemStack
import net.minecraft.server.command.ServerCommandSource

class DropInventoryItemSurprise : Surprise {
    override fun execute(context: CommandContext<ServerCommandSource>) {
        val player = context.getRandomPlayer() ?: return
        val inventory = player.inventory
        val inventoryItems = inventory.main.toList()
            .mapIndexed { index, itemStack -> Pair(index, itemStack) }
            .filter { (_, item) -> item.count != 0 }

        inventoryItems.randomOrNull()?.let { (indexToDrop, itemToDrop) ->
            logger.info("Dropping $itemToDrop from ${player.name.string} inventory")
            context.sayAsServer("Quelqu'un n'a pas vraiment besoin de ça: ${itemToDrop.name}")
            inventory.main[indexToDrop] = ItemStack.EMPTY
        } ?: run {
            logger.info("Lucky boy/girl ${player.name.string}.. nothing to drop in their inventory")
            context.sayAsServer("${player.name} est déjà trop pauvre pour donner aux démunis")
        }
    }
}
