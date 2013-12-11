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
        
        boolean x = TestovaciTridaA.test();
        
    }
}
