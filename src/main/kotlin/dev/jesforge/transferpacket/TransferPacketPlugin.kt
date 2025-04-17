package dev.jesforge.transferpacket

import dev.jesforge.transferpacket.commands.TransferPacketCommand
import dev.jesforge.transferpacket.packets.TransferUtils
import dev.jesforge.transferpacket.utils.Log
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import org.bukkit.plugin.java.JavaPlugin

class TransferPacketPlugin : JavaPlugin() {

    companion object {
        lateinit var instance: TransferPacketPlugin
    }

    init {
        instance = this
    }

    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this).verboseOutput(true))
        Log.info("Loading Plugin...")
    }

    override fun onEnable() {
        CommandAPI.onEnable()

        // Commands
        TransferPacketCommand

        //Events
        server.pluginManager.registerEvents(dev.jesforge.transferpacket.events.PlayerJoinEvent, this)

        Log.info("Plugin enabled!")
    }

    override fun onDisable() {
        CommandAPI.onDisable()

        Log.info("Plugin disabled!")
    }

}