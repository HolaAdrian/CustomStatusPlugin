package de.adrian.customStatus.Commands;

import de.adrian.customStatus.Utility.ItemGranter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!commandSender.hasPermission("status.admin")){
            commandSender.sendMessage(ChatColor.RED + "Du hast keine Rechte diesen Command zu benutzen!");
            return false;
        }

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "Du musst ein Spieler sein um diesen Command auszuführen!");
            return false;
        }

        Player player = ((Player) commandSender).getPlayer();

        player.openInventory(ItemGranter.AdminInventory());



        return false;
    }
}
