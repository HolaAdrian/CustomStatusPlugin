package de.adrian.customstatus.commands;

import de.adrian.customstatus.CustomStatus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DeleteStatus implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("status.deletestatus")){
            commandSender.sendMessage(ChatColor.RED + "Du hast keine Rechte diesen Command zu benutzen!");
            return false;
        }

        if (!(strings.length == 1)){
            commandSender.sendMessage(ChatColor.RED + "Syntax: /deletestatus <status>");
            return false;
        }

        if (!CustomStatus.prefixs.containsKey(strings[0])){
            commandSender.sendMessage(ChatColor.RED + "Der Status wurde nicht gefunden!");
            return false;
        }

        //FortressCustomStatus.prefixs.remove(strings[0]);
        CustomStatus.prefixs.remove(strings[0], CustomStatus.prefixs.get(strings[0]));
        commandSender.sendMessage(ChatColor.GREEN + "Der Status wurde entfernt.");


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
