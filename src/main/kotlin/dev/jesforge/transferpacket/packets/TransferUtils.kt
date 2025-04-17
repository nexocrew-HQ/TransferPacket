package dev.jesforge.transferpacket.packets

import dev.jesforge.transferpacket.config.ConfigManager
import dev.jesforge.transferpacket.utils.Cookie
import org.bukkit.entity.Player


object TransferUtils {
    fun forceTransfer(player: Player, ip: String, port: Int) {
        Cookie.storeCookie(ConfigManager.settings.whitelistString as String, player)
        player.transfer(ip, port)
    }
}