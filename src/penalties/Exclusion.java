package penalties;

import container.Team;
import entity.Player;

public class Exclusion implements Sanction {
    private float duration = 2;
    @Override
    public void apply(Player player, Team team) {
        team.removePlayerFromLineup(player);
        System.out.println(player + " removed from lineup of " + team + " for 2 minutes.");
        team.increaseLineupSize(-1);
        // in "duration" reverse the size diff
    }
}
