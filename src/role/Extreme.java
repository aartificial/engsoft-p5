package role;

public class Extreme extends RoleDecorator {
    public Extreme(Role role) {
        super(role);
    }

    public String act() {
        return super.act() + addFunction();
    }

    @Override
    protected String addFunction() {
        return " and Extreme";
    }
}
