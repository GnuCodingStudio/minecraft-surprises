package fr.o80.testmc.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import fr.o80.testmc.StartResponse
import fr.o80.testmc.SurpriseManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import kotlin.time.Duration

class StartSurpriseCommand(
    private val delay: Duration = Duration.parse("PT5M"),
    private val millisInterval: Duration = Duration.parse("PT5M")
) : Command<ServerCommandSource> {

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        val startSurpriseResult = SurpriseManager.start(
            delay = delay.inWholeMilliseconds,
            millisInterval = millisInterval.inWholeMilliseconds,
            context
        )

        when (startSurpriseResult) {
            StartResponse.STARTED -> {
                context.source.sendFeedback({ Text.literal("Surprise started") }, true)
                return 1
            }

            StartResponse.ALREADY_STARTED -> {
                context.source.sendFeedback({ Text.literal("Surprise was already started") }, true)
                return -1
            }
        }
    }
}