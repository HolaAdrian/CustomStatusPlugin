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
        TextComponent delete = new TextComponent(org.bukkit.ChatColor.BOLD + "" + ChatColor.AQUA + "Klicke auf den Status den du löschen möchtest:");
        p.spigot().sendMessage(delete);
        for (String s: CustomStatus.prefixs.keySet()){
            TextComponent st = new TextComponent(s);
            st.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deletestatus " + s));
            st.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Lösche den Status: " + s)));
            p.spigot().sendMessage(st);
        }

    }
    public static void RemoveStatus(Player p){
        TextComponent delete = new TextComponent(org.bukkit.ChatColor.BOLD + "" + ChatColor.AQUA + "Klicke auf den Spieler, dessen Status du löschen möchtest:");
        p.spigot().sendMessage(delete);
        for (UUID uuid: CustomStatus.prefix.keySet()){
            TextComponent st = new TextComponent(Bukkit.getOfflinePlayer(uuid).getName());
            st.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/removestatus " + Bukkit.getOfflinePlayer(uuid).getName()));
            st.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Lösche den Status von: " + Bukkit.getOfflinePlayer(uuid).getName())));
            p.spigot().sendMessage(st);
        }

    }
    public static void CreateStatus(Player p){
        p.sendMessage(ChatColor.BOLD + "" + ChatColor.RED+ "Schreib CANCEL um den Vorgang abzubrechen! \n" + ChatColor.AQUA+ "Gib ein unter welchem Name der Status gespeichert werden soll:");
        CustomStatus.prefixname.remove(p.getUniqueId());
        CustomStatus.creating.put(p.getUniqueId(), "A");
    }

}
