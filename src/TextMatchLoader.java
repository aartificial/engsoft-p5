import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TextMatchLoader implements MatchLoader {
    @Override
    public Match load() {
        Coach localCoach = new Coach();
        Coach visitorCoach = new Coach();
        List<Player> localLineup = new ArrayList<>();
        List<Player> localBench = new ArrayList<>();
        List<Player> visitorLineup = new ArrayList<>();
        List<Player> visitorBench = new ArrayList<>();

        Scanner scanner;
        try {
            scanner = new Scanner(new File(Objects.requireNonNull(this.getClass().getResource("match_setup.txt")).getPath()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Referee ref1 = new Referee();
        Referee ref2 = new Referee();
        Team local = readTeam(localCoach, localLineup, localBench, scanner);
        Team visitor = readTeam(visitorCoach, visitorLineup, visitorBench, scanner);

        return new Match(local, visitor, ref1, ref2);
    }

    private Team readTeam(Coach coach, List<Player> lineup, List<Player> bench, Scanner scanner) {
        int lineupSize = scanner.nextInt();
        int benchSize = scanner.nextInt();
        for (int i = 0; i < lineupSize; i++) {
            try {
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                lineup.add(createPlayer(scanner.nextLine()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < benchSize; i++) {
            try {
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                bench.add(createPlayer(scanner.nextLine()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new Team(coach, lineup, bench, true);
    }

    private Player createPlayer(String descriptor) {
        return new Player(RoleSelector.createRole(descriptor));
    }
}
