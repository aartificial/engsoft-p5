package entity;

import container.Team;
import penalties.Sanction;

public class Referee {
    public void applySanction(Team team, Player player, Sanction sanction) {
        System.out.println("Sanction " + sanction + " applied to " + player);
        sanction.apply(player, team);
    }
}
