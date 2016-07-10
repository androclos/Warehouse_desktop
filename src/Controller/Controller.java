package Controller;


import static Helper.DBProperties.GetconenctionsInfo;
import Helper.UserContainer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *Az osszes controller elemet tartalmazza
 */
public class Controller {
    
    private LoginController logincontroller;
    private RegisterController registercontroller;
    private WHOperationController whoperationcontroller;
    private UserContainer loggedinuser = null;
    private HashMap<String,String>dbconnectionproperties = null;

    public Logger logger;

    Controller() throws SecurityException{
        try {
            
            File crt = new File("log");
            
            if(!crt.exists()){

                crt.mkdir();
            }
            
            logger = Logger.getLogger( Controller.class.getName() );
            FileHandler hand = new FileHandler("log/log.txt", true);
            hand.setFormatter(new Helper.LogFormatter());
            logger.addHandler(hand);
            logger.setLevel(Level.ALL);
            hand.setLevel(Level.ALL);
            
            dbconnectionproperties = GetconenctionsInfo();
            logincontroller = new LoginController(this,dbconnectionproperties,logger);
            registercontroller = new RegisterController(this,dbconnectionproperties,logger);
            logincontroller.SetVisibility(true);
 
            
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

       
       
    }

    /**
     * Vissza adja az eppen bejelentkezett felhasznalot
     * @return
     */
    public UserContainer getLoggedinuser() {
        return loggedinuser;
    }

    /**
     * Beallitja az eppen bejelentkezett felhasznalot
     * @param loggedinuser
     */
    public void setLoggedinuser(UserContainer loggedinuser) {
        this.loggedinuser = loggedinuser;  
    }
    
    /**
     * Atvalt a regisztracios nezetre
     */
    public void SetRegisterView(){
    
        if(whoperationcontroller != null){
            whoperationcontroller.SetVisibility(false);
            whoperationcontroller.DisposeWindow();
            whoperationcontroller = null;
        }
          
        loggedinuser = null;
        logincontroller.SetVisibility(false);
        registercontroller.SetVisibility(true);
    
    
    }
    
    /**
     * Atvalt a login nezetre
     */
    public void SetLogInView(){
    
        if(whoperationcontroller != null){
            whoperationcontroller.SetVisibility(false);
            whoperationcontroller.DisposeWindow();
            whoperationcontroller = null;
        }
        
        loggedinuser = null;
        registercontroller.SetVisibility(false);
        logincontroller.SetVisibility(true);
        
    
    }

    /**
     * Atvalt raktar es admin funkciok nezetre
     */
    public void SetOperationView(){
    
        registercontroller.SetVisibility(false);
        logincontroller.SetVisibility(false);
        whoperationcontroller = new WHOperationController(this,dbconnectionproperties,logger);
        whoperationcontroller.SetVisibility(true);
        whoperationcontroller.SetProductListTableContent(loggedinuser.getId());
        

    
    }
      
}
