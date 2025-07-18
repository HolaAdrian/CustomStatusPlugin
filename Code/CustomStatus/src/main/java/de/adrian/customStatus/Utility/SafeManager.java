package de.adrian.customStatus.Utility;

import de.adrian.customStatus.CustomStatus;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class SafeManager {

    public static void SafeAll(FileConfiguration config, CustomStatus main) {
        // Clear existing sections to avoid duplicates
        config.set("prefix", null);
        config.set("prefixs", null);

        // Save player prefixes
        for (UUID uuid : CustomStatus.prefix.keySet()) {
            String prefixValue = CustomStatus.prefix.get(uuid);
            if (prefixValue != null) {
                config.set("prefix." + uuid.toString(), prefixValue);
            }
        }

        // Save available status prefixes
        for (String statusKey : CustomStatus.prefixs.keySet()) {
            String statusValue = CustomStatus.prefixs.get(statusKey);
            if (statusValue != null) {
                config.set("prefixs." + statusKey, statusValue);
            }
        }

        // Save settings
        config.set("usePlayerNamePrefix", CustomStatus.use);
        config.set("useNormalJoinMessage", CustomStatus.joinMessageNormal);
        config.set("language", main.getLanguage()); // Save current language

        // Config header with improved formatting
        config.options().header(
                "CustomStatus Configuration\n" +
                        "=========================\n" +
                        "Permissions:\n" +
                        "- status.setstatus: Set your status\n" +
                        "- status.removestatus: Remove someone's Status\n" +
                        "- status.deletestatus: Delete a status\n" +
                        "- status.addstatus: Add a status to the Server\n\n" +
                        "Notes:\n" +
                        "- You can use color codes (&) in the Statuses\n" +
                        "- usePlayerNamePrefix: Shows status before player name (F5 list)\n" +
                        "- language: Set to 'en' for English or 'de' for German"
        );
        config.options().copyHeader(true);

        main.saveConfig();
    }

    public static void LoadAll(FileConfiguration config) {
        // Clear existing data
        CustomStatus.prefix.clear();
        CustomStatus.prefixs.clear();

        // Load language setting with validation
        String language = config.getString("language", "en");
        if (!language.equals("en") && !language.equals("de")) {
            CustomStatus.getInstance().getLogger().warning(
                    "Invalid language '" + language + "' in config, defaulting to 'en'"
            );
            language = "en";
        }
        CustomStatus.getInstance().setLanguage(language);

        // Load player prefixes
        if (config.getConfigurationSection("prefix") != null) {
            for (String uuidString : config.getConfigurationSection("prefix").getKeys(false)) {
                try {
                    UUID playerUUID = UUID.fromString(uuidString);
                    String prefix = config.getString("prefix." + uuidString);
                    if (prefix != null) {
                        CustomStatus.prefix.put(playerUUID, prefix);
                    }
                } catch (IllegalArgumentException e) {
                    CustomStatus.getInstance().getLogger().warning(
                            "Invalid UUID in config: " + uuidString
                    );
                }
            }
        }

        // Load available statuses
        if (config.getConfigurationSection("prefixs") != null) {
            for (String statusKey : config.getConfigurationSection("prefixs").getKeys(false)) {
                String statusValue = config.getString("prefixs." + statusKey);
                if (statusValue != null) {
                    CustomStatus.prefixs.put(statusKey.toLowerCase(), statusValue);
                }
            }
        }

        // Load other settings with defaults
        CustomStatus.use = config.getBoolean("usePlayerNamePrefix", true);
        CustomStatus.joinMessageNormal = config.getBoolean("useNormalJoinMessage", true);
    }
}