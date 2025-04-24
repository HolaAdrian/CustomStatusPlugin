package Utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import de.adrian.customStatus.CustomStatus;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TabListUtils {

    public static void SetPlayerTabListPrefix(Player player){

        if (CustomStatus.prefix.containsKey(player.getUniqueId())){
            player.setPlayerListName(CustomStatus.prefix.get(player.getUniqueId()) + player.getName());
            String nick = CustomStatus.prefix.get(player.getUniqueId());;


            if (CustomStatus.use != null){
                if (CustomStatus.use){
                    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                    String teamName = "nick-" + player.getUniqueId().toString().substring(0, 8);

                    Team oldTeam = scoreboard.getTeam(teamName);
                    if (oldTeam != null) {
                        oldTeam.removeEntry(player.getName());
                        oldTeam.unregister();
                    }

                    Team team = scoreboard.registerNewTeam(teamName);
                    team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);

                    team.setPrefix(ChatColor.translateAlternateColorCodes('&', nick) + " ");
                    team.setSuffix("");
                    team.addEntry(player.getName());
                }

            }
        }



    }


}
