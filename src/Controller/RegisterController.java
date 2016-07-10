package Controller;

import View.RegisterView;
import Model.RegisterModel;
import java.awt.Color;
import static Helper.FieldChecker.*;
import Helper.UserContainer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *Regisztralashoz szukseges view-t, modelt-es egyeb metodusokat tartalamaz
 */
public class RegisterController {

    private RegisterModel registermodel;
    private RegisterView registerview;
    private Controller maincontrollref;
    private HashMap<String,String>dbconnectionproperties = null;
    private Logger logger;
    
    /**
     *
     * @param c
     * @param conf
     * @param logger
     */
    public RegisterController(Controller c,HashMap<String,String> conf,Logger logger) {
        
        this.logger = logger;
        this.dbconnectionproperties = conf;
        registermodel = new RegisterModel(dbconnectionproperties);
        registerview = new RegisterView();
        registerview.setTitle("Register");
        this.maincontrollref = c;
        
        this.registerview.setTitle("WHO");
        SetListeners();
        
    }
    
    /**
     * Beallitj a register keperenyo lathatosagat
     * @param value
     */
    public void SetVisibility(boolean value){
    
        registerview.setVisible(value);
      
    } 
    /**
    * Feluleti elemekhez beallitja az esemeny figyeloket
    */  
    private void SetListeners(){
   
        //////////Gomb figyeles/////////
        registerview.getSubmitRegisterButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                RegisterAttempt();
                
            }
        });
        
        registerview.getCancelRegisterButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
     
                maincontrollref.SetLogInView();
                
            }
        }); 
        
        //////////Mezo ellenorzes/////////
        registerview.getUserNameTextField().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
          
                if(UserNameCheck(registerview.getUserNameTextField().getText()))
                    registerview.getUserNameTextField().setBackground(new Color(204,255,204));
                else
                    registerview.getUserNameTextField().setBackground(new Color(255,204,204));
  
            
        }
    });
       
/*------------------------------------------------------------------------------------------------*/       
        registerview.getUserNameTextField().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
         
                
                registerview.getUserNameTextField().setBackground(new Color(255,255,255));
            
        }
    });
   
        registerview.getBirthDayTextField().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
          
                if(DateCheck(registerview.getBirthDayTextField().getText()))
                    registerview.getBirthDayTextField().setBackground(new Color(204,255,204));
                else
                    registerview.getBirthDayTextField().setBackground(new Color(255,204,204));
            
        }
    });
       
        
        registerview.getBirthDayTextField().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
          
                
                registerview.getBirthDayTextField().setBackground(new Color(255,255,255));
            
        }
    });
        
        registerview.getEmailTextField().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
          
                if(EmailCheck(registerview.getEmailTextField().getText()))
                    registerview.getEmailTextField().setBackground(new Color(204,255,204));
                else
                    registerview.getEmailTextField().setBackground(new Color(255,204,204));
            
            
        }
    });
       
        
        registerview.getEmailTextField().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
          
                
                registerview.getEmailTextField().setBackground(new Color(255,255,255));
            
        }
    });
        
        registerview.getFullNameTexField().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
          
                if(!registerview.getFullNameTexField().getText().isEmpty())
                    registerview.getFullNameTexField().setBackground(new Color(204,255,204));
                else
                    registerview.getFullNameTexField().setBackground(new Color(255,204,204));

            
        }
    });
       
        
        registerview.getFullNameTexField().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
          
                
                registerview.getFullNameTexField().setBackground(new Color(255,255,255));
            
        }
    });
        
        registerview.getPasswordField().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
          
                if(PasswordCheck(new String(registerview.getPasswordField().getPassword())))
                    registerview.getPasswordField().setBackground(new Color(204,255,204));
                else
                    registerview.getPasswordField().setBackground(new Color(255,204,204));
        }
    });
       
        
        registerview.getPasswordField().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
          
                
                registerview.getPasswordField().setBackground(new Color(255,255,255));
            
        }
    });
        

         
   }
    
    /**
     * Lekezel egy regisztralasi kiserletet
     */
    public void RegisterAttempt(){
        try {
            
            logger.log(Level.FINEST, "Register attempt.");
 
            if(!DateCheck(registerview.getBirthDayTextField().getText())){
                
                logger.log(Level.FINEST, "Registration failed : Date of Birth: Field is empty or the format is invalid.");
                registerview.ScreenMessage("Date of Birth: Field is empty or the format is invalid.");
                return;
                
            }
            
            if(registerview.getFullNameTexField().getText().isEmpty()){
                
                logger.log(Level.FINEST, "Registration failed : Full Name: The field can not be empty.");
                registerview.ScreenMessage("Full Name: The field can not be empty.");
                return;
                
            }
            
            if(!EmailCheck(registerview.getEmailTextField().getText())){

                logger.log(Level.FINEST, "Registration failed : Email Address: Field is empty or the format is invalid.");
                registerview.ScreenMessage("Email Address: Field is empty or the format is invalid.");
                return;
                
            }
            
            if(!PasswordCheck(new String(registerview.getPasswordField().getPassword()))){
                
                logger.log(Level.FINEST, "Registration failed : Password: Field is empty or the format is invalid.");
                registerview.ScreenMessage("Password: Field is empty or the format is invalid.");
                return;
                
            }
            
            if(!UserNameCheck(registerview.getUserNameTextField().getText())){
                
                logger.log(Level.FINEST, "Registration failed : UserName: Field is empty or the format is invalid.");
                registerview.ScreenMessage("UserName: Field is empty or the format is invalid.");
                return;
                
            }
            
 /*------------------------------------------------------------------------------------------------*/               
            if(registermodel.UserCheck(registerview.getUserNameTextField().getText())){
                
                logger.log(Level.FINEST, "Registration failed : The username is already in use.");
                registerview.ScreenMessage("The username is already in use.");
                return; 
            }
            
            if(registermodel.EmailCheck(registerview.getEmailTextField().getText())){
                
                logger.log(Level.FINEST, "Registration failed : The email address is already in use.");
                registerview.ScreenMessage("The email address is already in use.");
                return; 
            }
            
            
            String newuseruname = registerview.getUserNameTextField().getText();
            String newusername = registerview.getFullNameTexField().getText();
            String newuserbday = registerview.getBirthDayTextField().getText();
            String newusersalt = registerview.getUserNameTextField().getText();
            String newuserpassword = new String(registerview.getPasswordField().getPassword()); 
            String newuseremail = registerview.getEmailTextField().getText();
            
            UserContainer newuser = new UserContainer(0, newuseruname, newusername, newuserbday, newusername, newuserpassword, null, newuseremail);
            
            boolean result = registermodel.RegisterUser(newuser);
            
            if(result){

                maincontrollref.SetLogInView();
                registerview.ScreenMessage("Successful registration, please log in.");
                logger.log(Level.FINEST, "Registration successful.");

            }
            else{
            
                maincontrollref.SetLogInView();
                registerview.ScreenMessage("Registration failed!");
                logger.log(Level.FINEST, "Registration failed : Reason unknown.");
            
            }
            
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            registerview.ScreenMessageError(ex.getMessage());
        }
        
        
        
    }
    
}
