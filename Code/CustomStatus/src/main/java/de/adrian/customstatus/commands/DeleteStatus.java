package de.adrian.customStatus.Commands;

import de.adrian.customStatus.CustomStatus;
import de.adrian.customStatus.Utility.SafeManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DeleteStatus implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("status.deletestatus")){
            commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return false;
        }

        if (!(strings.length == 1)){
            commandSender.sendMessage(ChatColor.RED + "Syntax: /deletestatus <status>");
            return false;
        }

        if (!CustomStatus.prefixs.containsKey(strings[0].toLowerCase())){
            commandSender.sendMessage(ChatColor.RED + "Status not found!");
            return false;
        }

        for (Player p: Bukkit.getOnlinePlayers()){
            String status = CustomStatus.prefix.get(p.getUniqueId());
            if (status != null){
                if (status.equals(CustomStatus.prefixs.get(strings[0]))){
                    p.setPlayerListName(p.getName());
                    CustomStatus.prefix.remove(p.getUniqueId());

                    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                    String teamName = "nick-" + p.getUniqueId().toString().substring(0, 8);

                    Team team = scoreboard.getTeam(teamName);
                    if (team != null) {
                        team.removeEntry(Bukkit.getPlayer(p.getUniqueId()).getName());
                        team.unregister();
                    }
                }
            }
        }
        for (OfflinePlayer p: Bukkit.getOfflinePlayers()){
            String status = CustomStatus.prefix.get(p.getUniqueId());
            if (status != null){
                if (status.equals(CustomStatus.prefixs.get(strings[0]))){
                    CustomStatus.prefix.remove(p.getUniqueId());

                    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                    String teamName = "nick-" + p.getUniqueId().toString().substring(0, 8);

                    Team team = scoreboard.getTeam(teamName);
                    if (team != null) {
                        team.removeEntry(Bukkit.getPlayer(p.getUniqueId()).getName());
                        team.unregister();
                    }
                }
            }
        }

        CustomStatus.prefixs.remove(strings[0].toLowerCase());
        SafeManager.SafeAll(CustomStatus.getInstance().getConfig(), CustomStatus.getInstance());
        commandSender.sendMessage(ChatColor.GREEN + "Status has been removed.");

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