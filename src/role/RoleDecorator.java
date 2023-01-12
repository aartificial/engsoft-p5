package role;

public abstract class RoleDecorator implements Role {
    private Role role;

    public RoleDecorator(Role role) {
        this.role = role;
    }

    @Override
    public String act() {
        return role.act();
    }

    protected abstract String addFunction();
}
