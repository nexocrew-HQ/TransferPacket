package dev.jesforge.transferpacket.utils

import dev.jesforge.transferpacket.TransferPacketPlugin
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType

object Cookie {

    var namespacedKeyForJoin = NamespacedKey(TransferPacketPlugin.instance, "joinKey")

    fun storeCookie(cookie: String, player: Player) {
        val cookieBytes = cookie.toByteArray(Charsets.UTF_8)
        player.storeCookie(namespacedKeyForJoin, cookieBytes)
    }

    fun getCookie(player: Player, callback: (String?) -> Unit) {
        player.retrieveCookie(namespacedKeyForJoin).thenAccept { bytes ->
            if (bytes == null) {
                return@thenAccept
            }
            val string = String(bytes, Charsets.UTF_8)
            callback(string)
        }.exceptionally {
            it.printStackTrace()
            callback(null)
            null
        }
    }
}