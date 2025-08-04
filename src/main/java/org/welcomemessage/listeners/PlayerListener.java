package org.welcomemessage.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.welcomemessage.WelcomeMessage;

public class PlayerListener implements Listener {
    private final WelcomeMessage plugin;

    public PlayerListener(WelcomeMessage plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        String playerName = event.getPlayer().getName();

        String joinMessage = plugin.getLangManager().getMessage(
                "messages.join",
                playerName,
                "§a+ §6%player% §7joined the server"
        );
        Bukkit.broadcastMessage(joinMessage);

        if (plugin.getConfig().getBoolean("toggle-welcome-message", true)) {
            if (!event.getPlayer().hasPlayedBefore()) {
                String welcomeMessage = plugin.getLangManager().getMessage(
                        "messages.welcome",
                        playerName,
                        "§eWelcome §6%player% §eto the server!"
                );
                Bukkit.broadcastMessage(welcomeMessage);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        String playerName = event.getPlayer().getName();
        String quitMessage = plugin.getLangManager().getMessage(
                "messages.quit",
                playerName,
                "§c- §6%player% §7left the server"
        );
        Bukkit.broadcastMessage(quitMessage);
    }
}