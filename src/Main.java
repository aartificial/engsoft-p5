import container.Match;
import container.Team;
import entity.Coach;
import entity.Player;
import entity.Referee;
import penalties.Exclusion;
import role.Goalkeeper;
import role.Role;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MatchLoader loader = new TextMatchLoader();
        Match match = loader.load();

        selectOptions(match);
    }

    private static void selectOptions(Match match) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            printMenu();
            int option = scanner.nextInt() ;
            switch (option) {
                case 1 -> coachNotifyPlayers(match);
                case 2 -> refereeExcludePlayer(match);
                case 3 -> coachSwapPlayers(match);
                case 0 -> exit = true;
                default -> {}
            }
        }
    }

    private static void refereeExcludePlayer(Match match) {
        Team team = selectTeamFromMatch(match);
        Player playerToExclude = selectElementFromList(team.lineup());
        Referee referee = selectElementFromList(match.referees());
        referee.applySanction(team, playerToExclude, new Exclusion());
    }

    private static void coachSwapPlayers(Match match) {
        Team team = selectTeamFromMatch(match);
        Player lineupPlayer = selectElementFromList(team.lineup());
        Player benchPlayer = selectElementFromList(team.bench());
        System.out.println("Introdueix un rol:");
        Scanner scanner = new Scanner(System.in);
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        String roleDescription = scanner.nextLine();

        Role newRole = RoleSelector.createRole(roleDescription);
        if (!(lineupPlayer.role() instanceof Goalkeeper) && newRole instanceof Goalkeeper) {
            System.out.println("No es pot tenir dos porters a la pista del mateix equip.");
            return;
        }

        team.swapPlayers(lineupPlayer, benchPlayer, newRole);
        System.out.println("Jugador " + lineupPlayer + " s'ha mogut a la banqueta.");
        System.out.println("Jugador " + benchPlayer + " s'ha mogut a la pista amb el nou rol " + benchPlayer.role());
    }

    private static <T> T selectElementFromList(List<T> list) {
        int selection = Integer.MAX_VALUE;
        while (selection >= list.size()) {
            for (int i = 0; i < list.size(); i++)
                System.out.println("[" + i + "] " + list.get(i));
            Scanner scanner = new Scanner(System.in);
            selection = scanner.nextInt();
        }
        return list.get(selection);
    }

    private static void coachNotifyPlayers(Match match) {
        Team team = selectTeamFromMatch(match);
        Coach coach = team.coach();
        System.out.println("Introdueix el missatge:");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.next();
        coach.notifyLineup(message);
    }

    private static Team selectTeamFromMatch(Match match) {
        System.out.println("[0] Local:   " + match.localTeam());
        System.out.println("[1] Visitant: " + match.visitorTeam());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt() == 0 ? match.localTeam() : match.visitorTeam();
    }

    private static void printMenu() {
        System.out.println("Selecciona cas d'us:");
        System.out.println("[1] Entrenador notifica als jugadors de la pista.");
        System.out.println("[2] Arbitre exclou al jugador X.");
        System.out.println("[3] Entrenador ordena que el Jugador X substitueix al jugador Y.");
        System.out.println("[0] Sortir.");
    }
}