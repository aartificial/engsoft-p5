public class Coach {
    public Coach() {
        EventBus.create(this.getClass() + "listenTeam");
        EventBus.create(this.getClass() + "listenBench");
        EventBus.create(this.getClass() + "listenLineup");
    }
    public void notifyLineup(String message) {
        EventBus.notify(this.getClass() + "listenLineup", message);
    }
}
