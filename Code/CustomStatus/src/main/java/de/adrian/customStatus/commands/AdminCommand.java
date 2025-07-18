package de.adrian.customStatus.Commands;

import de.adrian.customStatus.CustomStatus;
import de.adrian.customStatus.Utility.ItemGranter;
import de.adrian.customStatus.Utility.TranslationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        TranslationManager tm = CustomStatus.getTranslationManager();
        String lang = CustomStatus.getInstance().getLanguage();

        if (!sender.hasPermission("status.admin")) {
            sender.sendMessage(tm.getTranslation("no_permission", lang));
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(tm.getTranslation("only_players", lang));
            return false;
        }

        Player player = (Player) sender;
        player.openInventory(ItemGranter.AdminInventory());

        return true;
    }
}
