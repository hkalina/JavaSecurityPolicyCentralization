import java.security.AccessController;
import java.security.PrivilegedAction;

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
        
        System.out.println(klic);
        
        return klic;
      }
    });
  }
}
