package org.welcomemessage;

import org.bukkit.plugin.java.JavaPlugin;


public class WelcomeMessage extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(" __          __  _                          __  __           ");
        getLogger().info(" \\ \\        / / | |                        |  \\/  |          ");
        getLogger().info("  \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___| \\  / |___  __ _ ");
        getLogger().info("   \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ |\\/| / __|/ _` |");
        getLogger().info("    \\  /\\  /  __/ | (_| (_) | | | | | |  __/ |  | \\__ \\ (_| |");
        getLogger().info("     \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|_|  |_|___/\\__, |");
        getLogger().info("                                                        __/ |");
        getLogger().info("                                                       |___/");
        getLogger().info("By Nenfal");
        getLogger().info("WelcomeMessage plugin activated! Version 1.0");
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info(" __          __  _                          __  __           ");
        getLogger().info(" \\ \\        / / | |                        |  \\/  |          ");
        getLogger().info("  \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___| \\  / |___  __ _ ");
        getLogger().info("   \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ |\\/| / __|/ _` |");
        getLogger().info("    \\  /\\  /  __/ | (_| (_) | | | | | |  __/ |  | \\__ \\ (_| |");
        getLogger().info("     \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|_|  |_|___/\\__, |");
        getLogger().info("                                                        __/ |");
        getLogger().info("                                                       |___/");
        getLogger().info("===");
        getLogger().info("WelcomeMessage plugin disabled.");
    }
}