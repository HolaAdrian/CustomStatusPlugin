package de.adrian.customStatus.Utility;

import de.adrian.customStatus.Commands.*;
import de.adrian.customStatus.CustomStatus;
import de.adrian.customStatus.Listeners.ChatListener;
import de.adrian.customStatus.Listeners.InventoryListener;
import de.adrian.customStatus.Listeners.PlayerJoinListener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

public class Importer {

    public static void ImportAll(PluginManager pluginManager, CustomStatus main){
        ImportPermission(pluginManager);
        ImportCommands(main);
        ImportListeners(pluginManager, main);
    }

    static void ImportPermission(PluginManager pluginManager){
        pluginManager.addPermission(new Permission("status.removestatus"));
        pluginManager.addPermission(new Permission("status.addstatus"));
        pluginManager.addPermission(new Permission("status.deletestatus"));
        pluginManager.addPermission(new Permission("status.setstatus"));
        pluginManager.addPermission(new Permission("status.admin"));
    }


    static void ImportCommands(CustomStatus main){
        main.getCommand("status").setExecutor(new StatusCommand());
        main.getCommand("removestatus").setExecutor(new RemoveStatus());
        main.getCommand("addstatus").setExecutor(new AddStatus());
        main.getCommand("deletestatus").setExecutor(new DeleteStatus());
        main.getCommand("adminpanel").setExecutor(new AdminCommand());
    }

    static void ImportListeners(PluginManager pluginManager, CustomStatus main){
        pluginManager.registerEvents(new PlayerJoinListener(), main);
        pluginManager.registerEvents(new InventoryListener(), main);
        pluginManager.registerEvents(new ChatListener(), main);
    }

}
