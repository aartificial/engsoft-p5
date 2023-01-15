package container;

import entity.Coach;
import entity.Player;
import event.EventBus;
import role.Attack;
import role.Defense;
import role.Goalkeeper;
import role.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team {
    private int MAX_LINEUP_SIZE = 6;
    private final int MAX_BENCH_SIZE = 6;
    private Coach coach;
    private List<Player> lineup = new ArrayList<>();
    private List<Player> bench = new ArrayList<>();
    private boolean defense;

    public Team(Coach coach, List<Player> lineup, List<Player> bench, boolean defense) {
        this.coach = coach;
        this.defense = defense;
        addPlayersToTeam(lineup, bench);
    }

    private void addPlayersToTeam(List<Player> lineup, List<Player> bench) {
        for (List<Player> playerList :
                Arrays.asList(lineup, bench)) {
            for (Player player:
                 playerList) {
                EventBus.subscribe(coach + "listenTeam", player.toString(), player::listenTeam);
            }
        }

        addPlayersToLineup(lineup);
        addPlayersToBench(bench);
    }

    private void addPlayersToBench(List<Player> playerList) {
        for (Player player:
                playerList) {
            if (bench.size() >= MAX_BENCH_SIZE) break;

            addPlayerToBench(player);
        }
    }

    public void removePlayerFromBench(Player player) {
        bench.remove(player);
        EventBus.unsubscribe(coach + "listenBench", player.toString());
    }

    private void addPlayersToLineup(List<Player> playerList) {
        boolean goalkeeperCheck = false;
        for (Player player:
             playerList) {
            if (lineup.size() >= MAX_LINEUP_SIZE) break;

            if (player.role() instanceof Defense && !defense) {
                EventBus.unsubscribe(coach + "listenTeam", player.toString());
                System.out.println("No es pot afegir un jugador a la pista amb rol defensiu quan l'equip esta atacant.");
                continue;
            } else if (player.role() instanceof Attack && defense) {
                EventBus.unsubscribe(coach + "listenTeam", player.toString());
                System.out.println("No es pot afegir un jugador a la pista amb rol d'atac quan l'equip esta defensant.");
                continue;
            }

            if (player.role() instanceof Goalkeeper) {
                if (goalkeeperCheck) {
                    EventBus.unsubscribe(coach + "listenTeam", player.toString());
                    System.out.println("No es poden afegir dos porters a la pista en el mateix equip.");
                    continue;
                }
                goalkeeperCheck = true;
            }

            addPlayerToLineup(player);
        }
    }

    public void removePlayerFromLineup(Player player) {
        lineup.remove(player);
        EventBus.unsubscribe(coach + "listenLineup", player.toString());
    }

    public Coach coach() {
        return coach;
    }

    public List<Player> lineup() {
        return lineup;
    }

    public List<Player> bench() {
        return bench;
    }

    public void swapPlayers(Player lineupPlayer, Player benchPlayer, Role role) {
        removePlayerFromLineup(lineupPlayer);
        removePlayerFromBench(benchPlayer);
        benchPlayer.setRole(role);
        addPlayerToBench(lineupPlayer);
        addPlayerToLineup(benchPlayer);

    }

    private void addPlayerToLineup(Player player) {
        lineup.add(player);
        EventBus.subscribe(coach + "listenLineup", player.toString(), player::listenLineup);
    }

    private void addPlayerToBench(Player player) {
        bench.add(player);
        EventBus.subscribe(coach + "listenBench", player.toString(), player::listenBench);
    }

    public void increaseLineupSize(int diff) {
        if (diff > 1 || diff < -1) {
            System.out.println("Mida de la pista ha canviat des de " + MAX_LINEUP_SIZE + " a " + MAX_LINEUP_SIZE + diff);
            MAX_LINEUP_SIZE += diff;
        }
    }
}
