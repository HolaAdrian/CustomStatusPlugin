package de.adrian.customStatus;

import Utility.Importer;
import Utility.SafeManager;
import de.adrian.customStatus.commands.AddStatus;
import de.adrian.customStatus.commands.DeleteStatus;
import de.adrian.customStatus.commands.RemoveStatus;
import de.adrian.customStatus.commands.StatusCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class CustomStatus extends JavaPlugin {

    public static HashMap<UUID, String> prefix = new HashMap<>();

    public static HashMap<String, String> prefixs = new HashMap<>();



    @Override
    public void onEnable() {

        getLogger().info("CustomStatus plugin is being enabled...");

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
