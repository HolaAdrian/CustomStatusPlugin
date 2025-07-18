package de.adrian.customStatus.Commands;

import de.adrian.customStatus.CustomStatus;
import de.adrian.customStatus.Utility.TranslationManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AddStatus implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        TranslationManager tm = CustomStatus.getTranslationManager();
        String lang = CustomStatus.getInstance().getLanguage();

        if (!sender.hasPermission("status.addstatus")) {
            sender.sendMessage(tm.getTranslation("no_permission", lang));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(tm.getTranslation("addstatus_syntax", lang));
            return false;
        }

        // Combine all arguments after the first as the prefix string
        StringBuilder prefixBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            prefixBuilder.append(args[i]).append(" ");
        }
        String prefix = ChatColor.translateAlternateColorCodes('&', prefixBuilder.toString());

        CustomStatus.prefixs.put(args[0].toLowerCase(), prefix);

        // Send success message replacing {0} with status name
        sender.sendMessage(tm.getTranslation("status_added", lang).replace("{0}", args[0]));

        return true;
    }
}
