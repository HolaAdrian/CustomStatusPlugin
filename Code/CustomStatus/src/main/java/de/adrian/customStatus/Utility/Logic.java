package de.adrian.customStatus.Utility;

import de.adrian.customStatus.CustomStatus;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class Logic {

    public static void DeleteStatus(Player p) {
        String language = CustomStatus.getInstance().getLanguage();
        TranslationManager translationManager = CustomStatus.getTranslationManager();

        // Send header message
        TextComponent delete = new TextComponent(translationManager.getTranslation(
                "select_status_to_delete",
                language
        ));
        p.spigot().sendMessage(delete);

        // Send clickable status list
        for (String status : CustomStatus.prefixs.keySet()) {
            TextComponent statusComponent = new TextComponent(status);
            statusComponent.setClickEvent(new ClickEvent(
                    ClickEvent.Action.RUN_COMMAND,
                    "/deletestatus " + status
            ));
            statusComponent.setHoverEvent(new HoverEvent(
                    HoverEvent.Action.SHOW_TEXT,
                    TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&',
                            translationManager.getTranslation(
                                    "delete_status_confirmation",
                                    language
                            ) + status)
                    )
            ));
            p.spigot().sendMessage(statusComponent);
        }
    }

    public static void RemoveStatus(Player p) {
        String language = CustomStatus.getInstance().getLanguage();
        TranslationManager translationManager = CustomStatus.getTranslationManager();

        // Send header message
        TextComponent header = new TextComponent(translationManager.getTranslation(
                "select_player_to_remove",
                language
        ));
        p.spigot().sendMessage(header);

        // Send clickable player list
        for (UUID uuid : CustomStatus.prefix.keySet()) {
            String playerName = Objects.requireNonNullElse(
                    Bukkit.getOfflinePlayer(uuid).getName(),
                    "Unknown Player"
            );

            TextComponent playerComponent = new TextComponent(playerName);
            playerComponent.setClickEvent(new ClickEvent(
                    ClickEvent.Action.RUN_COMMAND,
                    "/removestatus " + playerName
            ));
            playerComponent.setHoverEvent(new HoverEvent(
                    HoverEvent.Action.SHOW_TEXT,
                    TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&',
                            translationManager.getTranslation(
                                    "remove_status_confirmation",
                                    language
                            ) + playerName)
                    )
            ));
            p.spigot().sendMessage(playerComponent);
        }
    }

    public static void CreateStatus(Player p) {
        String language = CustomStatus.getInstance().getLanguage();
        p.sendMessage(CustomStatus.getTranslationManager().getTranslation(
                "enter_name",
                language
        ));
        CustomStatus.prefixname.remove(p.getUniqueId());
        CustomStatus.creating.put(p.getUniqueId(), "A");
    }
}