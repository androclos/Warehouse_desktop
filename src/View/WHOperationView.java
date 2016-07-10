package View;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Pifko
 */
public class WHOperationView extends javax.swing.JFrame {

    /**
     * Creates new form WHOperationView
     */
    public WHOperationView() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ///ilyen modellu lesz a jtable////
        TableModel model = (new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Type", "Amount", "Comment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            
        });
        
         TableModel model2 = (new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Type", "Amount", "Comment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            
        });
        
       TableModel usersmodel = (new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "User Name", "Full Name", "Email Address", "Date of Birth", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            
        });


           ProductListTable.setModel((DefaultTableModel)model);
           AddProductListTable.setModel((DefaultTableModel)model);
           AdminFunctionsUsersTable.setModel((DefaultTableModel)usersmodel);
           AdminFunctionsUsersProductsTable.setModel((DefaultTableModel)model2);
           
           TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(model);
           viewsorter = sorter1;
           sorter1.setSortsOnUpdates(true);
           sorter1.setRowFilter(null);
           
           TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(model);
           viewsorter2 = sorter2;
           sorter2.setSortsOnUpdates(true);
           sorter2.setRowFilter(null);
           
           TableRowSorter<TableModel> sorter3 = new TableRowSorter<TableModel>(usersmodel);
           viewsorter3 = sorter3;
           sorter3.setSortsOnUpdates(true);
           sorter3.setRowFilter(null);
           
           ProductListTable.setRowSorter(sorter1);
           AddProductListTable.setRowSorter(sorter2);
           AdminFunctionsUsersTable.setRowSorter(sorter3);
           
           ProductListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           AddProductListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           AdminFunctionsUsersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           AdminFunctionsUsersProductsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           
           ProductListTable.getTableHeader().setReorderingAllowed(false);
           AddProductListTable.getTableHeader().setReorderingAllowed(false);
           AdminFunctionsUsersTable.getTableHeader().setReorderingAllowed(false);
           AdminFunctionsUsersProductsTable.getTableHeader().setReorderingAllowed(false);
           
           deftablemodel = (DefaultTableModel)ProductListTable.getModel();
           userstablemodel = (DefaultTableModel)AdminFunctionsUsersTable.getModel();
           usersproductstablemodel = (DefaultTableModel)AdminFunctionsUsersProductsTable.getModel();
    

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        MainTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ProductListTable = new javax.swing.JTable();
        NameFilterTextField = new javax.swing.JTextField();
        TypeFilterTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ProductListRefreshButton = new javax.swing.JButton();
        ExportProductlistButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProductCommentTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        AddProductListTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        AddProductNameTextField = new javax.swing.JTextField();
        AddProductTypeTextField = new javax.swing.JTextField();
        AddProductAmountTextField = new javax.swing.JTextField();
        ExistingProductCheckBox = new javax.swing.JCheckBox();
        AddProductButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        AddProductCommentTextArea = new javax.swing.JTextArea();
        AddProductRemoveButton = new javax.swing.JButton();
        AddProductRemoveTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        AddProductNameFilterTextField = new javax.swing.JTextField();
        AddProductTypeFilterTextField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        AdminFunctionsUsersTable = new javax.swing.JTable();
        AdminUsersListRefreshButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        AdminFunctionsUsersProductsTable = new javax.swing.JTable();
        AdminExportUserListButton = new javax.swing.JButton();
        AdminProductsListRefreshButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        LogOutMenuItem = new javax.swing.JMenuItem();
        ExitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ProductListTable.setAutoCreateRowSorter(true);
        ProductListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Type", "Amount", "Comment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ProductListTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Type:");

        ProductListRefreshButton.setText("Refresh");

        ExportProductlistButton.setText("Export to file");

        ProductCommentTextArea.setEditable(false);
        ProductCommentTextArea.setColumns(20);
        ProductCommentTextArea.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        ProductCommentTextArea.setRows(5);
        jScrollPane1.setViewportView(ProductCommentTextArea);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Selected product comment:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NameFilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TypeFilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(ProductListRefreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExportProductlistButton)
                .addGap(208, 208, 208))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ExportProductlistButton)
                            .addComponent(ProductListRefreshButton)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NameFilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TypeFilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainTabbedPane.addTab("Product List", jPanel1);

        AddProductListTable.setAutoCreateRowSorter(true);
        AddProductListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Type", "Amount", "Comment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(AddProductListTable);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Name:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Type:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Amount:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Comment:");

        ExistingProductCheckBox.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        ExistingProductCheckBox.setText("Add to existing product");

        AddProductButton.setText("Add Product");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setText("(Max. 200 characters)");

        AddProductCommentTextArea.setColumns(20);
        AddProductCommentTextArea.setRows(3);
        AddProductCommentTextArea.setAutoscrolls(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, AddProductCommentTextArea, org.jdesktop.beansbinding.ELProperty.create("true"), AddProductCommentTextArea, org.jdesktop.beansbinding.BeanProperty.create("lineWrap"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, AddProductCommentTextArea, org.jdesktop.beansbinding.ELProperty.create("true"), AddProductCommentTextArea, org.jdesktop.beansbinding.BeanProperty.create("wrapStyleWord"));
        bindingGroup.addBinding(binding);

        jScrollPane5.setViewportView(AddProductCommentTextArea);

        AddProductRemoveButton.setText("Remove Product");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("Amount to take out:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setText("Name: ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Type: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(AddProductButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AddProductNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(AddProductTypeTextField)
                                    .addComponent(AddProductAmountTextField)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ExistingProductCheckBox)
                                            .addComponent(jLabel8)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(72, 72, 72)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(AddProductRemoveButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(AddProductRemoveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(10, 10, 10)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AddProductNameFilterTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(AddProductTypeFilterTextField))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(AddProductNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(AddProductTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(AddProductAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExistingProductCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(AddProductButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(AddProductNameFilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(AddProductTypeFilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddProductRemoveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(29, 29, 29)
                        .addComponent(AddProductRemoveButton)))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        MainTabbedPane.addTab("Add New Product", jPanel2);

        AdminFunctionsUsersTable.setAutoCreateRowSorter(true);
        AdminFunctionsUsersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "User Name", "Full Name", "Email Address", "Date of Birth", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(AdminFunctionsUsersTable);

        AdminUsersListRefreshButton.setText("Refresh User List");

        AdminFunctionsUsersProductsTable.setAutoCreateRowSorter(true);
        AdminFunctionsUsersProductsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(AdminFunctionsUsersProductsTable);

        AdminExportUserListButton.setText("Export Users List");

        AdminProductsListRefreshButton.setText("Refresh Product List");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(AdminUsersListRefreshButton)
                .addGap(57, 57, 57)
                .addComponent(AdminProductsListRefreshButton)
                .addGap(46, 46, 46)
                .addComponent(AdminExportUserListButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AdminUsersListRefreshButton)
                    .addComponent(AdminExportUserListButton)
                    .addComponent(AdminProductsListRefreshButton))
                .addContainerGap())
        );

        MainTabbedPane.addTab("Admin Functions", jPanel3);

        FileMenu.setText("File");

        LogOutMenuItem.setText("Log out");
        FileMenu.add(LogOutMenuItem);

        ExitMenuItem.setText("Exit");
        FileMenu.add(ExitMenuItem);

        jMenuBar1.add(FileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void ScreenMessage(String s){
    
       javax.swing.JOptionPane.showMessageDialog(null,s);

    }
    
    public JTabbedPane getjTabbedPane1() {
        return MainTabbedPane;
    }


    public JTable getProductListTable() {
        return ProductListTable;
    }

    public DefaultTableModel getDeftablemodel() {
        return deftablemodel;
    }

    public JTextField getNameFilterTextField() {
        return NameFilterTextField;
    }

    public TableRowSorter<TableModel> getProductListSorter() {
        return viewsorter;
    }

    public JTextField getTypeFilterTextField() {
        return TypeFilterTextField;
    }

    public JButton getProductListRefreshButton() {
        return ProductListRefreshButton;
    }

    public JTextArea getProductCommentTextArea() {
        return ProductCommentTextArea;
    }

    public JTextField getAddProductAmountTextField() {
        return AddProductAmountTextField;
    }

    public JButton getAddProductButton() {
        return AddProductButton;
    }

    public JTextArea getAddProductCommentTextArea() {
        return AddProductCommentTextArea;
    }

    public JTextField getAddProductNameTextField() {
        return AddProductNameTextField;
    }

    public JTextField getAddProductTypeTextField() {
        return AddProductTypeTextField;
    }

    public JCheckBox getExistingProductCheckBox() {
        return ExistingProductCheckBox;
    }

    public JButton getExportProductlistButton() {
        return ExportProductlistButton;
    }

    public JTable getAddProductListTable() {
        return AddProductListTable;
    }

    public JButton getAddProductRemoveButton() {
        return AddProductRemoveButton;
    }

    public JTextField getAddProductRemoveTextField() {
        return AddProductRemoveTextField;
    }

    public JMenuItem getExitMenuItem() {
        return ExitMenuItem;
    }

    public JMenuItem getLogOutMenuItem() {
        return LogOutMenuItem;
    }

    public JTextField getAddProductNameFilterTextField() {
        return AddProductNameFilterTextField;
    }

    public JTextField getAddProductTypeFilterTextField() {
        return AddProductTypeFilterTextField;
    }

    public TableRowSorter<TableModel> getProductListOperationsSorter() {
        return viewsorter2;
    }


    public DefaultTableModel getUsersTableModel() {
        return userstablemodel;
    }

    public JPanel getAdminFunctionsPanel() {
        return jPanel3;
    }

    public JTabbedPane getMainTabbedPane() {
        return MainTabbedPane;
    }

    public JButton getAdminUsersListRefreshButton() {
        return AdminUsersListRefreshButton;
    }

    public JTable getAdminFunctionsUsersProductsTable() {
        return AdminFunctionsUsersProductsTable;
    }

    public DefaultTableModel getUsersproductstablemodel() {
        return usersproductstablemodel;
    }

    public JTable getAdminFunctionsUsersTable() {
        return AdminFunctionsUsersTable;
    }

    public JButton getAdminExportUserListButton() {
        return AdminExportUserListButton;
    }

    public JButton getAdminProductsListRefreshButton() {
        return AdminProductsListRefreshButton;
    }

    public void ScreenMessageError(String s){
    
       javax.swing.JOptionPane.showMessageDialog(null,s,null,JOptionPane.ERROR_MESSAGE);

    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddProductAmountTextField;
    private javax.swing.JButton AddProductButton;
    private javax.swing.JTextArea AddProductCommentTextArea;
    private javax.swing.JTable AddProductListTable;
    private javax.swing.JTextField AddProductNameFilterTextField;
    private javax.swing.JTextField AddProductNameTextField;
    private javax.swing.JButton AddProductRemoveButton;
    private javax.swing.JTextField AddProductRemoveTextField;
    private javax.swing.JTextField AddProductTypeFilterTextField;
    private javax.swing.JTextField AddProductTypeTextField;
    private javax.swing.JButton AdminExportUserListButton;
    private javax.swing.JTable AdminFunctionsUsersProductsTable;
    private javax.swing.JTable AdminFunctionsUsersTable;
    private javax.swing.JButton AdminProductsListRefreshButton;
    private javax.swing.JButton AdminUsersListRefreshButton;
    private javax.swing.JCheckBox ExistingProductCheckBox;
    private javax.swing.JMenuItem ExitMenuItem;
    private javax.swing.JButton ExportProductlistButton;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuItem LogOutMenuItem;
    private javax.swing.JTabbedPane MainTabbedPane;
    private javax.swing.JTextField NameFilterTextField;
    private javax.swing.JTextArea ProductCommentTextArea;
    private javax.swing.JButton ProductListRefreshButton;
    private javax.swing.JTable ProductListTable;
    private javax.swing.JTextField TypeFilterTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel deftablemodel;
    private DefaultTableModel userstablemodel;
    private DefaultTableModel usersproductstablemodel;
    private TableRowSorter<TableModel> viewsorter;
    private TableRowSorter<TableModel> viewsorter2;
    private TableRowSorter<TableModel> viewsorter3;
}
