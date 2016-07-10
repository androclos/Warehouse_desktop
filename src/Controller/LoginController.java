package Controller;

import Helper.PasswordHash;
import Helper.UserContainer;
import Model.LoginModel;
import View.LoginView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *Login-hez szukseges view-t, modelt-es egyeb metodusokat tartalamaz
 */
public class LoginController {

   public LoginView loginview;
   public LoginModel loginmodel;
   public Controller maincontrollref;
   private HashMap<String,String>dbconnectionproperties = null;
   private Logger logger;

    /**
     *
     * @param c
     * @param conf
     * @param logger
     */
    public LoginController(Controller c,HashMap<String,String> conf,Logger logger) {

           this.logger = logger;
           this.dbconnectionproperties = conf;
           loginview = new LoginView();
           loginview.setTitle("LogIn");
           loginmodel = new LoginModel(dbconnectionproperties);
           maincontrollref = c;
           
           this.loginview.setTitle("WHO");
           SetListeners();

       
    }
    
   /**
   * Feluleti elemekhez beallitja az esemeny figyeloket
   */
   private void SetListeners(){
   
        loginview.getLoginButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                LoginAttempt();
                
            }
        });
        
        loginview.getRegisterButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
     
                maincontrollref.SetRegisterView();
                
            }
        }); 
   }

    /**
     * Beallitj a login keperenyo lathatosagat
     * @param value
     */
    public void SetVisibility(boolean value){
    
        loginview.setVisible(value);

   }

    /**
     *Lekezel egy bejelenetkezesi kiserletet
     */
    public void LoginAttempt(){
        logger.log(Level.FINEST, "Login attempt.");
    
        String name = loginview.getTextField().getText();
        String pass = new String (loginview.getPasswordField().getPassword());
        
       try {
          
           if(name.isEmpty() || pass.isEmpty()){
               
               loginview.ScreenMessage("None of the fields can be empty.");
               logger.log(Level.FINEST, "Login failed : Empty field(s).");
               return;
               
           }
           
           UserContainer userresult = loginmodel.GetUser(name, pass);
           if(userresult == null){
               
               loginview.ScreenMessage("There is no user with the given username.");
               logger.log(Level.FINEST, "Login failed.");
               
           }
           else if((PasswordHash.GetHashedPassword(pass,userresult.getSlat()).equals(userresult.getPassword()))){
               
               userresult.setPassword(null);
               userresult.setSlat(null);
               maincontrollref.setLoggedinuser(userresult);
               loginview.EmptyTextFields();
               maincontrollref.SetOperationView();
               
               logger.log(Level.FINEST, "Login successful - "+"ID: {0}", maincontrollref.getLoggedinuser().getId());
               
           }
                      
           else{
               logger.log(Level.FINEST, "Login failed : Wrong password");
               loginview.ScreenMessage("Wrong password.");
           }

           
       } catch (SQLException ex) {
           logger.log(Level.SEVERE, ex.getMessage(), ex);
           loginview.ScreenMessageError(ex.getMessage());
       }
    
        
    
    }

    
   
   
    
}
