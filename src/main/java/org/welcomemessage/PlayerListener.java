package org.welcomemessage;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.broadcastMessage("§a+ " + event.getPlayer().getName() + " §7joined the server");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Bukkit.broadcastMessage("§c- " + event.getPlayer().getName() + " §7left the server");
        }
}
