package de.adrian.customStatus.Listeners;

import de.adrian.customStatus.CustomStatus;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        if (event.getMessage().contains(" ")) {
            return;
        }
        if (CustomStatus.creating.containsKey(event.getPlayer().getUniqueId())){
            if (CustomStatus.creating.get(event.getPlayer().getUniqueId()).equals("A")){
                if (event.getMessage().equals("CANCEL")){
                    event.getPlayer().sendMessage(ChatColor.GREEN + "Creation process cancelled.");
                    event.setCancelled(true);
                    CustomStatus.prefixname.remove(event.getPlayer().getUniqueId());
                    CustomStatus.creating.remove(event.getPlayer().getUniqueId());
                    return;
                }
                CustomStatus.prefixname.put(event.getPlayer().getUniqueId(), event.getMessage().toLowerCase());
                CustomStatus.creating.put(event.getPlayer().getUniqueId(), "B");
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "Type CANCEL to abort the process! \n" + ChatColor.AQUA + "Enter what the status prefix should be:");
                return;
            }
            if (CustomStatus.creating.get(event.getPlayer().getUniqueId()).equals("B")){
                if (event.getMessage().equals("CANCEL")){
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.GREEN + "Creation process cancelled.");
                    CustomStatus.prefixname.remove(event.getPlayer().getUniqueId());
                    CustomStatus.creating.remove(event.getPlayer().getUniqueId());
                    return;
                }
                String name = CustomStatus.prefixname.get(event.getPlayer().getUniqueId());
                CustomStatus.prefixname.remove(event.getPlayer().getUniqueId());
                CustomStatus.creating.remove(event.getPlayer().getUniqueId());
                event.setCancelled(true);
                event.getPlayer().performCommand("addstatus " + name + " " + event.getMessage());
            }
        }
    }
}