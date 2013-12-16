import java.security.BasicPermission;

// musí být public!
public class ZaznamPermission extends BasicPermission {
    
    public ZaznamPermission(String name) {
        super(name);
    }
    
    public ZaznamPermission(String name, String actions) {
        super(name, actions);
    }
    
}
