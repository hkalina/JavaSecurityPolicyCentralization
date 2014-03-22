import java.lang.SecurityManager;
import java.security.Permission;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

class Program {
	public static void main(String[] args){
		
		int beforeHelloWorld = TestovaciSM.countOfCalls;
		
		System.out.print("Hello world!");
		
		int afterHelloWorld = TestovaciSM.countOfCalls;
		
		System.out.println();
		System.out.println("Calls before Hello world: "+beforeHelloWorld);
		System.out.println("Calls after Hello world: "+afterHelloWorld);
		
		System.out.println("PropertyPermission: "+TestovaciSM.countOfPropertyCalls);
		System.out.println("RuntimePermission: "+TestovaciSM.countOfRuntimeCalls);
		System.out.println("FilePermission: "+TestovaciSM.countOfFileCalls);
	}
}
