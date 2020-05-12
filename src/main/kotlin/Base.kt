import java.util.logging.Logger

/**
 * Base class for accessing static properties.
 */
interface Base {
    val plugin
        get() = MirrorPlugin.instance

    val logger: Logger
        get() = plugin.logger

    val config: net.md_5.bungee.config.Configuration
        get() = plugin.configManager.config
}