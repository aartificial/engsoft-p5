package entity;

import role.Role;

public class Player {

    private final Role role;

    public Player(Role role) {
        this.role = role;
    }

    public Role role() { return role; }

    public void listenLineup(String message) {
        System.out.println("[" + this + "] with Role [" + role.act() + "] recieved message from listenLineup(): " + message);
    }

    public void listenTeam(String message) {
    }

    public void listenBench(String message) {

    }
}
