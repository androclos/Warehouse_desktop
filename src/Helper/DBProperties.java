package Helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



/**
 *Adatbazis kapcsolatokkoz szukseges adatok beolvasast vegzi
 */
public class DBProperties {
    
    
    public static HashMap<String,String> GetconenctionsInfo() throws IOException{
        
        Properties prop = new Properties();
	InputStream input = null;

        input = new FileInputStream("DBconfig.properties");
        prop.load(input);


        HashMap<String,String> properties = new HashMap<String, String>();
        
        for(Map.Entry<Object,Object> entry : prop.entrySet()){          
           properties.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));      
        }
    
        return properties;
    
    }
	
    
}
