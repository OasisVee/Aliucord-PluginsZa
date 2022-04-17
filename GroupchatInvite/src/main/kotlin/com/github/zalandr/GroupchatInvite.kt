package com.github.zalandr

import android.content.Context
import com.aliucord.Utils
import com.aliucord.Utils.createCommandChoice
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
        val choices = listOf(
            createCommandChoice("true", "true"),
            createCommandChoice("false", "false"),
        )
        // A bit more advanced command with arguments
        commands.registerCommand(
            "invite", "get groupchat invite link", 
            Utils.createCommandOption(ApplicationCommandType.BOOLEAN,
                "send", "whether to send the link - default: false",
                choices = choices)
        ){
            // group channel is type 3 fyi
            if (it.currentChannel.type == 3) {
                val send = it.getBoolOrDefault("send", false);
                val invite = JSONObject(Http.Request.newDiscordRequest("/channels/${it.channelId}/invites", "POST")
                    .executeWithJson(JSONObject("{max_age: 86400}"))
                    .text());
                CommandsAPI.CommandResult("https://discord.gg/" + invite.getString("code"), null, send);
            } else {
                CommandsAPI.CommandResult("this isnt a groupchat channel (you did a bad)", null, false);
            }
        }
    }

    override fun stop(context: Context) {
        // Unregister our commands
        commands.unregisterAll()
    }
}
