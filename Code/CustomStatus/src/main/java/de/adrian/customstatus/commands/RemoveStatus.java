package de.adrian.customStatus.Commands;

import de.adrian.customStatus.CustomStatus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

public class RemoveStatus implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("status.removestatus")){
            commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return false;
        }

        if (!(strings.length == 1)){
            commandSender.sendMessage(ChatColor.RED + "Syntax: /removestatus <player>");
            return false;
        }

        if (Bukkit.getOfflinePlayer(strings[0]) == null){
            commandSender.sendMessage(ChatColor.RED + "Player not found or doesn't have a status!");
            return false;
        }

        OfflinePlayer player = Bukkit.getOfflinePlayer(strings[0]);
        if (!CustomStatus.prefix.containsKey(player.getUniqueId())){
            commandSender.sendMessage(ChatColor.RED + "This player doesn't have a status!");
            return false;
        }

        CustomStatus.prefix.remove(player.getUniqueId());
        if (Bukkit.getPlayer(player.getUniqueId()) != null){
            Bukkit.getPlayer(player.getUniqueId()).setPlayerListName(player.getName());

            Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            String teamName = "nick-" + Bukkit.getPlayer(player.getUniqueId()).getUniqueId().toString().substring(0, 8);

            Team team = scoreboard.getTeam(teamName);
            if (team != null) {
                team.removeEntry(Bukkit.getPlayer(player.getUniqueId()).getName());
                team.unregister();
            }
        }

        commandSender.sendMessage(ChatColor.GREEN + "Status has been removed from " + player.getName() + ".");

        return true;
    }
}