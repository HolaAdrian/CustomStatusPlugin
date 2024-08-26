package de.adrian.customStatus.commands;

import de.adrian.customStatus.CustomStatus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class RemoveStatus implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("status.removestatus")){
            commandSender.sendMessage(ChatColor.RED + "Du hast keine Rechte diesen Command zu benutzen!");
            return false;
        }

        if (!(strings.length == 1)){
            commandSender.sendMessage(ChatColor.RED + "Syntax: /removestatus <player>");
            return false;
        }


        if (Bukkit.getOfflinePlayer(strings[0]) == null){
            commandSender.sendMessage(ChatColor.RED + "Der Spieler wurde nicht gefunden oder er hat kein Status!");
            return false;
        }

        OfflinePlayer player = Bukkit.getOfflinePlayer(strings[0]);
        if (!CustomStatus.prefix.containsKey(player.getUniqueId())){
            commandSender.sendMessage(ChatColor.RED +  "Der Spieler hat keinen Status!");
            return false;
        }

        CustomStatus.prefix.remove(player.getUniqueId());
        if (Bukkit.getPlayer(player.getUniqueId()) != null){
            Bukkit.getPlayer(player.getUniqueId()).setPlayerListName(player.getName());
        }

        commandSender.sendMessage(ChatColor.GREEN + "Der Status von " + player.getName() + " wurde entfernt.");


        return true;
    }
}
