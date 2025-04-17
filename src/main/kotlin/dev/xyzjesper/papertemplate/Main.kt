package dev.xyzjesper.papertemplate

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this).verboseOutput(true))

        logger.info("Loading Plugin...")

    }

    override fun onEnable() {
        CommandAPI.onEnable()

        logger.info("Plugin enabled!")

    }

    override fun onDisable() {
        CommandAPI.onDisable()

        logger.info("Plugin disabled!")
    }

}