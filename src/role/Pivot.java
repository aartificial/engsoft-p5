package role;

public class Pivot extends RoleDecorator {
    public Pivot(Role role) {
        super(role);
    }

    public String act() {
        return super.act() + addFunction();
    }

    @Override
    protected String addFunction() {
        return " and Pivot";
    }
}
