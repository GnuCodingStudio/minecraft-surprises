package fr.o80.testmc

import fr.o80.testmc.command.TestCommand
import fr.o80.testmc.command.StartSurpriseCommand
import fr.o80.testmc.command.StopSurpriseCommand
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.literal
import kotlin.time.Duration

class TestMC : ModInitializer {

    override fun onInitialize() {
        CommandRegistrationCallback.EVENT.register { dispatcher, registryAccess, environment ->
            dispatcher.register(
                literal("surprise")
                    .requires { it.hasPermissionLevel(2) }
                    .then(
                        literal("test").executes(TestCommand())
                    )
                    .then(
                        literal("start-test").executes(
                            StartSurpriseCommand(
                                delay = Duration.parse("PT1S"),
                                millisInterval = Duration.parse("PT30S")
                            )
                        )
                    )
                    .then(
                        literal("start").executes(StartSurpriseCommand())
                    )
                    .then(
                        literal("stop").executes(StopSurpriseCommand())
                    )
            )
        }
    }
}
