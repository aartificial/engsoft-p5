
public class Match {
    private final Team local;
    private final Team visitor;
    private final Referee ref1;
    private final Referee ref2;
    public Match(Team local, Team visitor, Referee ref1, Referee ref2) {
        this.local = local;
        this.visitor = visitor;
        this.ref1 = ref1;
        this.ref2 = ref2;
    }

    public Team localTeam() {
        return local;
    }

    public Team visitorTeam() {
        return visitor;
    }
}
