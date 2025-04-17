package dev.jesforge.transferpacket.config

import kotlinx.serialization.encodeToString
import java.io.File

object ConfigManager {

    private val settingsFile = File("plugins/TransferPacket/config.json")

    var settings = settingsFile.loadConfig(
        SettingsData(
            commandPermission = "transferpacket.commands.transferpacket",
            commandName = "transferpacket",
            messages = Messages(
                successTransfer = "<green>Successfully transfer from the server!</green>"
            )
        )
    )

    fun save() {
        settingsFile.writeText(json.encodeToString(settings))
    }

    fun reload() {
        settings = loadFromFile(settingsFile)
    }

}
