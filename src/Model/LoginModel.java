package Model;

import Helper.UserContainer;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.util.HashMap;


/**
 * A login muvelethez tartozo adatbazis funkciokat tartalmazza
 */
public class LoginModel {


   String DB_URL;


   String USER;
   String PASS;
    
   Connection conn = null;
   PreparedStatement prepstat = null;
   ResultSet rs;

    public LoginModel(HashMap<String,String> dbproperties) {
        
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
     * Username es password alapjan vissza ad egy usert
     * @param name
     * @param pass
     * @return
     * @throws SQLException
     */
    public UserContainer GetUser(String name,String pass) throws java.sql.SQLException{

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT `user`.`IdUser`,`user`.`UserName`,`user`.`Name`,`user`.`Password`,`user`.`Salt`,`user`.`Role`,`user`.`Email`,`user`.`Date-of-Birth` FROM `mydb`.`user` WHERE `UserName` = ?";
        

        
        prepstat = conn.prepareStatement(query);
        prepstat.setString(1, name);
        
        rs = prepstat.executeQuery();
        UserContainer tempuser = null;
        
        while(rs.next()){
        
            tempuser = new UserContainer(Integer.valueOf(rs.getString("IdUser")), rs.getString("UserName"), rs.getString("Name"), rs.getString("Date-of-Birth"), rs.getString("Salt"), rs.getString("Password"), rs.getString("Role"),rs.getString("Email"));

        }
        
        CloseConnection();
        
        if(tempuser == null)
            return null;
        else
            return tempuser;

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
    

