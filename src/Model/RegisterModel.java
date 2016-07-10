package Model;


import Helper.UserContainer;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


/**
 *A register muvelethez tartozo adatbazis funkciokat tartalmazza
 */
public class RegisterModel {

   
   String DB_URL;
  
   String USER;
   String PASS;
    
   Connection conn = null;
   PreparedStatement prepstat = null;
   ResultSet rs;

    /**
     *Csatlakozashoz szukseges valtozokat allitja be a megadott properties segitsegevel
     * @param dbproperties
     */
    public RegisterModel(HashMap<String,String> dbproperties) {

        
        
       try {
           
           InetAddress net;
           net = InetAddress.getLocalHost();

           if(net.getHostName().contains("Pifko"))
               DB_URL = "jdbc:mysql://" + dbproperties.get("databaselocaladdress")+":"+dbproperties.get("databaselocalport")+"/"+dbproperties.get("databaselocalname");
           else
               throw new UnknownHostException();
           
       } catch (UnknownHostException ex) {
           
           DB_URL = "jdbc:mysql://" + dbproperties.get("databaseaddress")+":"+dbproperties.get("databaseport")+"/"+dbproperties.get("databasename");
           
       } finally {
       
           PASS = dbproperties.get("dbpassword");
           USER = dbproperties.get("dbuser"); 
       
       }
        
        
    }

    /**
     * Megnezei hogy adott nevu felahsznalo letezik-e
     * @param name
     * @return
     * @throws SQLException
     */
    public boolean UserCheck(String name) throws java.sql.SQLException{
   
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT * FROM `mydb`.`user` WHERE `UserName` = ?";
        

        
        prepstat = conn.prepareStatement(query);
        prepstat.setString(1, name);
        rs = prepstat.executeQuery();
        
        while(rs.next()){
            CloseConnection();
            return true;
        }
        
        CloseConnection();
        return false;

   }

    /**
     * Megnezei hogy adott email-el rendelkezo felahsznalo letezik-e
     * @param email
     * @return
     * @throws SQLException
     */
    public boolean EmailCheck(String email) throws java.sql.SQLException{
   
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT * FROM `mydb`.`user` WHERE `Email` = ?";
        
        
        prepstat = conn.prepareStatement(query);
        prepstat.setString(1, email);
        rs = prepstat.executeQuery();
        
        while(rs.next()){
            CloseConnection();
            return true;
        }
        
        CloseConnection();
        return false;

   }

    /**
     * Az addot usert beregisztralja
     * @param user
     * @return
     * @throws SQLException
     */
    public boolean RegisterUser(UserContainer user) throws java.sql.SQLException{
          
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "INSERT INTO `mydb`.`user`(`UserName`,`Password`,`Email`,`Salt`,`Date-of-Birth`,`Name`,`Role`)VALUES(?,SHA1(CONCAT(?,?)),?,?,?,?,?);";
        
        prepstat = conn.prepareStatement(query);
        prepstat.setString(1, user.getUsername());
        prepstat.setString(2, user.getPassword());
        prepstat.setString(3, user.getUsername());
        prepstat.setString(4, user.getEmail());
        prepstat.setString(5, user.getUsername());
        prepstat.setString(6, user.getBday());
        prepstat.setString(7, user.getName());
        prepstat.setString(8, "user");
        
        int result = prepstat.executeUpdate();
        CloseConnection();
        
        if(result > 0)
            return true;
        else
            return false;
        
      
      }
    
     /**
     *Adatbazis mulvelet utan lezarja az egyes ehhez szukseges elemeket
     */    
     private void CloseConnection() throws java.sql.SQLException{
   
       if(conn != null)
           conn.close();
       if(prepstat != null)
           prepstat.close();
       if(rs != null)
           rs.close();
   
   }
    
}
