package fr.o80.testmc.surprise

import com.mojang.brigadier.context.CommandContext
import fr.o80.testmc.Surprise
import fr.o80.testmc.executeAsServer
import fr.o80.testmc.getRandomPlayer
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.util.math.Vec3i
import org.slf4j.LoggerFactory
import kotlin.math.roundToInt

private val mobs = listOf(
    "zombie" to listOf(Vec3i(7, 2, -1), Vec3i(7, 2, 0), Vec3i(7, 2, 1)),
    "creeper" to listOf(Vec3i(10, 2, 10), Vec3i(10, 2, -10)),
    "spider" to listOf(Vec3i(10, 2, -1), Vec3i(10, 2, 1)),
    "skeleton" to listOf(Vec3i(10, 2, 10), Vec3i(10, 2, -10)),
    "cow" to listOf(Vec3i(5, 2, 5), Vec3i(5, 2, -5), Vec3i(-5, 2, -5), Vec3i(-5, 2, 5)),
    "goat" to listOf(Vec3i(5, 2, 5), Vec3i(5, 2, -5), Vec3i(-5, 2, -5), Vec3i(-5, 2, 5)),
    "chicken" to listOf(Vec3i(5, 2, 5), Vec3i(5, 2, -5), Vec3i(-5, 2, -5), Vec3i(-5, 2, 5)),
    "sheep" to listOf(Vec3i(5, 2, 5), Vec3i(5, 2, -5), Vec3i(-5, 2, -5), Vec3i(-5, 2, 5)),
)

class SummonSurprise : Surprise {

    val logger = LoggerFactory.getLogger(this::class.java)

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val player = context.getRandomPlayer() ?: return
        val playerPos = player.pos

        val (mob, positions) = mobs.random()
        positions.forEach { position ->
            logger.info("Summoning $mob near ${player.name.string}")
            val posX = playerPos.x.roundToInt() + position.x
            val posY = playerPos.y.roundToInt() + position.y
            val posZ = playerPos.z.roundToInt() + position.z
            context.executeAsServer("summon $mob $posX $posY $posZ {PersistenceRequired:1,CustomName:\"Surprise\"}")
        }
    }
}