import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

class DatabazeVSouboru {
  
  public String nactiZaznam(final String klic) {
    
    // Kontrola opravneni Access controllerem
    AccessController.checkPermission(new ZaznamPermission(klic, "nacteni"));
    
    // Kontrolova opravneni Security managerem
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
        sm.checkPermission(new ZaznamPermission(klic, "nacteni"));
    }
    
    // protože oprávnění má, bez ohledu na to že volající kód nemá
    // oprávnění přistupovat k databázovému souboru záznam načteme
    return AccessController.doPrivileged(new PrivilegedAction<String>() {
      public String run() {
        // Zde bude práce s databázovým souborem
        try{
          BufferedReader br = new BufferedReader(new FileReader("knihovna"+File.separator+klic));
          String hodnota = br.readLine();
          br.close();
          return hodnota;
        }
        catch(IOException e){
          return "";
        }
      }
    });
  }
  
  public void ulozZaznam(final String klic, final String hodnota) throws Exception {
    
    // Kontrola opravneni Access controllerem
    AccessController.checkPermission(new ZaznamPermission(klic, "ulozeni"));
    
    // Kontrolova opravneni Security managerem
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
        sm.checkPermission(new ZaznamPermission(klic, "ulozeni"));
    }
    
    // protože oprávnění má, bez ohledu na to že volající kód nemá
    // oprávnění přistupovat k databázovému souboru záznam uložíme
    try {
      AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
        public Void run() throws IOException {
          // Zde bude práce s databázovým souborem
          
          FileWriter fw = new FileWriter("knihovna"+File.separator+klic);
          fw.write(hodnota);
          fw.close();
          
          return null;
        }
      });
    }
    catch(PrivilegedActionException e){
      throw e.getException();
    }
  }
  
}
