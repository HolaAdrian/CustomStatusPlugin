package de.adrian.customStatus.Utility;

import de.adrian.customStatus.CustomStatus;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class SafeManager {

    public static void SafeAll(FileConfiguration config, CustomStatus main) {
        config.set("prefix", null);
        config.set("prefixs", null);

        for (UUID uuid : CustomStatus.prefix.keySet()) {
            String prefixValue = CustomStatus.prefix.get(uuid);
            if (prefixValue != null) {
                config.set("prefix." + uuid.toString(), prefixValue);
            }
        }

        for (String statusKey : CustomStatus.prefixs.keySet()) {
            String statusValue = CustomStatus.prefixs.get(statusKey);
            if (statusValue != null) {
                config.set("prefixs." + statusKey, statusValue);
            }
        }

        config.set("usePlayerNamePrefix", CustomStatus.use);
        config.set("useNormalJoinMessage", CustomStatus.joinMessageNormal);

        config.options().header(
                "#Permissions\n" +
                        "# -status.setstatus = Set your status\n" +
                        "# -status.removestatus = Remove someone's Status\n" +
                        "# -status.deletestatus = Delete your status\n" +
                        "# -status.addstatus = Add a status to the Server\n" +
                        "\n" +
                        "\n" +
                        "# You can use color codes in the Statuses\n" +
                        "\n" +
                        "# usePlayerNamePrefix Decides whether the status should also be visible in front of the name when pressing F5."
        );
        config.options().copyHeader(true);

        main.saveConfig();
    }

    public static void LoadAll(FileConfiguration config) {
        CustomStatus.prefix.clear();
        CustomStatus.prefixs.clear();

        if (config.getConfigurationSection("prefix") != null) {
            for (String uuidString : config.getConfigurationSection("prefix").getKeys(false)) {
                try {
                    UUID playerUUID = UUID.fromString(uuidString);
                    String prefix = config.getString("prefix." + uuidString);
                    if (prefix != null) {
                        CustomStatus.prefix.put(playerUUID, prefix);
                    }
                } catch (IllegalArgumentException e) {
                    CustomStatus.getInstance().getLogger().warning("Invalid UUID in config: " + uuidString);
                }
            }
        }

        if (config.getConfigurationSection("prefixs") != null) {
            for (String statusKey : config.getConfigurationSection("prefixs").getKeys(false)) {
                String statusValue = config.getString("prefixs." + statusKey);
                if (statusValue != null) {
                    CustomStatus.prefixs.put(statusKey.toLowerCase(), statusValue);
                }
            }
        }

        CustomStatus.use = config.getBoolean("usePlayerNamePrefix", true);
        CustomStatus.joinMessageNormal = config.getBoolean("useNormalJoinMessage", true);
    }
}