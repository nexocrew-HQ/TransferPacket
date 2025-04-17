package dev.jesforge.transferpacket.api

import dev.jesforge.transferpacket.config.ConfigManager
import dev.jesforge.transferpacket.utils.Cookie
import org.bukkit.entity.Player

object TransferPacketAPI {

    fun addPlayerUuidToList(playerUuid: String) {
        ConfigManager.settings.whitelistedPlayerUUIDs.add(playerUuid)
        ConfigManager.save()
    }

    fun removePlayerUuidFromList(playerUuid: String) {
        ConfigManager.settings.whitelistedPlayerUUIDs.remove(playerUuid)
        ConfigManager.save()
    }

    fun addTokenToList(token: String) {
        ConfigManager.settings.whitelistString!!.add(token)
        ConfigManager.save()
    }

    fun removeTokenFromList(token: String) {
        ConfigManager.settings.whitelistString!!.remove(token)
        ConfigManager.save()
    }

    fun transferPlayerWithToken(player: Player, ip: String, port: Int, token: String): Boolean {
        try {
            Cookie.storeCookie(token, player)
            player.transfer(ip, port)
            return true
        } catch (ex: Exception) {
            return false
        }
    }

}