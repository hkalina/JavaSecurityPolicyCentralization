import java.lang.SecurityManager;
import java.security.Policy;
import java.io.FileReader;

class Program {
  public static void main(String[] args){
    
    test();
    
    System.setProperty("java.security.policy","povolitVse.policy");
    Policy.getPolicy().refresh();
    System.setSecurityManager(new SecurityManager());
    test();
    
    System.setProperty("java.security.policy","nepovolitNic.policy");
    Policy.getPolicy().refresh();
    test();
    
    System.setProperty("java.security.policy","povolitVse.policy");
    Policy.getPolicy().refresh();
    test();
    
    System.setProperty("java.security.policy","nepovolitNic.policy");
    Policy.getPolicy().refresh();
    test();
    
    System.setSecurityManager(null);
    test();
    
  }
  
  private static void test(){
    System.out.print(System.getProperty("java.security.policy") + "...");
    try{
      FileReader fr = new FileReader("/etc/passwd");
      fr.close();
      System.out.println("lze cist");
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
  }
  
}
