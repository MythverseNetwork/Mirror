package utils

import Base

/**
 * Utility object for allowing configuration of plugin language.
 */
object Language : Base {
    val memberCountFormat: String = config.getString("language.memberCountFormat")
    val mirrorFormat: String = config.getString("language.mirrorFormat")
}