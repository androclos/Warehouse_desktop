package Controller;

import Model.WHOperationModel;
import View.WHOperationView;
import Helper.ProductContainer;
import Helper.UserContainer;
import Helper.FieldChecker;
import Helper.XmlExporter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class WHOperationController {
    
    private WHOperationModel whoperationmodel;
    private WHOperationView  whoperationview;
    private Controller maincontrollref;
    public Map<Integer,ProductContainer> userproducts;
    private HashMap<String,String>dbconnectionproperties = null;
    private Map<Integer,UserContainer> userslist;
    
    private Logger logger;

    /**
     *
     * @param c
     * @param conf
     * @param logger
     */
    public WHOperationController(Controller c,HashMap<String,String> conf,Logger logger) {
   
            this.logger = logger;
            this.dbconnectionproperties = conf;
            whoperationmodel = new WHOperationModel(dbconnectionproperties);
            whoperationview = new WHOperationView();
            maincontrollref = c;
            
            if(maincontrollref.getLoggedinuser().getRole().toLowerCase().equals("admin"))
                SetUsersListTabelContent();
            else
              whoperationview.getMainTabbedPane().remove(whoperationview.getAdminFunctionsPanel());  

            this.whoperationview.setTitle("WHO");
            SetListeners();

        
    }

    /**
     * Beallitj a operation keperenyo lathatosagat
     * @param value
     */
    public void SetVisibility(boolean value){
    
        whoperationview.setVisible(value);  
        
   }
    
    /**
    * Feluleti elemekhez beallitja az esemeny figyeloket
    */
    private void SetListeners(){

        ///texfieldbe iras utan kereses////
        whoperationview.getNameFilterTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {

                whoperationview.getProductListTable().clearSelection();
                whoperationview.getProductCommentTextArea().setText("");
                whoperationview.getProductListSorter().setRowFilter(CreateFilterProductList());

                
            }
        });
        
        ///tpye szures///
        whoperationview.getTypeFilterTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {

                whoperationview.getProductListTable().clearSelection();
                whoperationview.getProductCommentTextArea().setText("");
                whoperationview.getProductListSorter().setRowFilter(CreateFilterProductList());

                
                
            }
        });
        
        //muvelet ablak szures
        whoperationview.getAddProductNameFilterTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {

                
                whoperationview.getAddProductListTable().clearSelection();
                whoperationview.getProductCommentTextArea().setText("");
                whoperationview.getProductListOperationsSorter().setRowFilter(CreateFilterOperation());

                
                
            }
        });
        
        whoperationview.getAddProductTypeFilterTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {

                whoperationview.getAddProductListTable().clearSelection();
                whoperationview.getProductCommentTextArea().setText("");
                whoperationview.getProductListOperationsSorter().setRowFilter(CreateFilterOperation());

                
                
            }
        });
        
        
       ///tábla refresh///
       whoperationview.getProductListRefreshButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                RefreshTable();
                
            }
        });
       
       whoperationview.getProductListTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {

            if((whoperationview.getProductListTable().getSelectedRow() != -1))
                whoperationview.getProductCommentTextArea().setText(whoperationview.getProductListTable().getValueAt(whoperationview.getProductListTable().getSelectedRow(), 4).toString());
 
        }
    });
       
        whoperationview.getAddProductButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
     
                if(whoperationview.getExistingProductCheckBox().isSelected())
                    AddExsitingProduct();
                else
                    AddProduct();
                
            }
        }); 

        whoperationview.getAddProductRemoveButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                RemoveProduct();
                
            }
        }); 
        
        whoperationview.getExportProductlistButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

              XmlExporter.ImportProducts(userproducts,maincontrollref.getLoggedinuser().getName());
                
            }
        }); 
        
        whoperationview.getLogOutMenuItem().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

              logger.log(Level.FINEST, "Logging out.");
              maincontrollref.SetLogInView();

                
            }
        });
        
        whoperationview.getExitMenuItem().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                logger.log(Level.FINEST, "Exitting application.");
                System.exit(42);

                
            }
        });
        
        whoperationview.getAdminUsersListRefreshButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                RefreshUsersListTable();

            }
        });
        
        whoperationview.getAdminFunctionsUsersTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {

            
            
            if((whoperationview.getAdminFunctionsUsersTable().getSelectedRow() != -1)){
                int i = whoperationview.getAdminFunctionsUsersTable().getSelectedRow();
                int j = Integer.valueOf(whoperationview.getAdminFunctionsUsersTable().getValueAt(i,0).toString());
                SetUsersProductsListTabelContent(j);
            }
 
        }
    });
        
        
        
       whoperationview.getAdminProductsListRefreshButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                RefreshUsersProductsListTable();

            }
        });
       
         whoperationview.getAdminExportUserListButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                XmlExporter.ImportUsers(userslist);

            }
        });
        
    }
   
    /**
     * Az adott tablat tolti fel az adtabazis beli termkekkel
     * @param id
     */
    public void SetProductListTableContent(int id){
        
        try {
            userproducts = null;
            userproducts = whoperationmodel.GetProducts(id);
        } catch (SQLException ex) {
            Logger.getLogger(WHOperationController.class.getName()).log(Level.SEVERE, null, ex);
            whoperationview.ScreenMessageError(ex.getMessage());

        }
        for (Map.Entry<Integer, ProductContainer> entry : userproducts.entrySet()) {
                
            ProductContainer temp = entry.getValue();
            whoperationview.getDeftablemodel().addRow(new Object[]{temp.getId(),temp.getName(),temp.getType(),temp.getAmount(),temp.getComment()});

        }
        

    }

    /**
     * Az adott tablat tolti fel az adtabazis beli termkekkel
     */
    public void SetUsersListTabelContent(){
        
        try {
            userslist = whoperationmodel.GetUsersList();
        } catch (SQLException ex) {
            Logger.getLogger(WHOperationController.class.getName()).log(Level.SEVERE, null, ex);
            whoperationview.ScreenMessageError(ex.getMessage());
           
        }
        for (Map.Entry<Integer, UserContainer> entry : userslist.entrySet()) {
                
            UserContainer temp = entry.getValue();
            whoperationview.getUsersTableModel().addRow(new Object[]{temp.getId(),temp.getUsername(),temp.getName(),temp.getEmail(),temp.getBday(),temp.getRole()});

        }
    }
     
    /**
     * Az adott tablat tolti fel az adtabazis beli felhasznaloi adatokkal
     * @param id
     */
    public void SetUsersProductsListTabelContent(int id){
        
        Map<Integer,ProductContainer> tempuserproducts=null;
        try {
            tempuserproducts = whoperationmodel.GetProducts(id);
        } catch (SQLException ex) {
            Logger.getLogger(WHOperationController.class.getName()).log(Level.SEVERE, null, ex);
            whoperationview.ScreenMessageError(ex.getMessage());
        }
        
        whoperationview.getUsersproductstablemodel().setRowCount(0);
        
        if(tempuserproducts != null){
            for (Map.Entry<Integer, ProductContainer> entry : tempuserproducts.entrySet()) {
                
                ProductContainer temp = entry.getValue();
                whoperationview.getUsersproductstablemodel().addRow(new Object[]{temp.getId(),temp.getName(),temp.getType(),temp.getAmount(),temp.getComment()});

            }
        }
    }
    
    /**
     * Termektabla szurot keszit 
     * @return
     */
    public RowFilter<Object,Object> CreateFilterProductList(){
    
            RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                public boolean include(RowFilter.Entry entry) {
                    
                    String name = entry.getValue(1).toString().toLowerCase();
                    String type = entry.getValue(2).toString().toLowerCase();
                    boolean trya = (name.contains(whoperationview.getNameFilterTextField().getText().toLowerCase()) && type.contains(whoperationview.getTypeFilterTextField().getText().toLowerCase()));

                    return trya;
                    
                }
            };
    
            return filter;
    
    }

    /**
     * Termektabla szurot keszit 
     * @return
     */
    public RowFilter<Object,Object> CreateFilterOperation(){
       
    
            RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                public boolean include(RowFilter.Entry entry) {
                    
                    String name = entry.getValue(1).toString().toLowerCase();
                    String type = entry.getValue(2).toString().toLowerCase();
                    boolean trya = (name.contains(whoperationview.getAddProductNameFilterTextField().getText().toLowerCase()) && type.contains(whoperationview.getAddProductTypeFilterTextField().getText().toLowerCase()));
                    
                    
                    return trya;
                    
                }
            };
    
            return filter;
    
    }
    
    /**
    * Uj termek hozzadasat kezeli le
    */
    private void AddProduct(){
    
        logger.log(Level.FINEST, "Attempt to adding new product.");
        
        
        if((whoperationview.getAddProductNameTextField().getText().isEmpty() || whoperationview.getAddProductNameTextField().getText()==null)){
        
            logger.log(Level.FINEST, "Adding new product failed : The name field can not be empty.");
            whoperationview.ScreenMessage("The name field can not be empty.");
            return;
        
        }
        
        if((whoperationview.getAddProductNameTextField().getText().length() > 40)){
        
            logger.log(Level.FINEST, "Adding new product failed : The name field is too long");
            whoperationview.ScreenMessage("The name field can not be longer than 40 characters.");
            return;
        
        }
        
        if((whoperationview.getAddProductTypeTextField().getText().isEmpty() || whoperationview.getAddProductTypeTextField().getText()==null)){
        
            logger.log(Level.FINEST, "Adding new product failed : The type field can not be empty.");
            whoperationview.ScreenMessage("The type field can not be empty.");
            return;
        
        }
        
        if((whoperationview.getAddProductTypeTextField().getText().length() > 40)){
        
            logger.log(Level.FINEST, "Adding new product failed : The type field is too long");
            whoperationview.ScreenMessage("The type field can not be longer than 40 characters.");
            return;
        
        }
    
        if(whoperationview.getAddProductAmountTextField().getText().isEmpty() || whoperationview.getAddProductAmountTextField().getText()==null){
        
            logger.log(Level.FINEST, "Adding new product failed : The amount field can not be empty.");
            whoperationview.ScreenMessage("The amount field can not be empty.");
            return;
        
        }
        
        if(!FieldChecker.AmountCheck(whoperationview.getAddProductAmountTextField().getText())){
        
            logger.log(Level.FINEST, "Adding new product failed : Wrong amount format, only positive integers are allowed.");
            whoperationview.ScreenMessage("Wrong amount format, only positive integers are allowed.");
            return;
        
        }
        
       if(whoperationview.getAddProductAmountTextField().getText().length() > String.valueOf(Integer.MAX_VALUE).length() || Long.valueOf(whoperationview.getAddProductAmountTextField().getText())> Integer.MAX_VALUE){
        
            logger.log(Level.FINEST, "Adding new product failed : "+ "Amount can not be bigger than: " + String.valueOf(Integer.MAX_VALUE) + ".");
            whoperationview.ScreenMessage("Amount can not be bigger than: " + String.valueOf(Integer.MAX_VALUE) + ".");
            return;
        
        }
        
        if(whoperationview.getAddProductCommentTextArea().getText().length() > 200){
        
            logger.log(Level.FINEST, "Adding new product failed : The comment can not be longer than 200 characters.");
            whoperationview.ScreenMessage("The comment can not be longer than 200 characters.");
            return;
        
        }
        try {
            
            if(whoperationmodel.GetProductsbyName(whoperationview.getAddProductNameTextField().getText())){

                logger.log(Level.FINEST, "Adding new product failed : There is already a product with this name.");
                whoperationview.ScreenMessage("There is already a product with this name.");

                return;
                
            }
            
            String name = whoperationview.getAddProductNameTextField().getText();
            String type = whoperationview.getAddProductTypeTextField().getText();
            int amount = Integer.valueOf(whoperationview.getAddProductAmountTextField().getText());
            String comment = whoperationview.getAddProductCommentTextArea().getText().replace("\n", "").replace("\r", "").replace("\t", "");
            
            
            ProductContainer newproduct = new ProductContainer(0,name,type,comment,amount);
            whoperationmodel.AddNewProduct(newproduct, maincontrollref.getLoggedinuser().getId());

            RefreshTable();
            logger.log(Level.FINEST, "Adding new product was successful.");
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            whoperationview.ScreenMessageError(ex.getMessage());
        }
        
    
    }
    
    /**
    * Mar bent levo termekhez ad hozza ujabb mennyiseget
    */
    private void AddExsitingProduct(){
        
        logger.log(Level.FINEST, "Attempt to adding existing product.");
    
        if(whoperationview.getExistingProductCheckBox().isSelected() && whoperationview.getAddProductListTable().getSelectedRow() == -1){
        
            logger.log(Level.FINEST, "Adding existing product failed : None of the rows was selected.");
            whoperationview.ScreenMessage("None of the rows was selected.");
            return;

        }
        
    
        if(whoperationview.getAddProductAmountTextField().getText().isEmpty() || whoperationview.getAddProductAmountTextField().getText()==null){
        
            logger.log(Level.FINEST, "Adding existing product failed : The amount field can not be empty.");
            whoperationview.ScreenMessage("The amount field can not be empty.");
            return;
        
        }
        
        if(!FieldChecker.AmountCheck(whoperationview.getAddProductAmountTextField().getText())){
        
            logger.log(Level.FINEST, "Adding existing product failed : Wrong amount format, only positive integers are allowed.");
            whoperationview.ScreenMessage("Wrong amount format, only positive integers are allowed.");
            return;
        
        }
        
       if(whoperationview.getAddProductAmountTextField().getText().length() > String.valueOf(Integer.MAX_VALUE).length() || Long.valueOf(whoperationview.getAddProductAmountTextField().getText())> Integer.MAX_VALUE){
        
            logger.log(Level.FINEST, "Adding existing product failed : "+"Amount can not be bigger than: " + String.valueOf(Integer.MAX_VALUE) + ".");
            whoperationview.ScreenMessage("Amount can not be bigger than: " + String.valueOf(Integer.MAX_VALUE) + ".");
            return;
        
        }

        try {
 
                int productid =  Integer.valueOf(whoperationview.getAddProductListTable().getValueAt(whoperationview.getAddProductListTable().getSelectedRow(),0).toString());
                int amount = Integer.valueOf(whoperationview.getAddProductAmountTextField().getText());
            
                if(!whoperationmodel.AddExistingProduct(productid, amount)){
                
                    logger.log(Level.FINEST, "Adding existing product failed : "+"Failure! The operation exceeded the product amount limit. Limit: " + Integer.MAX_VALUE + ".");
                    whoperationview.ScreenMessage("Failure! The operation exceeded the product amount limit. Limit: " + Integer.MAX_VALUE + ".");
                    return;
                 
                }
                
                RefreshTable();
                logger.log(Level.FINEST, "Adding existing product was successful.");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            whoperationview.ScreenMessageError(ex.getMessage());
        }

    
    }
    
    /**
    * Mar bent levo termekbol vesz el adott mennyiseget
    */
    private void RemoveProduct(){
        logger.log(Level.FINEST, "Attempt to remove existing product.");
    
        if(whoperationview.getAddProductListTable().getSelectedRow() == -1){
        
            logger.log(Level.FINEST, "Removing existing product failed.");
            whoperationview.ScreenMessage("None of the rows was selected.");
            return;

        }
    
         if(whoperationview.getAddProductRemoveTextField().getText().isEmpty() || whoperationview.getAddProductRemoveTextField().getText()==null){
        
            logger.log(Level.FINEST, "Removing existing product failed : The amount field can not be empty.");
            whoperationview.ScreenMessage("The amount field can not be empty.");
            return;
        
        }
         
         if(!FieldChecker.AmountCheck(whoperationview.getAddProductRemoveTextField().getText())){
        
            logger.log(Level.FINEST, "Removing existing product failed : Wrong amount format, only positive integers are allowed.");
            whoperationview.ScreenMessage("Wrong amount format, only positive integers are allowed.");
            return;
        
        }
        
       if(whoperationview.getAddProductRemoveTextField().getText().length() > String.valueOf(Integer.MAX_VALUE).length() || Long.valueOf(whoperationview.getAddProductRemoveTextField().getText())> Integer.MAX_VALUE){
        
            logger.log(Level.FINEST, "Removing existing product failed : "+"Amount can not be bigger than: " + String.valueOf(Integer.MAX_VALUE) + ".");
            whoperationview.ScreenMessage("Amount can not be bigger than: " + String.valueOf(Integer.MAX_VALUE) + ".");
            return;
        
        }
       
        try {
 
                int productid =  Integer.valueOf(whoperationview.getAddProductListTable().getValueAt(whoperationview.getAddProductListTable().getSelectedRow(),0).toString());
                int amount = Integer.valueOf(whoperationview.getAddProductRemoveTextField().getText());
            
                if(!whoperationmodel.RemoveProduct(amount, productid)){
                
                    logger.log(Level.FINEST, "Removing existing product failed : Failure! Take out amount can not be bigger than the product amount.");
                    whoperationview.ScreenMessage("Failure! Take out amount can not be bigger than the product amount.");
                    return;
                 
                }
                
                RefreshTable();
                logger.log(Level.FINEST, "Removing existing product was successful.");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            whoperationview.ScreenMessageError(ex.getMessage());
        }
       
    
    }
    
    /**
     * Ablak eldobasa
     */
    public void DisposeWindow(){
    
        whoperationview.dispose();
    
    }
    
    
    /**
    * Tabla frissitese
    */
    private void RefreshTable(){
    
        whoperationview.getProductListSorter().setRowFilter(null);
        whoperationview.getDeftablemodel().setRowCount(0);
        SetProductListTableContent(maincontrollref.getLoggedinuser().getId());

    }
    /**
    * Tabla frissitese
    */
    private void RefreshUsersListTable(){
    
        whoperationview.getUsersTableModel().setRowCount(0);
        SetUsersListTabelContent();

    }
    /**
    * Tabla frissitese
    */
    private void RefreshUsersProductsListTable(){
    
            if((whoperationview.getAdminFunctionsUsersTable().getSelectedRow() != -1)){
                int i = whoperationview.getAdminFunctionsUsersTable().getSelectedRow();
                int j = Integer.valueOf(whoperationview.getAdminFunctionsUsersTable().getValueAt(i,0).toString());
                SetUsersProductsListTabelContent(j);
            }

    }
    
}
