import java.io.IOException;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;

// http://www.javablogging.com/java-classloader-2-write-your-own-classloader/

public class TestovaciClassLoader extends ClassLoader {
    
    public TestovaciClassLoader(ClassLoader parent) {
        super(parent);
    }
    
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("loadClass('" + name + "')");
        if (name.startsWith("TestovaciTrida")) {
            return getClass(name);
        }
        return super.loadClass(name);
    }
    
    private Class<?> getClass(String name) throws ClassNotFoundException {
        String file = name.replace('.', File.separatorChar) + ".class";
        byte[] b = null;
        try {
            b = loadClassData(file);
            Class<?> c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private byte[] loadClassData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }
    
}
