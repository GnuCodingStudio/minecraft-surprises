package fr.o80.testmc

import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource

abstract class SurpriseAction {

    val worldMessage: String? = "Quelque chose de surprenant va se passer..."

    abstract fun execute(context: CommandContext<ServerCommandSource>)
}