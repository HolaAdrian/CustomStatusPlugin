package de.adrian.customstatus.listeners;

import Utility.TabListUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        TabListUtils.SetPlayerTabListPrefix(event.getPlayer());
    }
}
