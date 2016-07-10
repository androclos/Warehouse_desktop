package Helper;

import java.security.MessageDigest;
import java.security.*;

/**
 *Jelszo hashlesehez tartalamz metodust
 */
public class PasswordHash {
    
    private static MessageDigest md;
    
    /**
     * SH1 hash elkeszitese jelszo es salt alapjan
     * @param password
     * @param salt
     * @return
     */
    public static String GetHashedPassword(String password, String salt){
    
         String passwordhash = null;
         try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passwordhash = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
   
        }
    
        return passwordhash; 
    
    } 
    
}
