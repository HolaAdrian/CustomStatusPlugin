package Utility;

import de.adrian.customStatus.CustomStatus;
import de.adrian.customStatus.commands.AddStatus;
import de.adrian.customStatus.commands.DeleteStatus;
import de.adrian.customStatus.commands.RemoveStatus;
import de.adrian.customStatus.commands.StatusCommand;
import de.adrian.customStatus.listeners.PlayerJoinListener;
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
    }


    static void ImportCommands(CustomStatus main){
        main.getCommand("status").setExecutor(new StatusCommand());
        main.getCommand("removestatus").setExecutor(new RemoveStatus());
        main.getCommand("addstatus").setExecutor(new AddStatus());
        main.getCommand("deletestatus").setExecutor(new DeleteStatus());
    }

    static void ImportListeners(PluginManager pluginManager, CustomStatus main){
        pluginManager.registerEvents(new PlayerJoinListener(), main);
    }
}
