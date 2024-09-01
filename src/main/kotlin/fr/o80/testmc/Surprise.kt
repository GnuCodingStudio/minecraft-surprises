package fr.o80.testmc

import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource

interface Surprise {

    val worldMessage: String? get() = "Quelque chose de surprenant va se passer..."

    fun execute(context: CommandContext<ServerCommandSource>)
}