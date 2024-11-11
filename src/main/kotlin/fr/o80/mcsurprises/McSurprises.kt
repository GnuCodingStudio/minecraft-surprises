package fr.o80.mcsurprises

import fr.o80.mcsurprises.command.StartSurpriseCommand
import fr.o80.mcsurprises.command.StopSurpriseCommand
import fr.o80.mcsurprises.command.TestCommand
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.literal
import kotlin.time.Duration.Companion.seconds

class McSurprises : ModInitializer {

    override fun onInitialize() {
        CommandRegistrationCallback.EVENT.register { dispatcher, registryAccess, environment ->
            dispatcher.register(
                literal("surprise")
                    .requires { it.hasPermissionLevel(2) }
                    .then(
                        literal("test").executes(TestCommand())
                    )
                    .then(
                        literal("start-test").executes(StartSurpriseCommand(delay = 1.seconds, 30.seconds))
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
