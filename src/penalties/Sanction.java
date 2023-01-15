package penalties;

import container.Team;
import entity.Player;

public interface Sanction {
    void apply(Player player, Team team);
}
