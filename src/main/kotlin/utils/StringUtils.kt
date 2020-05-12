package utils

import net.md_5.bungee.api.ChatColor

object StringUtils {
    fun colorize(string: String): String = ChatColor.translateAlternateColorCodes('&', string)
}