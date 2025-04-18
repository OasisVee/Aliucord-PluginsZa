version = "1.1.0" // Plugin version. Increment this to trigger the updater
description = "Google stuff for people who cant" // Plugin description that will be shown to user

aliucord {
    // Changelog of your plugin
    changelog.set("""
    	# 1.1.0
	* changed lmgtfy domain, since lmgtfy.app is broken/dead, to lmgtfy2.com
	
    	# 1.0.2
        * query param is now autoselected
    """.trimIndent())
    // Image or Gif that will be shown at the top of your changelog page
    // changelogMedia.set("https://cool.png")

    // Add additional authors to this plugin
    // author("Name", 0)
    // author("Name", 0)

    // Excludes this plugin from the updater, meaning it won't show up for users.
    // Set this if the plugin is unfinished
//    excludeFromUpdaterJson.set(false)
}
