
package gui;

import java.io.InputStream;

public class Resources {
	
    public static InputStream getResourceFile(String name){
        // Getting named resource from Resources.class location...
        return Resources.class.getResourceAsStream(name);
    }

}
