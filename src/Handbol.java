import java.util.List;
import java.util.Scanner;

public class Handbol {
    public static void main(String[] args) {
        var loader = new TextMatchLoader();
        var match = loader.load();
        selectOptions(match);
    }

    private static void selectOptions(Match match) {
        var scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            printMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> coachNotifyPlayers(match, scanner);
                case 2 -> refereeExcludePlayer(match, scanner);
                case 3 -> coachSwapPlayers(match, scanner);
                case 0 -> exit = true;
                default -> {}
            }
        }
    }

    private static void refereeExcludePlayer(Match match, Scanner scanner) {
        var team = selectTeamFromMatch(match, scanner);

        if (team.lineup().isEmpty()) {
            System.out.println("No queda cap jugador a la pista.");
            return;
        }

        var playerToExclude = selectElementFromList(team.lineup(), scanner);
        var referee = selectElementFromList(match.referees(), scanner);
        if (playerToExclude != null && referee != null)
            referee.applySanction(team, playerToExclude, new Exclusion());
    }

    private static void coachSwapPlayers(Match match, Scanner scanner) {
        var team = selectTeamFromMatch(match, scanner);
        System.out.println("Escull un jugador de la pista:");
        var lineupPlayer = selectElementFromList(team.lineup(), scanner);
        System.out.println("Escull un jugador de la banqueta:");
        var benchPlayer = selectElementFromList(team.bench(), scanner);
        System.out.println("Introdueix un rol:");
        scanner.nextLine();
        String mode = scanner.next();
        String function = scanner.next();
        String position = scanner.next();
        var newRole = RoleSelector.createRole(mode, function, position);
        if (lineupPlayer != null && !(lineupPlayer.role() instanceof Goalkeeper) && newRole instanceof Goalkeeper) {
            System.out.println("No es pot tenir dos porters a la pista del mateix equip.");
            return;
        }

        if (benchPlayer != null) {
            System.out.println("Jugador " + benchPlayer + " s'ha mogut a la pista amb el nou rol " + benchPlayer.role());
            team.swapPlayers(lineupPlayer, benchPlayer, newRole);
            System.out.println("Jugador " + lineupPlayer + " s'ha mogut a la banqueta.");
        }
    }

    private static <T> T selectElementFromList(List<T> list, Scanner scanner) {
        if (list.isEmpty()) return null;

        int selection = Integer.MAX_VALUE;
        while (selection >= list.size()) {
            for (int i = 0; i < list.size(); i++)
                System.out.println("[" + i + "] " + list.get(i));
            selection = scanner.nextInt();
        }
        return list.get(selection);
    }

    private static void coachNotifyPlayers(Match match, Scanner scanner) {
        var team = selectTeamFromMatch(match, scanner);
        var coach = team.coach();
        System.out.println("Introdueix el missatge:");
        scanner.nextLine();
        String message = scanner.nextLine();
        coach.notifyLineup(message);
    }

    private static Team selectTeamFromMatch(Match match, Scanner scanner) {
        System.out.println("Escull un equip del partit:");
        System.out.println("[0] Local:   " + match.localTeam());
        System.out.println("[1] Visitant: " + match.visitorTeam());
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