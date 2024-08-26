package de.adrian.customStatus.commands;

import Utility.TabListUtils;
import de.adrian.customStatus.CustomStatus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StatusCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("status.setstatus")){
            commandSender.sendMessage(ChatColor.RED + "Du hast keine Rechte diesen Command zu benutzen!");
            return false;
        }

        if (!(strings.length == 1)){
            commandSender.sendMessage(ChatColor.RED + "Syntax: /status <status>");
            return false;
        }

        if (!CustomStatus.prefixs.containsKey(strings[0])){
            commandSender.sendMessage(ChatColor.RED + "Der Status ist nicht vorhanden!");
            return false;
        }

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "Du musst ein Spieler sein um deinen Status zu setzen!");
            return false;
        }

        Player player = (Player) commandSender;
        CustomStatus.prefix.put(player.getUniqueId(), CustomStatus.prefixs.get(strings[0]));
        TabListUtils.SetPlayerTabListPrefix(player);
        player.sendMessage(ChatColor.GREEN + "Dein Status wurde geändert!");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ArrayList<String> vorschläge = new ArrayList<>();


        if (strings.length ==1){
            for (String st: CustomStatus.prefixs.keySet()){
                vorschläge.add(st);
            }
        }
        ArrayList<String> startingWith = new ArrayList<>();

        String arg = strings[strings.length -1];

        for (String s1 : vorschläge) {
            if (s1.toLowerCase().startsWith(arg)|| s1.startsWith(arg)){
                startingWith.add(s1);
            }
        }


        return startingWith;
    }
}
