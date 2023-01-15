public class Left extends RoleDecorator {
    public Left(Role role) {
        super(role);
    }

    public String act() {
        return super.act() + addFunction();
    }

    @Override
    protected String addFunction() {
        return " and Left";
    }
}
