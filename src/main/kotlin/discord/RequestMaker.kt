package discord

import Base
import utils.Language
import utils.StringUtils

object RequestMaker : Base {
    private const val DISCORD_CHANNEL_ENDPOINT = "https://discordapp.com/api/v6/channels/%s";

    // Rate limit handling.
    private var lastUpdate = System.currentTimeMillis()
    private const val minDelay = 30e3

    /**
     * Update the displayed member count.
     */
    fun setDisplayedCount(count: Int) {
        if (System.currentTimeMillis() - lastUpdate < minDelay) {
            return
        }

        lastUpdate = System.currentTimeMillis()
        updateChannelName(config.getString("memberCount.channel"), Language.memberCountFormat.format(count))
    }

    /**
     * Send a mirror message.
     */
    fun sendMirroredChatMessage(serverName: String, playerName: String, content: String) {
        callWebhook(
            config.getString("mirror.webhookUrl"),
            StringUtils.colorize(Language.mirrorFormat.format(serverName, playerName, content))
        )
    }

    /**
     * Call a discord webhook.
     */
    private fun callWebhook(url: String, content: String) {
        khttp.async.post(url, mapOf("content" to content))
    }

    /**
     * Update a channel name.
     */
    private fun updateChannelName(id: String, name: String) {
        val payload = mapOf("name" to name)
        val headers = mapOf("Authorization" to "Bearer " + config.getString("token"))

        khttp.async.post(DISCORD_CHANNEL_ENDPOINT.format(id), data = payload, headers = headers, onError = {
            logger.severe("Failed to execute channel name update.")
            logger.severe(this.message)
        })
    }
}