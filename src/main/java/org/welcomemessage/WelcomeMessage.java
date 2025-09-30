package org.welcomemessage;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WelcomeMessage extends JavaPlugin {

    private final String[] logo = {
    " __          __  _                          __  __           ",
    " \\ \\        / / | |                        |  \\/  |          ",
    "  \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___| \\  / |___  __ _ ",
    "   \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ |\\/| / __|/ _` |",
    "    \\  /\\  /  __/ | (_| (_) | | | | | |  __/ |  | \\__ \\ (_| |",
    "     \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|_|  |_|___/\\__, |",
    "                                                        __/ |",
    "                                                       |___/",
    " Version 1.0.0 By Nenfal",
    " https://github.com/MatthieuClaessens/WelcomeMessage"
    };

    @Override
    public void onEnable() {
        new UpdateChecker(this, 127633).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                getLogger().info("You are running the latest version!");
            } else {
                getLogger().info("new version is available: " + version);
                getLogger().info("You can download it from: https://www.spigotmc.org/resources/welcomemessage.127633/");
            }
        });
        String currentVersion = getDescription().getVersion();
        saveDefaultConfig();
        for (String line : logo) {
            getLogger().info(line);
        }
        getLogger().info("WelcomeMessage plugin activated! Version 1.0");
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getConfig();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("wmreload")) {
            if (!sender.hasPermission("welcomemessage.reload")) {
                sender.sendMessage("§6§lᴡᴇʟᴄᴏᴍᴇᴍꜱɢ §8§l» §cYou don't have permission to use this command.");
                return true;
            }
            reloadConfig();
            sender.sendMessage("§6§lᴡᴇʟᴄᴏᴍᴇᴍꜱɢ §8§l» §aConfig reloaded!");
            getLogger().info("Config reloaded by " + sender.getName());
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        for (String line : logo) {
            getLogger().info(line);
        }
        getLogger().info("WelcomeMessage plugin disabled.");
    }
}