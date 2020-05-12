package utils

import Base

import com.google.common.io.ByteStreams
import net.md_5.bungee.config.Configuration
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration

import java.io.*

class Configuration : Base {

    /**
     * Fetch and cache the configuration file for the plugin.
     */
    override lateinit var config: Configuration
        private set

    init {
        loadConfig()
    }

    /**
     * Save the current cached config to disk.
     * @return
     */
    fun saveConfig(): Boolean {
        logger.info(config.getString("lang.console.config.save"))
        return writeConfig(config)
    }

    /**
     * Write the provided configuration to 'config.yml'.
     * @param configuration
     */
    private fun writeConfig(configuration: Configuration?): Boolean {
        val file = File(plugin.dataFolder, "config.yml")
        return try {
            if (!file.exists()) {
                file.parentFile.mkdirs()
                file.createNewFile()
            }
            ConfigurationProvider.getProvider(YamlConfiguration::class.java).save(configuration, file)
            true
        } catch (err: IOException) {
            err.printStackTrace()
            false
        }
    }

    /**
     * Fetch the 'config.yml' stored in the plugin data folder, and set it to the private 'config' variable.
     */
    private fun loadConfig() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration::class.java).load(loadResource("config.yml"))
        } catch (e: IOException) {
            logger.severe(config.getString("lang.console.config.loadError"))
        }
    }

    /**
     * Load a resource from the plugin's folder, creating it if it does not already exist.
     * @param resource Filename to load.
     */
    private fun loadResource(resource: String): File {
        val folder: File = plugin.dataFolder

        if (!folder.exists()) folder.mkdir()

        val resourceFile = File(folder, resource)
        try {
            if (!resourceFile.exists()) {
                resourceFile.createNewFile()
                plugin.getResourceAsStream(resource)
                    .use { `in` -> FileOutputStream(resourceFile).use { out -> ByteStreams.copy(`in`, out) } }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return resourceFile
    }

    init {
        loadConfig()
    }
}