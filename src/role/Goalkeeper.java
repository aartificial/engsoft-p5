package role;

public class Goalkeeper extends RoleDecorator {
    public Goalkeeper(Role role) {
        super(role);
    }

    public String act() {
        return super.act() + addFunction();
    }

    @Override
    protected String addFunction() {
        return " and Goalkeeper";
    }
}
