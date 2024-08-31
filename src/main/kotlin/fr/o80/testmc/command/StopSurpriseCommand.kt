package fr.o80.testmc.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import fr.o80.testmc.StopResponse
import fr.o80.testmc.Surprise
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

class StopSurpriseCommand : Command<ServerCommandSource> {

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        val stopResponse = Surprise.stop()

        when (stopResponse) {
            StopResponse.STOPPED -> {
                context.source.sendFeedback({ Text.literal("Surprise stopped") }, true)
                return 1
            }

            StopResponse.ALREADY_STOPPED -> {
                context.source.sendFeedback({ Text.literal("Surprise wasn't started!") }, true)
                return -1
            }
        }

    }
}