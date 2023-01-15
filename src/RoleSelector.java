import role.*;

public class RoleSelector {
    public static Role createRole(String roleDescription) {
        Role role;
        switch(roleDescription) {
            case "defensa extrem esquerra": role = new Defense(new Extreme(new Left(new BaseRole()))); break;
            case "defensa extrem dret": role = new Defense(new Extreme(new Right(new BaseRole()))); break;
            case "defensa lateral esquerra": role = new Defense(new Lateral(new Left(new BaseRole()))); break;
            case "defensa lateral dret": role = new Defense(new Lateral(new Right(new BaseRole()))); break;
            case "defensa central esquerra": role = new Defense(new Central(new Left(new BaseRole()))); break;
            case "defensa central dret": role = new Defense(new Central(new Right(new BaseRole()))); break;
            case "atac extrem esquerra": role = new Attack(new Extreme(new Left(new BaseRole()))); break;
            case "atac extrem dret": role = new Attack(new Extreme(new Right(new BaseRole()))); break;
            case "atac lateral esquerra": role = new Attack(new Lateral(new Left(new BaseRole()))); break;
            case "atac lateral dret": role = new Attack(new Lateral(new Right(new BaseRole()))); break;
            case "atac central": role = new Attack(new Central(new BaseRole())); break;
            case "atac pivot": role = new Attack(new Pivot(new BaseRole())); break;
            case "porter": role = new Goalkeeper(new BaseRole()); break;
            default: System.out.println("Role [" + roleDescription + "] does not exist, assigning base role."); return new BaseRole();
        }
        return role;
    }
}
