import java.lang.SecurityManager;
import java.security.Permission;
import java.util.PropertyPermission;
import java.io.FilePermission;

public class TestovaciSM extends SecurityManager {
	
	public static int countOfCalls = 0;
	public static int countOfPropertyCalls = 0;
	public static int countOfRuntimeCalls = 0;
	public static int countOfFileCalls = 0;
	
	@Override
	public void checkPermission(Permission perm) {
		System.out.println(perm.toString());
		if(perm instanceof PropertyPermission) countOfPropertyCalls++;
		if(perm instanceof RuntimePermission) countOfRuntimeCalls++;
		if(perm instanceof FilePermission) countOfFileCalls++;
		countOfCalls++;
	}
	
}
