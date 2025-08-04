package org.welcomemessage;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@Deprecated
public class PlayerListener implements Listener {
    private final WelcomeMessage plugin;

    public PlayerListener(WelcomeMessage plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        String joinmsg = plugin.getConfig().getString("join-message", "§a+ §6%player% §7joined the server");
        joinmsg = joinmsg.replace("%player%", event.getPlayer().getName());
        Bukkit.broadcastMessage(joinmsg);
        if (plugin.getConfig().getBoolean("toggle-welcome-message", true)) {
            if (!event.getPlayer().hasPlayedBefore()) {
                if (plugin.getConfig().getBoolean("toggle-welcome-message", true)) {
                    String welcomemsg = plugin.getConfig().getString("welcome-message", "§eWelcome §6%player% §eto the server!");
                    welcomemsg = welcomemsg.replace("%player%", event.getPlayer().getName());
                    Bukkit.broadcastMessage(welcomemsg);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        event.setQuitMessage(null);
        String quitmsg = plugin.getConfig().getString ("quit-message", "§c- §6%player% §7left the server");
        quitmsg = quitmsg.replace("%player%", event.getPlayer().getName());
        Bukkit.broadcastMessage(quitmsg);
        }
}
