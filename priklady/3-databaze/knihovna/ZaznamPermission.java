import java.security.BasicPermission;

// třída oprávnění musí být public
public class ZaznamPermission extends BasicPermission {
    public ZaznamPermission(String name, String actions) {
        super(name, actions);
    }
}
