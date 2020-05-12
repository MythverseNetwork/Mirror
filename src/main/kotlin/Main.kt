import org.bukkit.plugin.java.JavaPlugin

public class Main : JavaPlugin() {
    override fun onEnable() {
        logger.info("Enabled!")
    }

    override fun onDisable() {
        logger.info("Disabled!")
    }
}