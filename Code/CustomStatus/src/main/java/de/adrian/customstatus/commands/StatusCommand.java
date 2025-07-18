package de.adrian.customStatus.Commands;

import de.adrian.customStatus.CustomStatus;
import de.adrian.customStatus.Utility.TabListUtils;
import de.adrian.customStatus.Utility.TranslationManager;
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
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        TranslationManager tm = CustomStatus.getTranslationManager();
        String lang = CustomStatus.getInstance().getLanguage();

        if (!commandSender.hasPermission("status.setstatus")) {
            commandSender.sendMessage(tm.getTranslation("no_permission", lang));
            return false;
        }

        if (args.length != 1) {
            commandSender.sendMessage(tm.getTranslation("status_syntax", lang));
            return false;
        }

        String statusKey = args[0].toLowerCase();
        if (!CustomStatus.prefixs.containsKey(statusKey)) {
            commandSender.sendMessage(tm.getTranslation("status_not_found", lang));
            return false;
        }

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(tm.getTranslation("must_be_player_set_status", lang));
            return false;
        }

        Player player = (Player) commandSender;
        CustomStatus.prefix.put(player.getUniqueId(), CustomStatus.prefixs.get(statusKey));
        TabListUtils.SetPlayerTabListPrefix(player);
        commandSender.sendMessage(tm.getTranslation("status_updated", lang));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            String input = args[0].toLowerCase();
            for (String status : CustomStatus.prefixs.keySet()) {
                if (status.toLowerCase().startsWith(input)) {
                    suggestions.add(status.toLowerCase());
                }
            }
        }

        return suggestions;
    }
}
