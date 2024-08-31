package fr.o80.testmc

import com.mojang.brigadier.context.CommandContext
import fr.o80.testmc.actions.FakeHelloSurpriseAction
import fr.o80.testmc.actions.GiveSomethingSurpriseAction
import fr.o80.testmc.actions.SummonSurpriseAction
import fr.o80.testmc.actions.TeleportSurpriseAction
import net.minecraft.server.command.ServerCommandSource
import org.slf4j.LoggerFactory
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.concurrent.scheduleAtFixedRate

private val actions = listOf(
    FakeHelloSurpriseAction(),
    TeleportSurpriseAction(),
    GiveSomethingSurpriseAction(),
    SummonSurpriseAction(),
)

object Surprise {

    val logger = LoggerFactory.getLogger(Surprise::class.java)

    var timer: Timer? = null

    fun start(delay: Long, millisInterval: Long, context: CommandContext<ServerCommandSource>): StartResponse {
        if (timer != null) {
            return StartResponse.ALREADY_STARTED
        }

        timer = Timer().also {
            it.scheduleAtFixedRate(delay, millisInterval) {
                logger.info("Triggering random surprise action...")
                val action = actions.random()

                val worldMessage = action.worldMessage
                if (worldMessage != null) {
                    context.sayAsServer(worldMessage)
                    Timer().schedule(10_000) { action.execute(context) }
                } else {
                    action.execute(context)
                }
            }
        }

        return StartResponse.STARTED
    }

    fun stop(): StopResponse {
        timer ?: return StopResponse.ALREADY_STOPPED

        timer?.cancel()
        timer = null
        return StopResponse.STOPPED
    }

}

enum class StartResponse { STARTED, ALREADY_STARTED }
enum class StopResponse { STOPPED, ALREADY_STOPPED }