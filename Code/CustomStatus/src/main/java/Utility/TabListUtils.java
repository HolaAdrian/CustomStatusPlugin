package Utility;

import de.adrian.customstatus.CustomStatus;
import org.bukkit.entity.Player;

public class TabListUtils{

    public static void SetPlayerTabListPrefix(Player player){

        if (CustomStatus.prefix.containsKey(player.getUniqueId())){
            player.setPlayerListName(CustomStatus.prefix.get(player.getUniqueId()) + player.getName());
        }

    }

}
