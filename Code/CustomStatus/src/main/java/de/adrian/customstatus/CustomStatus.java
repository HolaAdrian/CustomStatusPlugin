package de.adrian.customStatus;

import de.adrian.customStatus.Utility.Importer;
import de.adrian.customStatus.Utility.SafeManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class CustomStatus extends JavaPlugin {

    public static HashMap<UUID, String> prefix = new HashMap<>();
    public static HashMap<String, String> prefixs = new HashMap<>();
    public static HashMap<UUID, String> creating = new HashMap<>();
    public static HashMap<UUID, String> prefixname = new HashMap<>();
    public static Boolean use;
    public static Boolean joinMessageNormal;

    private static CustomStatus instance;

    @Override
    public void onEnable() {
        instance = this;
        joinMessageNormal = getConfig().getBoolean("useNormalJoinMessage", true);
        use = getConfig().getBoolean("usePlayerNamePrefix", true);

        getLogger().info("CustomStatus plugin is being enabled...");

        Importer.ImportAll(Bukkit.getPluginManager(), this);

        if (!getConfig().contains("prefixs")) {
            saveDefaultConfig();
            SafeManager.SafeAll(getConfig(), this);
        } else {
            SafeManager.LoadAll(getConfig());
        }
    }

    @Override
    public void onDisable() {
        SafeManager.SafeAll(getConfig(), this);
    }

    public static CustomStatus getInstance() {
        return instance;
    }
}