package fr.o80.testmc.actions

import com.mojang.brigadier.context.CommandContext
import fr.o80.testmc.SurpriseAction
import fr.o80.testmc.executeAsServer
import fr.o80.testmc.getRandomPlayerName
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.server.command.ServerCommandSource
import org.slf4j.LoggerFactory
import kotlin.random.Random

private val items = listOf<Pair<Item, IntProgression>>(
    Items.APPLE to (4..16 step 4),
    Items.STONE to (16..64 step 16),
    Items.COBBLESTONE to (16..64 step 16),
    Items.DIRT to (16..64 step 16),
    Items.SAND to (16..64 step 16),
    Items.OAK_LOG to (32..64 step 8),
    Items.OAK_WOOD to (32..64 step 8),
    Items.OAK_PLANKS to (32..64 step 16),
    Items.IRON_ORE to (8..16),
    Items.COPPER_ORE to (8..16),
    Items.GOLD_ORE to (8..16),
    Items.COAL to (8..16),
    Items.REDSTONE to (8..16),
    Items.REDSTONE_TORCH to (8..16),
    Items.LAPIS_LAZULI to (8..16),
    Items.EMERALD to (4..8),
    Items.DIAMOND to (1..4),
    Items.SPONGE to (1..1),
    Items.RED_WOOL to (3..3),
    Items.RED_BED to (1..1),
    Items.POPPY to (32..64),
    Items.DANDELION to (32..64),
    Items.SUGAR_CANE to (8..16 step 4),
    Items.OBSIDIAN to (4..16 step 2),
    Items.TORCH to (32..64 step 8),
    Items.CHEST to (1..4),
    Items.CRAFTING_TABLE to (1..4),
    Items.FURNACE to (1..4),
    Items.SNOWBALL to (64..64),
    Items.JUKEBOX to (1..1),
    Items.OAK_FENCE to (3..7),
    Items.OAK_FENCE_GATE to (1..3),
    Items.PUMPKIN to (1..3),
    Items.RED_CARPET to (8..16),
    Items.RED_STAINED_GLASS_PANE to (8..16),
    Items.OAK_BOAT to (2..4),
    Items.IRON_SWORD to (1..1),
    Items.IRON_SHOVEL to (1..1),
    Items.IRON_PICKAXE to (1..1),
    Items.IRON_AXE to (1..1),
    Items.IRON_HOE to (1..1),
    Items.WHEAT to (16..32),
    Items.BREAD to (1..5),
    Items.LEATHER_HELMET to (1..1),
    Items.LEATHER_CHESTPLATE to (1..1),
    Items.LEATHER_LEGGINGS to (1..1),
    Items.LEATHER_BOOTS to (1..1),
    Items.COOKED_PORKCHOP to (5..10),
    Items.OAK_SIGN to (2..4),
    Items.COOKIE to (1..2),
    Items.BAKED_POTATO to (1..2),
    Items.EXPERIENCE_BOTTLE to (4..8),
    Items.PLAYER_HEAD to (1..1),
    Items.FIREWORK_ROCKET to (10..20),
)

class GiveSomethingSurpriseAction : SurpriseAction() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val player = context.getRandomPlayerName()
        val (item, quantityRange) = items.random()

        val x = Random.nextInt((quantityRange.last - quantityRange.first) / quantityRange.step)
        val quantity = quantityRange.first + (x * quantityRange.step)

        logger.info("giving $quantity $item to $player")
        context.executeAsServer("give $player $item $quantity")
    }
}