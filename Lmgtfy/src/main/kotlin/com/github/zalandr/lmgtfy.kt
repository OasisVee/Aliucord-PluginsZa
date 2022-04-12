package com.github.zalandr

import android.content.Context
import com.aliucord.Utils
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.api.CommandsAPI
import com.aliucord.entities.Plugin
import com.discord.api.commands.ApplicationCommandType

// Aliucord Plugin annotation. Must be present on the main class of your plugin
@AliucordPlugin(requiresRestart = false /* Whether your plugin requires a restart after being installed/updated */)
// Plugin class. Must extend Plugin and override start and stop
// Learn more: https://github.com/Aliucord/documentation/blob/main/plugin-dev/1_introduction.md#basic-plugin-structure
class Lmgtfy : Plugin() {
    override fun start(context: Context) {
        // A bit more advanced command with arguments
        commands.registerCommand("Lmgtfy", "Here, let me google that for you.",
            Utils.createCommandOption(ApplicationCommandType.STRING, "query", "what you want them to google i guess", required = true)
        ) { ctx ->
            // Check if a user argument was passed
            if (ctx.containsArg("query")) {
                val query = ctx.getString("query")
                CommandsAPI.CommandResult(Regex("\\s").replace(query, "+"), null, true)
            } else {
                // you did a bad
                CommandsAPI.CommandResult("you did a bad", null, false)
            }
        }
    }

    override fun stop(context: Context) {
        // Unregister our commands
        commands.unregisterAll()
    }
}
