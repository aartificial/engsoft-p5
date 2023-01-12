package container;

import entity.Coach;
import entity.Player;
import event.EventBus;
import role.Goalkeeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team {
    private final int MAX_LINEUP_SIZE = 6;
    private final int MAX_BENCH_SIZE = 6;
    private Coach coach;
    private List<Player> lineup = new ArrayList<>();
    private List<Player> bench = new ArrayList<>();

    public Team(Coach coach, List<Player> lineup, List<Player> bench) {
        this.coach = coach;
        addPlayersToTeam(lineup, bench);
    }

    private void addPlayersToTeam(List<Player> lineup, List<Player> bench) {
        for (List<Player> playerList :
                Arrays.asList(lineup, bench)) {
            for (Player player:
                 playerList) {
                EventBus.subscribe(coach + "listenTeam", player::listenTeam);
            }
        }

        addPlayersToLineup(lineup);
        addPlayersToBench(bench);
    }

    private void addPlayersToBench(List<Player> playerList) {
        for (Player player:
                playerList) {
            if (bench.size() >= MAX_BENCH_SIZE) break;

            bench.add(player);
            EventBus.subscribe(coach + "listenBench", player::listenBench);
        }
    }

    private void removePlayerFromBench(Player player) {
        bench.remove(player);
        EventBus.unsubscribe(coach + "listenBench", player::listenBench);
    }

    private void addPlayersToLineup(List<Player> playerList) {
        boolean goalkeeperCheck = false;
        for (Player player:
             playerList) {
            if (lineup.size() >= MAX_LINEUP_SIZE) break;

            if (player.role() instanceof Goalkeeper) {
                if (goalkeeperCheck) {
                    EventBus.unsubscribe(coach + "listenTeam", player::listenTeam);
                    continue;
                }
                goalkeeperCheck = true;
            }

            lineup.add(player);
            EventBus.subscribe(coach + "listenLineup", player::listenLineup);
        }
    }

    private void removePlayerFromLineup(Player player) {
        lineup.remove(player);
        EventBus.unsubscribe(coach + "listenLineup", player::listenLineup);
    }

    public Coach coach() {
        return coach;
    }
}
