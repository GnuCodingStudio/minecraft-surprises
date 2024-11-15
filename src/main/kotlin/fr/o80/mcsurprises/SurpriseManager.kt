package fr.o80.mcsurprises

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.surprise.*
import net.minecraft.server.command.ServerCommandSource
import org.slf4j.LoggerFactory
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.time.Duration.Companion.minutes

private val surprises = listOf(
    FakeHelloSurprise(),
    TeleportSurprise(),
    GiveSomethingSurprise(),
    SummonSurprise(),
    KickPlayersSurprise(maxPlayersToKick = 2),
    GiveOrTakeExperienceSurprise(),
    LowGravityForAllSurprise(seconds = 60),
    SpeedOrSlowForAllSurprise(seconds = 60),
    TeleportEverybodySurprise(),
    HardDifficultySurprise(duration = 4.minutes),
    RandomHungerSurprise(chance = .2),
    RandomTNTSurprise(chance = .2, seconds = 2),
    DrunkSurprise()
)

object SurpriseManager {

    private val logger = LoggerFactory.getLogger(SurpriseManager::class.java)

    private var timer: Timer? = null

    fun start(delay: Long, millisInterval: Long, context: CommandContext<ServerCommandSource>): StartResponse {
        if (timer != null) {
            return StartResponse.ALREADY_STARTED
        }

        timer = Timer().also {
            it.scheduleAtFixedRate(delay, millisInterval) {
                logger.info("Triggering random surprise action...")
                executeSurprise(surprises.random(), context)
            }
        }

        return StartResponse.STARTED
    }

    private fun executeSurprise(
        surprise: Surprise,
        context: CommandContext<ServerCommandSource>
    ) {
        try {
            val worldMessage = surprise.worldMessage
            if (worldMessage != null) {
                context.sayAsServer(worldMessage)
                Timer().schedule(10_000) { surprise.execute(context) }
            } else {
                surprise.execute(context)
            }
        } catch (e: Exception) {
            logger.error("Surprise failed while executing: ${surprise::class.simpleName}", e)
        }
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
