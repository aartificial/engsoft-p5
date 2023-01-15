public class Right extends RoleDecorator {
    public Right(Role role) {
        super(role);
    }

    public String act() {
        return super.act() + addFunction();
    }

    @Override
    protected String addFunction() {
        return " and Right";
    }
}
