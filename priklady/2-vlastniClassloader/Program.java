import java.net.URLClassLoader;
import java.net.URL;

public class Program {
    /**
     * If you run this main method supplying the
     * -Djava.system.class.loader=javablogging.CustomClassLoader
     * parameter, class SimpleMain will be loaded with
     * our CustomClassLoader. Every other
     * class referenced from here will be also loaded with it.
     */
    public static void main(String... strings) {
        
        System.out.print("This is my ClassLoader: " + Program.class.getClassLoader());
        /*
        URL[] urls = {
        new URL("http://server/adresar/"),
        new URL("file:/srv/classes/")
        };
        */
        try{
        URLClassLoader urlClassLoader = new URLClassLoader( new URL[] {
        new URL("http://server/adresar/"),
        new URL("file:/srv/classes/")
        }, null);
        }
        catch(Exception e){}
        
        boolean x = TestovaciTridaA.test();
        
    }
}
