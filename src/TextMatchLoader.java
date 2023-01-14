import container.Match;
import container.Team;
import entity.Coach;
import entity.Player;
import entity.Referee;
import role.*;

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
        return new Team(coach, lineup, bench);
    }

    private Player createPlayer(String descriptor) throws Exception {
        Role role;
        switch(descriptor) {
            case "defensa extrem esquerra": role = new Defense(new Extreme(new Left(new BaseRole()))); break;
            case "defensa extrem dret": role = new Defense(new Extreme(new Right(new BaseRole()))); break;
            case "defensa lateral esquerra": role = new Defense(new Lateral(new Left(new BaseRole()))); break;
            case "defensa lateral dret": role = new Defense(new Lateral(new Right(new BaseRole()))); break;
            case "defensa central esquerra": role = new Defense(new Central(new Left(new BaseRole()))); break;
            case "defensa central dret": role = new Defense(new Central(new Right(new BaseRole()))); break;
            case "atac extrem esquerra": role = new Attack(new Extreme(new Left(new BaseRole()))); break;
            case "atac extrem dret": role = new Attack(new Extreme(new Right(new BaseRole()))); break;
            case "atac lateral esquerra": role = new Attack(new Lateral(new Left(new BaseRole()))); break;
            case "atac lateral dret": role = new Attack(new Lateral(new Right(new BaseRole()))); break;
            case "atac central": role = new Attack(new Central(new BaseRole())); break;
            case "atac pivot": role = new Attack(new Pivot(new BaseRole())); break;
            case "porter": role = new Goalkeeper(new BaseRole()); break;
            default: throw new Exception("Bad input while creating player with role: [" + descriptor + "]");

        }
        return new Player(role);
    }
}
