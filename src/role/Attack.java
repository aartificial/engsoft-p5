package role;

public class Attack extends RoleDecorator {
    public Attack(Role role) {
        super(role);
    }

    public String act() {
        return super.act() + addFunction();
    }

    @Override
    protected String addFunction() {
        return " and Attack";
    }
}
