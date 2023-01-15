public class Lateral extends RoleDecorator {
    public Lateral(Role role) {
        super(role);
    }

    public String act() {
        return super.act() + addFunction();
    }

    @Override
    protected String addFunction() {
        return " and Lateral";
    }
}
