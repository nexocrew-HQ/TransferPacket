package dev.xyzjesper.papertemplate.config

import kotlinx.serialization.encodeToString
import java.io.File

object ConfigManager {

    private val settingsFile = File("plugins/AffiliateCode/config.json")

    val settings = settingsFile.loadConfig(SettingsData)

    fun save() {
        settingsFile.writeText(json.encodeToString(settings))
    }

    fun reload() {
        settings = loadFromFile(settingsFile)
    }
    
}
