package de.adrian.customStatus.Commands;

import de.adrian.customStatus.CustomStatus;
import de.adrian.customStatus.Utility.TabListUtils;
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
            commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return false;
        }

        if (!(strings.length == 1)){
            commandSender.sendMessage(ChatColor.RED + "Syntax: /status <status>");
            return false;
        }

        if (!CustomStatus.prefixs.containsKey(strings[0].toLowerCase())){
            commandSender.sendMessage(ChatColor.RED + "This status doesn't exist!");
            return false;
        }

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "You must be a player to set your status!");
            return false;
        }

        Player player = (Player) commandSender;
        CustomStatus.prefix.put(player.getUniqueId(), CustomStatus.prefixs.get(strings[0].toLowerCase()));
        TabListUtils.SetPlayerTabListPrefix(player);
        player.sendMessage(ChatColor.GREEN + "Your status has been updated!");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ArrayList<String> suggestions = new ArrayList<>();

        if (strings.length ==1){
            for (String st: CustomStatus.prefixs.keySet()){
                suggestions.add(st.toLowerCase());
            }
        }
        ArrayList<String> startingWith = new ArrayList<>();

        String arg = strings[strings.length -1];

        for (String s1 : suggestions) {
            if (s1.toLowerCase().startsWith(arg)|| s1.startsWith(arg)){
                startingWith.add(s1.toLowerCase());
            }
        }

        return startingWith;
    }
}