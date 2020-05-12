import net.md_5.bungee.api.plugin.Plugin
import stores.MemberCountStore
import utils.Configuration

@kr.entree.spigradle.Plugin
class MirrorPlugin : Plugin() {
    val memberCountStore = MemberCountStore()
    val configManager = Configuration()

    override fun onLoad() {
        instance = this

    }

    override fun onEnable() {
        logger.info("")
    }

    companion object {
        lateinit var instance: MirrorPlugin
    }
}