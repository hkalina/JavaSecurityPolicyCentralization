import java.lang.SecurityManager;
import java.security.Permission;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class TestovaciSM extends SecurityManager {
	
	@Override
	public void checkPermission(Permission perm) {
		
		System.out.println("Povolit "+perm.toString()+" ?");
		
		if(!askUser()){ // jestliže uživatel nezvolí "a"
			throw new SecurityException("Operace byla zakázána uživatelem");
		}
		
	}
	
	public boolean askUser(){
		try{
			byte key[] = new byte[2];
			System.in.read(key);
			if(key[0] == (byte)'a') return true;
		}
		catch(IOException e){}
		return false;
	}
	
}
