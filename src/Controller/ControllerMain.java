package Controller;


import javax.swing.SwingUtilities;


public class ControllerMain {
   
   public static void main(String[] args) {           
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                                           
               
                Controller controller = new Controller();
                
            }
        });  
    }
    
    
}
