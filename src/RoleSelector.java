public class RoleSelector {
    public static Role createRole(String mode, String function, String position) {
        Role role = new BaseRole();
        if (mode.equals("defensa")) {
            role = new Defense(role);
            switch (function) {
                case "extrem" -> role = new Extreme(role);
                case "lateral" -> role = new Lateral(role);
                case "central" -> role = new Central(role);
                case "porter" -> {
                    return new Goalkeeper(role);
                }
            }
            role = switch (position) {
                case "esquerra" -> new Left(role);
                case "dret" -> new Right(role);
                default -> role;
            };
            return role;
        } else if (mode.equals("atac")) {
            role = new Attack(role);
            switch (function) {
                case "extrem" -> role = new Extreme(role);
                case "lateral" -> role = new Lateral(role);
                case "central" -> role = new Central(role);
                case "pivot" -> {
                    return new Pivot(role);
                }
                case "porter" -> {
                    return new Goalkeeper(role);
                }
            }
            role = switch (position) {
                case "esquerra" -> new Left(role);
                case "dret" -> new Right(role);
                default -> role;
            };
        }
        return role;
    }
}
