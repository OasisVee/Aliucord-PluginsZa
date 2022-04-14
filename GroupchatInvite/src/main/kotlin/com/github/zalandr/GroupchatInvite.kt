package com.github.zalandr

import android.content.Context
import com.aliucord.Utils
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.api.CommandsAPI
import com.aliucord.entities.Plugin
import com.discord.api.commands.ApplicationCommandType
import com.aliucord.Http
import org.json.JSONObject

// Aliucord Plugin annotation. Must be present on the main class of your plugin
@AliucordPlugin(requiresRestart = false /* Whether your plugin requires a restart after being installed/updated */)
// Plugin class. Must extend Plugin and override start and stop
// Learn more: https://github.com/Aliucord/documentation/blob/main/plugin-dev/1_introduction.md#basic-plugin-structure
class GroupchatInvite : Plugin() {
    override fun start(context: Context) {
        // A bit more advanced command with arguments
        commands.registerCommand(
            "invite", "get groupchat invite link", 
            Utils.createCommandOption(ApplicationCommandType.STRING, "send", "whether to send the link - default: false")
        ){
            // CommandsAPI.CommandResult(it.currentChannel.type.toString());
            // group channel is type 3 fyi
            if (it.currentChannel.type != 3) { 
                return CommandsAPI.CommandResult("this isnt a groupchat channel (you did a bad)", false)
            }
            
            val send = it.getBoolOrDefault("send", false);
            val invite = Http.Request.newDiscordRequest("/v9/channels/@me/${it.channelId}/invites")
                .executeWithJson(JSONObject("{max_age: 86400}"));
            CommandsAPI.CommandResult(invite, send);
        }
    }

    override fun stop(context: Context) {
        // Unregister our commands
        commands.unregisterAll()
    }
}
