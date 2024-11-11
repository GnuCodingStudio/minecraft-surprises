package fr.o80.mcsurprises

import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.network.ServerPlayerEntity


fun CommandContext<ServerCommandSource>.getRandomPlayer(): ServerPlayerEntity? =
    this.source.world.players.randomOrNull()

fun CommandContext<ServerCommandSource>.getRandomPlayers(): List<ServerPlayerEntity> =
    this.source.world.players.shuffled()

fun CommandContext<ServerCommandSource>.getRandomPlayerName(): String? =
    this.source.world.players.randomOrNull()?.name?.string

fun CommandContext<ServerCommandSource>.executeAsPlayer(player: ServerPlayerEntity, command: String) {
    val commandManager = this.source.server.commandManager
    val playerSource = player.commandSource
    commandManager.executeWithPrefix(playerSource, command)
}

fun CommandContext<ServerCommandSource>.executeAsServer(command: String) {
    val commandManager = this.source.server.commandManager
    val commandSource = this.source.server.commandSource
    commandManager.executeWithPrefix(commandSource, command)
}

fun CommandContext<ServerCommandSource>.sayAsServer(message: String) {
    val commandManager = this.source.server.commandManager
    val commandSource = this.source.server.commandSource
    commandManager.executeWithPrefix(commandSource, "say $message")
}
