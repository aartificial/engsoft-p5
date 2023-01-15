package entity;

import container.Team;
import event.EventBus;

public class Coach {
    public Coach() {
        EventBus.create(this + "listenTeam");
        EventBus.create(this + "listenBench");
        EventBus.create(this + "listenLineup");
    }
    public void notifyLineup(String message) {
        EventBus.notify(this + "listenLineup", message);
    }
}
