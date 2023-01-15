public class Defense extends RoleDecorator {
    public Defense(Role role) {
        super(role);
    }

    public String act() {
        return super.act() + addFunction();
    }

    @Override
    protected String addFunction() {
        return " and Defense";
    }
}
