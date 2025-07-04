package de.adrian.customStatus.Commands;

import de.adrian.customStatus.CustomStatus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AddStatus implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("status.addstatus")){
            commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return false;
        }

        if (!(strings.length > 1)){
            commandSender.sendMessage(ChatColor.RED + "/addstatus <statusname> <statusprefix (Use color code &)>");
            return false;
        }

        String prefix = "";
        for (String st: strings){
            if (st != strings[0]){
                prefix = prefix + " " + st;
            }
        }
        prefix = prefix + " ";

        CustomStatus.prefixs.put(strings[0].toLowerCase(), ChatColor.translateAlternateColorCodes('&', prefix));
        commandSender.sendMessage(ChatColor.GREEN + "The status " + strings[0] + " has been added.");

        return true;
    }
}