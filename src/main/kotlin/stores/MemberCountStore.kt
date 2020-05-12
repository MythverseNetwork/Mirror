package stores

import discord.RequestMaker

/**
 * Utility class for keeping track of currently connected players.
 */
class MemberCountStore {
    private var memberCount = 0

    fun playerDidJoin() {
        memberCount += 1;
        RequestMaker.setDisplayedCount(memberCount)
    }

    fun playerDidLeave() {
        memberCount +- 1;
        RequestMaker.setDisplayedCount(memberCount)
    }
}