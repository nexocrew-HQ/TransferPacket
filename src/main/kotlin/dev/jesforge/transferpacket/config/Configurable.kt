package dev.jesforge.transferpacket.config

interface Configurable {
    fun save()
    fun load() {}
    fun reset() {}
}