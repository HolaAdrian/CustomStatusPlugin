package de.adrian.customstatus.commands;

import de.adrian.customstatus.CustomStatus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AddStatus implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("status.addstatus")){
            commandSender.sendMessage(ChatColor.RED + "Du hast keine Rechte diesen Command zu benutzen!");
            return false;
        }

        if (!(strings.length > 1)){
            commandSender.sendMessage(ChatColor.RED + "/addstatus <statusname> <statusprefix (Mit colorcode &)>");
            return false;
        }

        String prefix = "";
        for (String st: strings){
            if (st != strings[0]){
                prefix = prefix + " " + st;
            }


        }
        prefix = prefix + " ";

        CustomStatus.prefixs.put(strings[0], ChatColor.translateAlternateColorCodes('&', prefix));
        commandSender.sendMessage(ChatColor.GREEN + "Der Status " + strings[0] + " wurde hinzugefügt.");


        return true;
    }
}
