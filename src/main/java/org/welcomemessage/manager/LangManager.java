package org.welcomemessage.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.welcomemessage.WelcomeMessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LangManager {
    private final WelcomeMessage plugin;
    private final Map<String, FileConfiguration> languages;
    private String currentLanguage;

    public LangManager(WelcomeMessage plugin) {
        this.plugin = plugin;
        this.languages = new HashMap<>();
        this.currentLanguage = plugin.getConfig().getString("language", "en-US");

        loadLanguages();
    }

    private void loadLanguages() {
        String[] availableLanguages = {"en-US", "es-ES"};

        for (String lang : availableLanguages) {
            loadLanguage(lang);
        }
    }

    private void loadLanguage(String language) {
        File langFolder = new File(plugin.getDataFolder(), "lang");
        if (!langFolder.exists()) {
            langFolder.mkdirs();
        }

        File langFile = new File(langFolder, language + ".yml");

        if (!langFile.exists()) {
            plugin.saveResource("lang/" + language + ".yml", false);
        }

        FileConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

        InputStream defConfigStream = plugin.getResource("lang/" + language + ".yml");
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(
                    new InputStreamReader(defConfigStream, StandardCharsets.UTF_8)
            );
            langConfig.setDefaults(defConfig);
        }

        languages.put(language, langConfig);
    }

    public String getMessage(String key) {
        return getMessage(key, null);
    }

    public String getMessage(String key, String defaultValue) {
        FileConfiguration langConfig = languages.get(currentLanguage);

        if (langConfig == null) {
            langConfig = languages.get("en-US");
        }

        if (langConfig == null) {
            return defaultValue != null ? defaultValue : key;
        }

        String message = langConfig.getString(key, defaultValue);
        return message != null ? colorize(message) : (defaultValue != null ? defaultValue : key);
    }

    public String getMessage(String key, String playerName, String defaultValue) {
        String message = getMessage(key, defaultValue);
        return message.replace("%player%", playerName);
    }

    private String colorize(String message) {
        return message.replace("&", "§");
    }

    public void setLanguage(String language) {
        if (languages.containsKey(language)) {
            this.currentLanguage = language;
            plugin.getConfig().set("language", language);
            plugin.saveConfig();
        }
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public boolean isLanguageAvailable(String language) {
        return languages.containsKey(language);
    }

    public String[] getAvailableLanguages() {
        return languages.keySet().toArray(new String[0]);
    }

    public void reloadLanguages() {
        languages.clear();
        loadLanguages();
    }
}