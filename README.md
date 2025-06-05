# TransferPacket

## Features
- Developer API
- Whitelist of Player UUID's
- Token Whitelist via Paper's Cookie System.
- Customizable and developer-friendly

## Commands
- `/transferpacket` (or your custom Option) - Permission: `transferpacket.command.<commandName>`

## Permissions
- `transferpacket.command.<commandName>.transfer`
- `transferpacket.command.<commandName>.whitelist`
- `transferpacket.command.<commandName>.whitelist.player`
- `transferpacket.command.<commandName>.whitelist.player.add`
- `transferpacket.command.<commandName>.whitelist.player.remove`
- `transferpacket.command.<commandName>.whitelist.token`
- `transferpacket.command.<commandName>.whitelist.token.add`
- `transferpacket.command.<commandName>.whitelist.token.remove`

## Developer API

**Use our repo to Download the API**
- [https://repo.crystopia.net/#/releases/dev/jesforge/transferpacket](https://repo.nexocrew.com/#/releases/dev/jesforge/transferpacket)

### Project Setup

To access the API use the `TransferPacketAPI` Menthods and interact with the plugin.
__Note: Please add the Plugin TransferPacket as depend! And check the enabled status.__

```kts
repositories {
    mavenCentral()
    maven("https://repo.crystopia.net/releases")
}

dependencies {
    compileOnly("dev.jesforge:transferpacket:0.1.0")
}

```
