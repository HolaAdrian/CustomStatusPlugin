package Utility;

import org.bukkit.entity.Player;
import de.adrian.customStatus.CustomStatus;

public class TabListUtils {

    public static void SetPlayerTabListPrefix(Player player){

        if (CustomStatus.prefix.containsKey(player.getUniqueId())){
            player.setPlayerListName(CustomStatus.prefix.get(player.getUniqueId()) + player.getName());
        }

    }


}
