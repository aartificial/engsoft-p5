package role;

public class Central extends RoleDecorator {
    public Central(Role role) {
        super(role);
    }

    public String act() {
        return super.act() + addFunction();
    }

    @Override
    protected String addFunction() {
        return " and Central";
    }
}

