package org.welcomemessage;

import org.bukkit.plugin.java.JavaPlugin;

public class WelcomeMessage extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin WelcomeMessage activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin WelcomeMessage désactivé.");
    }
}