import container.Match;
import container.Team;
import entity.Coach;
import entity.Player;
import entity.Referee;
import role.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*Coach localCoach = new Coach();
        Player lGoalkeeper = new Player(new Goalkeeper(new BaseRole()));
        Player lPlayer1 = new Player(new Defense(new Central(new Left(new BaseRole()))));
        Player lPlayer2 = new Player(new Defense(new Central(new Right(new BaseRole()))));
        Player lPlayer3 = new Player(new Defense(new Lateral(new Left(new BaseRole()))));
        Player lPlayer4 = new Player(new Defense(new Lateral(new Right(new BaseRole()))));
        List<Player> localLineup = Arrays.asList(lGoalkeeper, lPlayer1, lPlayer2, lPlayer3, lPlayer4);
        Player lGoalkeeper1 = new Player(new Goalkeeper(new BaseRole()));
        Player lPlayer5 = new Player(new Defense(new Central(new Right(new BaseRole()))));
        Player lPlayer6 = new Player(new Defense(new Central(new Left(new BaseRole()))));
        Player lPlayer7 = new Player(new Defense(new Central(new Left(new BaseRole()))));
        Player lPlayer8 = new Player(new Defense(new Lateral(new Left(new BaseRole()))));
        Player lPlayer9 = new Player(new Defense(new Lateral(new Right(new BaseRole()))));
        List<Player> localBench = Arrays.asList(lGoalkeeper1, lPlayer5, lPlayer6, lPlayer7, lPlayer8, lPlayer9);
        Team local = new Team(localCoach, localLineup, localBench);

        Coach visitorCoach = new Coach();
        Player vGoalkeeper = new Player(new Goalkeeper(new BaseRole()));
        Player vPlayer1 = new Player(new Attack(new Central(new Left(new BaseRole()))));
        Player vPlayer2 = new Player(new Attack(new Central(new Right(new BaseRole()))));
        Player vPlayer3 = new Player(new Attack(new Lateral(new Left(new BaseRole()))));
        Player vPlayer4 = new Player(new Attack(new Lateral(new Right(new BaseRole()))));
        List<Player> visitorLineup = Arrays.asList(vGoalkeeper, vPlayer1, vPlayer2, vPlayer3, vPlayer4);
        Player vGoalkeeper1 = new Player(new Goalkeeper(new BaseRole()));
        Player vPlayer5 = new Player(new Attack(new Lateral(new Left(new BaseRole()))));
        Player vPlayer6 = new Player(new Attack(new Lateral(new Right(new BaseRole()))));
        Player vPlayer7 = new Player(new Attack(new Lateral(new Right(new BaseRole()))));
        Player vPlayer8 = new Player(new Attack(new Central(new Left(new BaseRole()))));
        Player vPlayer9 = new Player(new Attack(new Central(new Right(new BaseRole()))));
        List<Player> visitorBench = Arrays.asList(vGoalkeeper1, vPlayer5, vPlayer6, vPlayer7, vPlayer8, vPlayer9);
        Team visitor = new Team(visitorCoach, visitorLineup, visitorBench);

        Referee ref1 = new Referee();
        Referee ref2 = new Referee();
        Match match = new Match(local, visitor, ref1, ref2);*/

        Match match = new TextMatchLoader().load();

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