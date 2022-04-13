package com.github.zalandr

import android.content.Context
import com.aliucord.Utils
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.api.CommandsAPI
import com.aliucord.entities.Plugin
import com.discord.api.commands.ApplicationCommandType
import com.aliucord.Http

// Aliucord Plugin annotation. Must be present on the main class of your plugin
@AliucordPlugin(requiresRestart = false /* Whether your plugin requires a restart after being installed/updated */)
// Plugin class. Must extend Plugin and override start and stop
// Learn more: https://github.com/Aliucord/documentation/blob/main/plugin-dev/1_introduction.md#basic-plugin-structure
class GCInvite : Plugin() {
    override fun start(context: Context) {
        // A bit more advanced command with arguments
        commands.registerCommand(
            "invite", "get groupchat invite link", 
            Utils.createCommandOption(ApplicationCommandType.STRING, "send", "whether to send the link - default: false")
        ){
            println(it.currentChannel.type);
            // if (it.currentChannel.type == "group") {
            //     val send = it.getBoolOrDefault("send", false);
            //     val invite = Http.Request.newDiscordRequest("/v9/channels/${it.channelId}/invites");
            //         .executeWithJson({"max_age": 86400});
            //     CommandsAPI.CommandResult(invite, send);
            // } else {
            //     CommandsAPI.CommandResult("this isnt a groupchat channel (you did a bad)", false);
            // }
        }
    }

    override fun stop(context: Context) {
        // Unregister our commands
        commands.unregisterAll()
    }
}
