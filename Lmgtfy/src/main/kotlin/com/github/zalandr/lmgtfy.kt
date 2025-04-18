package com.github.zalandr

import android.content.Context
import com.aliucord.Utils
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.api.CommandsAPI
import com.aliucord.entities.Plugin
import com.discord.api.commands.ApplicationCommandType
import com.aliucord.Utils.createCommandChoice
import java.net.URLEncoder

// Aliucord Plugin annotation. Must be present on the main class of your plugin
@AliucordPlugin(requiresRestart = false /* Whether your plugin requires a restart after being installed/updated */)
// Plugin class. Must extend Plugin and override start and stop
// Learn more: https://github.com/Aliucord/documentation/blob/main/plugin-dev/1_introduction.md#basic-plugin-structure
class Lmgtfy : Plugin() {
    override fun start(context: Context) {
        val engines = listOf (
            createCommandChoice("Lmgtfy", "https://lmgtfy2.com/?q="),
            createCommandChoice("Google", "https://google.com/search?q="),
            createCommandChoice("Duckduckgo", "https://duckduckgo.com/?q="),
            createCommandChoice("Bing", "https://bing.com/search?q=")
        )

        val options = listOf (
            Utils.createCommandOption(ApplicationCommandType.STRING, "query", "what you want them to google i guess", required = true, default = true),
            Utils.createCommandOption(ApplicationCommandType.STRING, "engine", "alternative search engines - default: lmgtfy", choices = engines)
        )

        // A bit more advanced command with arguments
        commands.registerCommand("Lmgtfy", "Here, let me search that for you.", options) {
            val query = it.getString("query")
            val engine = it.getStringOrDefault("engine", "https://lmgtfy2.com/?q=")
            CommandsAPI.CommandResult(engine + URLEncoder.encode(query, "UTF-8"), null, true)
        }
    }

    override fun stop(context: Context) {
        // Unregister our commands
        commands.unregisterAll()
    }
}
