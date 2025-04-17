package dev.jesforge.transferpacket.commands

import dev.jesforge.transferpacket.config.ConfigManager
import dev.jesforge.transferpacket.packets.TransferUtils
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.*
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player

object TransferPacketCommand {
    val mm = MiniMessage.miniMessage()

    val command = commandTree(ConfigManager.settings.commandName, "transferpacket") {
        withPermission("transferpacket.command.${ConfigManager.settings.commandName}")
        literalArgument("transfer") {
            withPermission("transferpacket.command.${ConfigManager.settings.commandName}.transfer")
            stringArgument("ip") {
                integerArgument("port") {
                    executes(
                        CommandExecutor { commandSender, commandArguments ->

                            TransferUtils.forceTransfer(
                                commandSender as Player, commandArguments[0] as String, commandArguments[1] as Int
                            )
                            commandSender.sendMessage(mm.deserialize(ConfigManager.settings.messages.successTransfer))
                        })
                }
            }
        }
        literalArgument("whitelist") {
            withPermission("transferpacket.command.${ConfigManager.settings.commandName}.whitelist")
            literalArgument("player") {
                withPermission("transferpacket.command.${ConfigManager.settings.commandName}.whitelist.player")
                literalArgument("add") {
                    withPermission("transferpacket.command.${ConfigManager.settings.commandName}.whitelist.player.add")
                    offlinePlayerArgument("player") {
                        executes(
                            CommandExecutor { commandSender, commandArguments ->
                                ConfigManager.settings.whitelistedPlayerUUIDs.add((commandArguments[0] as Player).uniqueId.toString())
                                ConfigManager.save()
                                commandSender.sendMessage(mm.deserialize(ConfigManager.settings.messages.addedNewPlayerToList))
                            })
                    }
                }
                literalArgument("remove") {
                    withPermission("transferpacket.command.${ConfigManager.settings.commandName}.whitelist.player.remove")
                    offlinePlayerArgument("player") {
                        executes(
                            CommandExecutor { commandSender, commandArguments ->
                                ConfigManager.settings.whitelistedPlayerUUIDs.remove((commandArguments[0] as Player).uniqueId.toString())
                                ConfigManager.save()
                                commandSender.sendMessage(mm.deserialize(ConfigManager.settings.messages.removePlayerFromList))
                            })
                    }
                }
            }
            literalArgument("token") {
                withPermission("transferpacket.command.${ConfigManager.settings.commandName}.whitelist.token")
                literalArgument("add") {
                    withPermission("transferpacket.command.${ConfigManager.settings.commandName}.whitelist.player.add")
                    textArgument("token") {
                        executes(
                            CommandExecutor { commandSender, commandArguments ->
                                val token = commandArguments[0] as String

                                ConfigManager.settings.whitelistString!!.add(token)
                                ConfigManager.save()

                                commandSender.sendMessage(mm.deserialize(ConfigManager.settings.messages.addedNewTokenToList))
                            })
                    }
                }
                literalArgument("remove") {
                    withPermission("transferpacket.command.${ConfigManager.settings.commandName}.whitelist.player.remove")
                    textArgument("token") {
                        executes(
                            CommandExecutor { commandSender, commandArguments ->
                                val token = commandArguments[0] as String

                                ConfigManager.settings.whitelistString!!.remove(token)
                                ConfigManager.save()

                                commandSender.sendMessage(mm.deserialize(ConfigManager.settings.messages.removeTokenFromList))
                            })
                    }
                }
            }
        }
    }
}