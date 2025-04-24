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
import java.util.logging.Logger;

public final class CustomStatus extends JavaPlugin {

    public static HashMap<UUID, String> prefix = new HashMap<>();

    public static HashMap<String, String> prefixs = new HashMap<>();

    public static Boolean use;

    public static Boolean joinMessageNormal;



    @Override
    public void onEnable() {

        joinMessageNormal = getConfig().getBoolean("useNormalJoinMessage", true);

        use = getConfig().getBoolean("usePlayerNamePrefix", true);


        getLogger().info("CustomStatus plugin is being enabled...");

        Importer.ImportAll(Bukkit.getPluginManager(), this);
        if (!getConfig().contains("prefixs")){
            saveDefaultConfig();
            SafeManager.SafeAll(getConfig(), this);
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
