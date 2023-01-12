import container.Match;
import container.Team;
import entity.Coach;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MatchLoader loader = new TextMatchLoader();
        Match match = loader.load();

        printMenu();
        selectOptions(match);
    }

    private static void selectOptions(Match match) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            int option = scanner.nextInt() ;
            switch (option) {
                case 0: coachNotifyPlayers(match); break;
                //case 1: refereeExcludePlayer(); break;
                //case 2: coachSwapPlayers(); break;
                default: exit = true;
            }
        }
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
        System.out.println("[0] Entrenador notifica als jugadors de la pista.");
        System.out.println("[1] Arbitre exclou al jugador X.");
        System.out.println("[2] Entrenador ordena que el Jugador X substitueix al jugador Y.");
        System.out.println("[*] Sortir.");
    }
}