package de.adrian.customStatus.Commands;

import de.adrian.customStatus.CustomStatus;
import de.adrian.customStatus.Utility.SafeManager;
import de.adrian.customStatus.Utility.TranslationManager;
import org.bukkit.Bukkit;
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
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        TranslationManager tm = CustomStatus.getTranslationManager();
        String lang = CustomStatus.getInstance().getLanguage();

        if (!sender.hasPermission("status.deletestatus")) {
            sender.sendMessage(tm.getTranslation("no_permission", lang));
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(tm.getTranslation("deletestatus_syntax", lang));
            return false;
        }

        String statusKey = args[0].toLowerCase();
        String statusValue = CustomStatus.prefixs.get(statusKey);
        if (statusValue == null) {
            sender.sendMessage(tm.getTranslation("status_not_found", lang));
            return false;
        }

        // Remove from online players
        for (Player p : Bukkit.getOnlinePlayers()) {
            String current = CustomStatus.prefix.get(p.getUniqueId());
            if (statusValue.equals(current)) {
                p.setPlayerListName(p.getName());
                CustomStatus.prefix.remove(p.getUniqueId());

                Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                String teamName = "nick-" + p.getUniqueId().toString().substring(0, 8);
                Team team = scoreboard.getTeam(teamName);
                if (team != null) {
                    team.removeEntry(p.getName());
                    team.unregister();
                }
            }
        }

        // Remove from offline players
        for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
            String current = CustomStatus.prefix.get(p.getUniqueId());
            if (statusValue.equals(current)) {
                CustomStatus.prefix.remove(p.getUniqueId());

                Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                String teamName = "nick-" + p.getUniqueId().toString().substring(0, 8);
                Team team = scoreboard.getTeam(teamName);
                if (team != null) {
                    team.removeEntry(p.getName());
                    team.unregister();
                }
            }
        }

        CustomStatus.prefixs.remove(statusKey);
        SafeManager.SafeAll(CustomStatus.getInstance().getConfig(), CustomStatus.getInstance());

        sender.sendMessage(tm.getTranslation("status_removed", lang));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length != 1) return null;

        String partial = args[0].toLowerCase();
        List<String> matches = new ArrayList<>();

        for (String status : CustomStatus.prefixs.keySet()) {
            if (status.toLowerCase().startsWith(partial)) {
                matches.add(status.toLowerCase());
            }
        }

        return matches;
    }
}
