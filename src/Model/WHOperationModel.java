package Model;

import Helper.ProductContainer;
import Helper.UserContainer;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *A raktarmuveletekhez tartozo adatbazis funkciokat tartalmazza
 */
public class WHOperationModel {
    

   String DB_URL;

   String USER;
   String PASS;
    
   Connection conn = null;
   PreparedStatement prepstat = null;
   ResultSet rs;

    /**
     * Csatlakozashoz szukseges valtozokat allitja be a megadott properties segitsegevel
     * @param dbproperties
     */
    public WHOperationModel(HashMap<String,String> dbproperties) {
        
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
     * Adott Id-hez tartozo termekekt adja vissza
     * @param id
     * @return
     * @throws SQLException
     */
    public HashMap<Integer,ProductContainer> GetProducts(int id) throws SQLException{
   
       conn = DriverManager.getConnection(DB_URL, USER, PASS);
       String query = "SELECT * FROM `mydb`.`product` WHERE `product`.`IdOwner` = ?";
       
       prepstat = conn.prepareStatement(query);
       prepstat.setString(1,Integer.toString(id));
       rs = prepstat.executeQuery();
        
       HashMap<Integer,ProductContainer> userproducts = new HashMap<Integer, ProductContainer>();
       
       while(rs.next()){

            ProductContainer newprodcut = new ProductContainer(Integer.valueOf(rs.getString("IdProduct")),rs.getString("ProductName"),rs.getString("ProductType"),rs.getString("Comment"),Integer.valueOf(rs.getString("Amount")));
            userproducts.put(Integer.valueOf(rs.getString("IdProduct")),newprodcut);
        
        }
       
       CloseConnection();
       return userproducts;

   }

    /**
     * Megnezi hogy adott nevu termeket letezik-e
     * @param name
     * @return
     * @throws SQLException
     */
    public boolean GetProductsbyName(String name) throws SQLException{
   
       conn = DriverManager.getConnection(DB_URL, USER, PASS);
       String query = "SELECT * FROM `mydb`.`product` WHERE `product`.`ProductName` = ?";
       
       prepstat = conn.prepareStatement(query);
       prepstat.setString(1,name);
       rs = prepstat.executeQuery();

       while(rs.next()){
           CloseConnection();
           return true;
        }
       
       CloseConnection();
       return false;
       
   }

    /**
     * Egy uj termeket ad hozza az adtabazishoz a megfelelo user-nek
     * @param product
     * @param idowner
     * @throws SQLException
     */
    public void AddNewProduct(ProductContainer product, int idowner) throws SQLException{
   
       conn = DriverManager.getConnection(DB_URL, USER, PASS);
       String query = "INSERT INTO `mydb`.`product` (`ProductName`, `ProductType`, `Amount`, `IdOwner`, `Comment`) VALUES (?,?,?,?,?)";
       
       prepstat = conn.prepareStatement(query);
       prepstat.setString(1,product.getName());
       prepstat.setString(2,product.getType());
       prepstat.setInt(3,product.getAmount());
       prepstat.setInt(4,idowner);
       prepstat.setString(5,product.getComment());
       prepstat.executeUpdate();
        
       CloseConnection();

   }

    /**
     * Mar bentlevo termekethez rak be ujabb mennyiseget
     * @param id
     * @param amount
     * @return
     * @throws SQLException
     */
    public boolean AddExistingProduct(int id, int amount) throws SQLException{
   
       conn = DriverManager.getConnection(DB_URL, USER, PASS);
       String query = "SELECT * FROM `mydb`.`product` WHERE `product`.`IdProduct` = ?";
       
       prepstat = conn.prepareStatement(query);
       prepstat.setInt(1,id);
       rs = prepstat.executeQuery();
       
       int newamount = 0;
       int productamount = 0;
       while(rs.next()){
            productamount = rs.getInt("Amount");
       }
       
       if(Long.valueOf(productamount+amount) > Integer.MAX_VALUE){
           CloseConnection();
           return false;
       }
       
       newamount = productamount+amount;
       
       query = "UPDATE `mydb`.`product` SET `Amount` = ? WHERE `IdProduct` = ?";
       prepstat = conn.prepareStatement(query);
       prepstat.setInt(1,newamount);
       prepstat.setInt(2,id);
       prepstat.executeUpdate();
 

       CloseConnection();
       return true;
   
   }

    /**
     * Adott termek mennyiseget adja vissza
     * @param id
     * @return
     * @throws SQLException
     */
    public int GetProductAmount(int id) throws SQLException{
   
       conn = DriverManager.getConnection(DB_URL, USER, PASS);
       String query = "SELECT * FROM `mydb`.`product` WHERE `product`.`IdProduct` = ?";
       
       prepstat = conn.prepareStatement(query);
       prepstat.setInt(1,id);
       rs = prepstat.executeQuery();
       
       int amount = 0;
       while(rs.next()){
            amount = Integer.valueOf(rs.getInt("Amount"));
       }

       return amount;

   }

    /**
     * Mar bentlevo termekbol vesz ki adott mennyiseget
     * @param amount
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean RemoveProduct(int amount,int id) throws SQLException{
       
       conn = DriverManager.getConnection(DB_URL, USER, PASS);
       String query = "SELECT * FROM `mydb`.`product` WHERE `product`.`IdProduct` = ?";
       
       prepstat = conn.prepareStatement(query);
       prepstat.setInt(1,id);
       rs = prepstat.executeQuery();
       
       int newamount = 0;
       int productamount = 0;
       while(rs.next()){
            productamount = Integer.valueOf(rs.getInt("Amount"));
       }
       
       newamount = productamount - amount;
       
       if(newamount < 0){
           CloseConnection();
           return false;
       }

       
       query = "UPDATE `mydb`.`product` SET `Amount` = ? WHERE `IdProduct` = ?";
       prepstat = conn.prepareStatement(query);
       prepstat.setInt(1,newamount);
       prepstat.setInt(2,id);
       prepstat.executeUpdate();
 

       CloseConnection();
       return true;
   
   }

    /**
     * Az osszes felhasznalot adatait adja vissza
     * @return
     * @throws SQLException
     */
    public HashMap<Integer,UserContainer> GetUsersList() throws SQLException{
   
       conn = DriverManager.getConnection(DB_URL, USER, PASS);
       String query = "SELECT `user`.`IdUser`,`user`.`UserName`,`user`.`Email`,`user`.`Date-of-Birth`,`user`.`Name`,`user`.`Role` FROM `mydb`.`user`";
       
       prepstat = conn.prepareStatement(query);
       rs = prepstat.executeQuery();

       HashMap<Integer,UserContainer> userlist = new HashMap<Integer,UserContainer>();
       
       while(rs.next()){
            UserContainer temp = new UserContainer(rs.getInt("IdUser"), rs.getString("UserName"), rs.getString("Name"), rs.getString("Date-of-Birth"), null, null, rs.getString("Role"), rs.getString("Email"));
            userlist.put(rs.getInt("IdUser"), temp);
      
       }

       CloseConnection();
       return userlist;
       
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
