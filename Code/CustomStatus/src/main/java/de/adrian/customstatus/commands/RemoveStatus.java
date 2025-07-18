package de.adrian.customStatus.Commands;

import de.adrian.customStatus.CustomStatus;
import de.adrian.customStatus.Utility.TranslationManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

public class RemoveStatus implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        TranslationManager tm = CustomStatus.getTranslationManager();
        String lang = CustomStatus.getInstance().getLanguage();

        if (!commandSender.hasPermission("status.removestatus")) {
            commandSender.sendMessage(tm.getTranslation("no_permission", lang));
            return false;
        }

        if (args.length != 1) {
            commandSender.sendMessage(tm.getTranslation("removestatus_syntax", lang));
            return false;
        }

        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        if (player == null || player.getName() == null) {
            commandSender.sendMessage(tm.getTranslation("player_not_found", lang));
            return false;
        }

        if (!CustomStatus.prefix.containsKey(player.getUniqueId())) {
            commandSender.sendMessage(tm.getTranslation("no_player_status", lang));
            return false;
        }

        // Remove from status tracking
        CustomStatus.prefix.remove(player.getUniqueId());

        // Cleanup if online
        if (player.isOnline()) {
            player.getPlayer().setPlayerListName(player.getName());

            Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            String teamName = "nick-" + player.getUniqueId().toString().substring(0, 8);
            Team team = scoreboard.getTeam(teamName);

            if (team != null) {
                team.removeEntry(player.getName());
                team.unregister();
            }
        }

        commandSender.sendMessage(tm.getTranslation("player_status_removed", lang, player.getName()));
        return true;
    }
}
