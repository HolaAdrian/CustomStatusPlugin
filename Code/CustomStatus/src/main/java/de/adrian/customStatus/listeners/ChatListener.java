package de.adrian.customStatus.Listeners;

import de.adrian.customStatus.CustomStatus;
import de.adrian.customStatus.Utility.TranslationManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String uuid = player.getUniqueId().toString();

        if (message.contains(" ")) {
            return;
        }

        if (CustomStatus.creating.containsKey(player.getUniqueId())) {
            String stage = CustomStatus.creating.get(player.getUniqueId());
            String lang = CustomStatus.getInstance().getLanguage();
            TranslationManager tm = CustomStatus.getTranslationManager();

            if (stage.equals("A")) {
                if (message.equalsIgnoreCase("CANCEL")) {
                    event.setCancelled(true);
                    player.sendMessage(tm.getTranslation("creation_cancelled", lang));
                    CustomStatus.prefixname.remove(player.getUniqueId());
                    CustomStatus.creating.remove(player.getUniqueId());
                    return;
                }

                CustomStatus.prefixname.put(player.getUniqueId(), message.toLowerCase());
                CustomStatus.creating.put(player.getUniqueId(), "B");
                event.setCancelled(true);
                player.sendMessage(tm.getTranslation("enter_prefix", lang));
                return;
            }

            if (stage.equals("B")) {
                if (message.equalsIgnoreCase("CANCEL")) {
                    event.setCancelled(true);
                    player.sendMessage(tm.getTranslation("creation_cancelled", lang));
                    CustomStatus.prefixname.remove(player.getUniqueId());
                    CustomStatus.creating.remove(player.getUniqueId());
                    return;
                }

                String name = CustomStatus.prefixname.get(player.getUniqueId());
                CustomStatus.prefixname.remove(player.getUniqueId());
                CustomStatus.creating.remove(player.getUniqueId());
                event.setCancelled(true);
                player.performCommand("addstatus " + name + " " + message);
            }
        }
    }
}
