package fr.o80.mcsurprises

import com.mojang.brigadier.context.CommandContext
import fr.o80.mcsurprises.surprise.FakeHelloSurprise
import fr.o80.mcsurprises.surprise.GiveOrTakeExperienceSurprise
import fr.o80.mcsurprises.surprise.GiveSomethingSurprise
import fr.o80.mcsurprises.surprise.HardDifficultySurprise
import fr.o80.mcsurprises.surprise.KickPlayersSurprise
import fr.o80.mcsurprises.surprise.LowGravityForAllSurprise
import fr.o80.mcsurprises.surprise.RandomHungerSurprise
import fr.o80.mcsurprises.surprise.RandomTNTSurprise
import fr.o80.mcsurprises.surprise.SpeedOrSlowForAllSurprise
import fr.o80.mcsurprises.surprise.SummonSurprise
import fr.o80.mcsurprises.surprise.TeleportEverybodySurprise
import fr.o80.mcsurprises.surprise.TeleportSurprise
import net.minecraft.server.command.ServerCommandSource
import org.slf4j.LoggerFactory
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.time.Duration.Companion.minutes

private val actions = listOf(
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
    RandomTNTSurprise(chance = .2, seconds = 2)
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
