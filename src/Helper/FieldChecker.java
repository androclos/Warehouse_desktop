package Helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *Szoveges mezok ellneorzeset vegzo metodusokat tartalmaz
 */
public class FieldChecker {
    
    
    private static final String datepattern = "(0[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)";
    private static final String usernamepattern = "^[a-zA-Z0-9]{3,10}$";
    private static final String passwordpattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{3,10})";
    private static final String emailpattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final String amountpattern = "^[1-9]\\d*$";
    
    public static boolean DateCheck(String s){

    Pattern pattern = Pattern.compile(datepattern);
    Matcher matcher = pattern.matcher(s);
    
    if(!s.isEmpty() && matcher.matches())
        return true;
    else
        return false;

    }
    
    public static boolean UserNameCheck(String s){
    
    Pattern pattern = Pattern.compile(usernamepattern);
    Matcher matcher = pattern.matcher(s);
    
    if(!s.isEmpty() && matcher.matches())
        return true;
    else
        return false;
    
    }
    
    public static boolean PasswordCheck(String s){
    
    Pattern pattern = Pattern.compile(passwordpattern);
    Matcher matcher = pattern.matcher(s);
    
    if(!s.isEmpty() && matcher.matches())
        return true;
    else
        return false;
    
    }
    
   public static boolean EmailCheck(String s){
    
    Pattern pattern = Pattern.compile(emailpattern, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(s);
    
    if(!s.isEmpty() && matcher.matches() && !(s.length()>40))
        return true;
    else
        return false;
    
    }
    
    public static boolean AmountCheck(String s){
    
    Pattern pattern = Pattern.compile(amountpattern);
    Matcher matcher = pattern.matcher(s);
    
    if(!s.isEmpty() && matcher.matches())
        return true;
    else
        return false;

    
    }
    
    
}
