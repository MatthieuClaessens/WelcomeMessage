package org.welcomemessage;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.welcomemessage.listeners.PlayerListener;
import org.welcomemessage.manager.LangManager;

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

    private LangManager langManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.langManager = new LangManager(this);

        for (String line : logo) {
            getLogger().info(line);
        }

        getLogger().info(langManager.getMessage("plugin.enabled", "WelcomeMessage plugin activated! Version 1.0"));
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("wmreload")) {
            if (!sender.hasPermission("welcomemessage.reload")) {
                sender.sendMessage(langManager.getMessage("commands.no-permission",
                        "§6§lWELCOMEMSG §8§l» §cYou don't have permission to use this command."));
                return true;
            }

            reloadConfig();
            langManager.reloadLanguages();
            sender.sendMessage(langManager.getMessage("commands.reload-success",
                    "§6§lWELCOMEMSG §8§l» §aConfig reloaded!"));
            getLogger().info("Config reloaded by " + sender.getName());
            return true;
        }

        if (command.getName().equalsIgnoreCase("wmlang")) {
            if (!sender.hasPermission("welcomemessage.lang")) {
                sender.sendMessage(langManager.getMessage("commands.no-permission",
                        "§6§lWELCOMEMSG §8§l» §cYou don't have permission to use this command."));
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage(langManager.getMessage("commands.usage-wmlang",
                        "§6§lWELCOMEMSG §8§l» §7Usage: §e/wmlang <language|list>"));
                return true;
            }

            if (args[0].equalsIgnoreCase("list")) {
                String[] languages = langManager.getAvailableLanguages();
                String languageList = String.join(", ", languages);
                String message = langManager.getMessage("commands.available-languages",
                        "§6§lWELCOMEMSG §8§l» §7Available languages: §e%languages%");
                sender.sendMessage(message.replace("%languages%", languageList));
                return true;
            }

            String newLanguage = args[0];
            if (langManager.isLanguageAvailable(newLanguage)) {
                langManager.setLanguage(newLanguage);
                String message = langManager.getMessage("commands.language-changed",
                        "§6§lWELCOMEMSG §8§l» §aLanguage changed to §e%language%§a!");
                sender.sendMessage(message.replace("%language%", newLanguage));
                getLogger().info("Language changed to " + newLanguage + " by " + sender.getName());
            } else {
                String message = langManager.getMessage("commands.language-not-found",
                        "§6§lWELCOMEMSG §8§l» §cLanguage §e%language% §cnot found!");
                sender.sendMessage(message.replace("%language%", newLanguage));
            }
            return true;
        }

        return false;
    }

    @Override
    public void onDisable() {
        for (String line : logo) {
            getLogger().info(line);
        }
        getLogger().info(langManager.getMessage("plugin.disabled", "WelcomeMessage plugin disabled."));
    }

    public LangManager getLangManager() {
        return langManager;
    }
}