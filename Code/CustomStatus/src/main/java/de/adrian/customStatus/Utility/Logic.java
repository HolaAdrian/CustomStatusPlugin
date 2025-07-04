package de.adrian.customStatus.Utility;

import de.adrian.customStatus.CustomStatus;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class Logic {

    public static void DeleteStatus(Player p){
        TextComponent delete = new TextComponent(org.bukkit.ChatColor.BOLD + "" + ChatColor.AQUA + "Click on the status you want to delete:");
        p.spigot().sendMessage(delete);
        for (String s: CustomStatus.prefixs.keySet()){
            TextComponent st = new TextComponent(s);
            st.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deletestatus " + s));
            st.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Delete status: " + s)));
            p.spigot().sendMessage(st);
        }
    }

    public static void RemoveStatus(Player p){
        TextComponent delete = new TextComponent(org.bukkit.ChatColor.BOLD + "" + ChatColor.AQUA + "Click on the player whose status you want to remove:");
        p.spigot().sendMessage(delete);
        for (UUID uuid: CustomStatus.prefix.keySet()){
            TextComponent st = new TextComponent(Bukkit.getOfflinePlayer(uuid).getName());
            st.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/removestatus " + Bukkit.getOfflinePlayer(uuid).getName()));
            st.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Remove status from: " + Bukkit.getOfflinePlayer(uuid).getName())));
            p.spigot().sendMessage(st);
        }
    }

    public static void CreateStatus(Player p){
        p.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "Type CANCEL to abort the process! \n" + ChatColor.AQUA + "Enter the name under which the status should be saved:");
        CustomStatus.prefixname.remove(p.getUniqueId());
        CustomStatus.creating.put(p.getUniqueId(), "A");
    }
}