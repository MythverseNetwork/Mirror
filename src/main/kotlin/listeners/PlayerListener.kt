package listeners

import Base
import discord.RequestMaker
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.ChatEvent
import net.md_5.bungee.api.event.PlayerDisconnectEvent
import net.md_5.bungee.api.event.PostLoginEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

/**
 * Listener that listens (not surprisingly) for player events.
 */
class PlayerListener : Listener, Base {
    @EventHandler
    fun onPostLogin(e: PostLoginEvent) {
        plugin.memberCountStore.playerDidJoin()
    }

    @EventHandler
    fun onPlayerLeave(e: PlayerDisconnectEvent) {
        plugin.memberCountStore.playerDidLeave()
    }

    @EventHandler
    fun onPlayerMessage(e: ChatEvent) {
        val player =(e.sender as ProxiedPlayer)
        val server = player.server

        RequestMaker.sendMirroredChatMessage(server.info.name, player.name, e.message)
    }
}