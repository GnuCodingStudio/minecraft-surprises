package fr.o80.mcsurprises

import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource
import org.slf4j.LoggerFactory

interface Surprise {

    val logger get() = LoggerFactory.getLogger(this::class.java)

    val worldMessage: String? get() = "Quelque chose de surprenant va se passer..."

    fun execute(context: CommandContext<ServerCommandSource>)
}
