package Utility;

import de.adrian.customStatus.CustomStatus;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class SafeManager {

    public static void SafeAll(FileConfiguration config, CustomStatus main){

        for (UUID uuid: CustomStatus.prefix.keySet()){
            config.set("prefix." + uuid, CustomStatus.prefix.get(uuid));
        }

        for (String string: CustomStatus.prefixs.keySet()){
            config.set("prefixs." + string, CustomStatus.prefixs.get(string));
        }

        main.saveConfig();





    }

    public static void LoadAll(FileConfiguration config){

        if (config.getConfigurationSection("prefix") != null){
            for (String uuid: config.getConfigurationSection("prefix").getKeys(false)){
                UUID UUID = java.util.UUID.fromString(uuid);
                String prefix  = config.getString("prefix." + uuid);
                CustomStatus.prefix.put(UUID, prefix);

            }
        }

        if (config.getConfigurationSection("prefixs") != null){
            for (String uuid: config.getConfigurationSection("prefixs").getKeys(false)){
                String prefixs  = config.getString("prefixs." + uuid);
                CustomStatus.prefixs.put(uuid, prefixs);

            }
        }

    }


}
