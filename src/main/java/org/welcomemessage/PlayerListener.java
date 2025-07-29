package org.welcomemessage;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private final WelcomeMessage plugin;

    public PlayerListener(WelcomeMessage plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String joinmsg = plugin.getConfig().getString("join-message", "§a+ §6%player% §7joined the server");
        joinmsg = joinmsg.replace("%player%", event.getPlayer().getName());
        Bukkit.broadcastMessage(joinmsg);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        String quitmsg = plugin.getConfig().getString ("quit-message", "§c- §6%player% §7left the server");
        quitmsg = quitmsg.replace("%player%", event.getPlayer().getName());
        Bukkit.broadcastMessage(quitmsg);
        }
}
