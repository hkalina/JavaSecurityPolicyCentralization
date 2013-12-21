import java.lang.SecurityManager;
import java.security.Policy;
import java.io.FileReader;

class Program {
  public static void main(String[] args){
    
    System.setProperty("java.security.policy","povolitVse.policy");
    refreshReplacement();
    System.setSecurityManager(new SecurityManager());
    test();
    
    System.setProperty("java.security.policy","nepovolitNic.policy");
    refreshReplacement();
    test();
    
  }
  
  // nahrade deprecated metody Policy.refresh()
  private static void refreshReplacement(){
    try{
      Class classOfPolicy = Policy.getPolicy().getClass();
      Policy newPolicy = (Policy)classOfPolicy.newInstance();
      Policy.setPolicy(newPolicy);
    }
    catch(Exception e){
      System.err.println(e.getMessage());
    }
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
