package de.adrian.customstatus;

import Utility.Importer;
import Utility.SafeManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class CustomStatus extends JavaPlugin {

    public static HashMap<UUID, String> prefix = new HashMap<>();

    public static HashMap<String, String> prefixs = new HashMap<>();



    @Override
    public void onEnable() {

        Importer.ImportAll(Bukkit.getPluginManager(), this);
        if (!getConfig().contains("prefixs")){
            saveDefaultConfig();
        }
        else {
            SafeManager.LoadAll(getConfig());
        }

    }

    @Override
    public void onDisable() {
        SafeManager.SafeAll(getConfig(), this);
    }
}
