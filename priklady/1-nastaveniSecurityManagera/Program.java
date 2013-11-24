import java.lang.SecurityManager;
import java.security.Permission;
import java.io.IOException;
import java.util.Scanner;

class Program {
	public static void main(String[] args){
		
		System.out.println("System.getSecurityManager()="+System.getSecurityManager());
		System.out.println("System.getProperty(java.security.manager)="+System.getProperty("java.security.manager"));
		
	}
}
