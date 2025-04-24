package de.adrian.customStatus.listeners;

import Utility.TabListUtils;
import de.adrian.customStatus.CustomStatus;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (CustomStatus.joinMessageNormal){
            String joinMessage = event.getJoinMessage();
            String realJoinMessage = joinMessage.substring(joinMessage.indexOf(event.getPlayer().getName()));
            event.setJoinMessage(ChatColor.YELLOW + realJoinMessage);
        }
        TabListUtils.SetPlayerTabListPrefix(event.getPlayer());


    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (CustomStatus.joinMessageNormal){
            String joinMessage = event.getQuitMessage();
            String realJoinMessage = joinMessage.substring(joinMessage.indexOf(event.getPlayer().getName()));
            event.setQuitMessage(ChatColor.YELLOW + realJoinMessage);
        }



    }



}
