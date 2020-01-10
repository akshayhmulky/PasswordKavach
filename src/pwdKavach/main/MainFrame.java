/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwdKavach.main;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import pwdKavach.database.DatabaseHandler;
import pwdKavach.ui.addgroup.AddGroup;
import pwdKavach.ui.additem.AddAccount;
import pwdKavach.ui.login.LoginForm;


/**
 *
 * @author Administrator
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
   
 DatabaseHandler handler = null;   
// private static int groupID = -1;
// private static int accountID = -1;
 boolean groupItemSelected = false;
 private boolean consume = false;
    
    public MainFrame() {
        handler = DatabaseHandler.getInstance();
        initComponents();
        
        if(!isListTableEmpty()){
            loadAllCategories();
            loadAllListTable();
            TableListSelection();
            tblList.requestFocus();
            tblList.changeSelection(0,0,false, false);
       }

        
        //TableListSelection();  //Acts lits list selection changes Trigger
        lblWelcomeMessage.setText("Hello, " + handler.getUsername(LoginForm.getID()));
        //loadAllListCategories();
        tblAccountTable.setName("loadAll");
        
        //tblList.requestFocus();
        //tblList.changeSelection(0,0,false, false);
        //listGroup.setSelectedValue("Banking", true);
    }

//    //Sending list item to AddAccount (comboBox)
//    public ArrayList<String> getGroupListElements(){
//        ArrayList<String> listGroupToBeSent = new ArrayList<>();
//        for(int i=0; i< listGroup.getModel().getSize();i++){
//            if(!(listGroup.getModel().getElementAt(i).toString().equals("All"))) // add all except "All"
//            listGroupToBeSent.add(listGroup.getModel().getElementAt(i).toString());
//        }
//        return listGroupToBeSent;   
//    }
  
    public boolean isListTableEmpty() {
        if (tblList != null && tblList.getModel() != null) {
            return tblList.getModel().getRowCount()<=0?true:false;
        }
        return false;
    }
    
    public boolean isAccountTableEmpty() {
        if (tblAccountTable != null && tblAccountTable.getModel() != null) {
            return tblAccountTable.getModel().getRowCount()<=0?true:false;
        }
        return false;
    }
    
    private void loadAllCategories(){
        
        fillAccountTable("", "");
    }     
    
    //Fill table
    private void fillAccountTable(String groupname, String accountName)
    {
    String columnNames[] = {"Title", "Username", "Password", "URL", "Group"}; //Set table names
    
    //Making table cell uneditable
    DefaultTableModel tableModel = new DefaultTableModel();
     
    tableModel.setColumnIdentifiers(columnNames);
//    tblAccountTable.setEnabled(false); // NEED TO CHECK
        
    ResultSet resultSet =  handler.getAccountResultSet(groupname, accountName);
    
    
    if(resultSet == null)
    {
        JOptionPane.showMessageDialog(null, "An error occured no ResultSet, main.java", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
        try {
            
            System.out.println("loading table");
            while(resultSet.next()){
                System.out.println("inside resultset");
                String title = resultSet.getString("title");
                System.out.println(title);
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String url = resultSet.getString("url");
                String groupName = resultSet.getString("groupname");
                
                
                Object[] row = new Object[5];
                
                row[0] = title;
                row[1] = username;
                row[2] = password;  
                row[3] = url;
                row[4] = groupName;
                
                tableModel.addRow(row);
                
                
            }
            
        } catch (SQLException e) {
            System.out.println("FillAccountTable error" + e.getMessage());
        }
        
       tblAccountTable.setDefaultEditor(Object.class, null); //Disable editing option
        tblAccountTable.setModel(tableModel);
        //tableModel.fireTableDataChanged();
        
    }
   
    private void loadAllListTable(){
        fillListTable("");
    }
    
    private void fillListTable(String groupname)
    {
    String columnNames[] = {"Group"}; //Set table names
    
    DefaultTableModel tableModel = new DefaultTableModel();    
    tableModel.setColumnIdentifiers(columnNames);       
    ResultSet resultSet =  handler.getGroupResultSet(groupname);
    if(resultSet == null)
    {
        JOptionPane.showMessageDialog(null, "An error occured no ResultSet, main.java", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
        try {
            
            System.out.println("loading List table");
            while(resultSet.next()){
                System.out.println("inside resultset");
                String groupName = resultSet.getString("groupname");
                
                
                Object[] row = new Object[1];
                
                row[0] = groupName;
                
                tableModel.addRow(row);
                
//                TableListSelection();  // on changing list table select its res data in right table
//                tblList.requestFocus();
//                tblList.changeSelection(0,0,false, false);
            }
            
        } catch (SQLException e) {
            System.out.println("FillAccountTable error" + e.getMessage());
        }
        
           tblList.setDefaultEditor(Object.class, null); //Disable editing option
           tblList.setModel(tableModel);
            //tableModel.fireTableDataChanged();
        //}
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        btnDeletePopUpMenu = new javax.swing.JMenuItem();
        btnEditPopupMenu = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        btnDeleteGroup = new javax.swing.JMenuItem();
        btnEditGroup = new javax.swing.JMenuItem();
        lblWelcomeMessage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAccountTable = new javax.swing.JTable();
        btnAddEntry = new javax.swing.JButton();
        btnAddGroup = new javax.swing.JButton();
        test = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();

        btnDeletePopUpMenu.setText("Delete");
        btnDeletePopUpMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePopUpMenuActionPerformed(evt);
            }
        });
        jPopupMenu1.add(btnDeletePopUpMenu);

        btnEditPopupMenu.setText("Edit");
        btnEditPopupMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPopupMenuActionPerformed(evt);
            }
        });
        jPopupMenu1.add(btnEditPopupMenu);

        btnDeleteGroup.setText("Delete");
        btnDeleteGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteGroupActionPerformed(evt);
            }
        });
        jPopupMenu2.add(btnDeleteGroup);

        btnEditGroup.setText("Edit");
        btnEditGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditGroupActionPerformed(evt);
            }
        });
        jPopupMenu2.add(btnEditGroup);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblWelcomeMessage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblWelcomeMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcomeMessage.setText("Hello, Username");

        tblAccountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAccountTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblAccountTableMouseReleased(evt);
            }
        });
        tblAccountTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblAccountTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblAccountTable);

        btnAddEntry.setText("Add Entry");
        btnAddEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEntryActionPerformed(evt);
            }
        });

        btnAddGroup.setText("Add Group");
        btnAddGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddGroupActionPerformed(evt);
            }
        });

        test.setText("jButton1");
        test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testActionPerformed(evt);
            }
        });

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblList.setGridColor(new java.awt.Color(204, 255, 255));
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblListMouseReleased(evt);
            }
        });
        tblList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblListKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblListKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnAddGroup)
                .addGap(18, 18, 18)
                .addComponent(btnAddEntry)
                .addGap(43, 43, 43)
                .addComponent(lblWelcomeMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(test)
                .addGap(84, 84, 84))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblWelcomeMessage)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAddEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(2, 2, 2)))
                    .addComponent(test))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //click add button
    private void btnAddEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEntryActionPerformed
       
        AddAccount addAccount = new AddAccount();
        addAccount.pack();
        addAccount.setLocationRelativeTo(null);
        addAccount.setVisible(true);
        addAccount.setbuttonOK("ADD");
        //this.setEnabled(false);
        //String selectedItemFromList = (String) listGroup.getSelectedValue();
        int column = 0;
        int row = tblList.getSelectedRow();
        String selectedItemFromList = tblList.getModel().getValueAt(row, column).toString();
        if(!selectedItemFromList.equals("All")){
            addAccount.inflateUI(selectedItemFromList);
        }
        
        addAccount.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
     
        //actions to be performed on closure of "AddAccount.java" window, here load the table to reflect changes
       addAccount.addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e)
          {
              //if (!groupItemSelected){
                //loadAllCategories();  
              //}
              //else
              //{
                String selectedItemFromComboBox = addAccount.getSelectedItem(); //Coming from AddAccount.java class
                int selectedIndexFromComboBox = addAccount.getSelectedIndex(); //Coming from AddAccount.java class
                
                fillAccountTable(selectedItemFromComboBox, ""); //Refresh table to show the selected value from combobox

                tblList.requestFocus();
                tblList.changeSelection(selectedIndexFromComboBox,0,false, false);
                //IF NEED TO SELECT LAST ELEMENAT OF A ACCOUNT TABLE THEN CAN USE THIS
//                int lastRow = tblAccountTable.convertRowIndexToView(tblAccountTable.getRowCount() - 1);
//                tblAccountTable.setRowSelectionInterval(lastRow, lastRow);
                
                
              //}   
                  System.out.println(selectedItemFromComboBox);
                e.getWindow().dispose();
          }
      });
       
    }//GEN-LAST:event_btnAddEntryActionPerformed
  
    
    //Update the table based on selection of List table
    public void TableListSelection(){
        
        if(!isListTableEmpty()){
        ListSelectionModel model = tblList.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
               if(!model.isSelectionEmpty())
               {
                   
                   int row = tblList.getSelectedRow();
                   String clickedItem = (tblList.getModel().getValueAt(row, 0).toString());
                   if(clickedItem.equals("All"))
                        {
                      loadAllCategories();
                        }
                    else
                    {
                    fillAccountTable(clickedItem, "");   
                     }                   
               }
            }
        });
        }
    }
    
    
    private void btnAddGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddGroupActionPerformed
        
        AddGroup addGroup = new AddGroup();
        addGroup.pack();
        addGroup.setLocationRelativeTo(null);
        addGroup.setGroupButton("ADD");
        addGroup.setVisible(true);
        
        addGroup.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        addGroup.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        int row = tblList.getSelectedRow();
        loadAllListTable();
//        tblList.requestFocus();
//        tblList.changeSelection(row,0,false, false);
        int lastRow = tblList.convertRowIndexToView(tblList.getRowCount() - 1);
        tblList.setRowSelectionInterval(lastRow, lastRow);
        
    }
});
        
        
    }//GEN-LAST:event_btnAddGroupActionPerformed

    private void testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testActionPerformed
 
    }//GEN-LAST:event_testActionPerformed

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
       
//      int row = tblList.getSelectedRow();
//      String clickedItem = (tblList.getModel().getValueAt(row, 0).toString());
//      
//      if(clickedItem.equals("All"))
//      {
//          loadAllCategories();
//      }
//      else
//      {
//        fillAccountTable(clickedItem, "");   
//      }
    }//GEN-LAST:event_tblListMouseClicked

    private void tblListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblListKeyPressed
//       int row = tblList.getSelectedRow();
//      String clickedItem = (tblList.getModel().getValueAt(row, 0).toString());
//      
//      if(clickedItem.equals("All"))
//      {
//          loadAllCategories();
//      }
//      else
//      {
//        fillAccountTable(clickedItem, "");   
//      }
    }//GEN-LAST:event_tblListKeyPressed

    
    //Mouse release is nothing but righclick
    private void tblAccountTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAccountTableMouseReleased
       
        if(!tblAccountTable.getSelectionModel().isSelectionEmpty()){
        if(evt.isPopupTrigger()){
            jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
        }
        }
        
    }//GEN-LAST:event_tblAccountTableMouseReleased

    private void deleteAccountRecord(){
        //DefaultTableModel model = (DefaultTableModel) tblAccountTable.getModel();
        //selected row from table
//        if(!isListTableEmpty()){
        int rowIndex = tblAccountTable.getSelectedRow();
        String selectedItem = tblAccountTable.getValueAt(rowIndex, 0).toString();
        
        //Selected row from listtable
        int row = tblList.getSelectedRow();
        String clickedItem = (tblList.getModel().getValueAt(row, 0).toString());
        
        String title = tblAccountTable.getValueAt(rowIndex, 0).toString();
        String username = tblAccountTable.getValueAt(rowIndex, 1).toString();
        String password = tblAccountTable.getValueAt(rowIndex, 2).toString();
        String url = tblAccountTable.getValueAt(rowIndex, 3).toString();
        String groupname = tblAccountTable.getValueAt(rowIndex, 4).toString();  
        //System.out.println("Before deletion: " + title + "username" + username + "pwd" +password+ "url" +url+ "group" +groupname);
        
        int groupID = handler.findGroupID(groupname);
        int accountID = handler.findAccountId(LoginForm.getID(), groupID, title, username, password, url, groupname);
       // System.out.println("group id"+ groupID);
       // System.out.println("account id"+ accountID);
        
        if(handler.deleteRecordFromAccount(accountID)){
            
            JOptionPane.showMessageDialog(null, "Record delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            fillAccountTable(clickedItem, "");
        }
        else{
            JOptionPane.showMessageDialog(null, "Record was not deleted", "Failed", JOptionPane.ERROR_MESSAGE);
        }
//        }
        
    }
    
    //perform delete on click on delete popupmenu
    private void btnDeletePopUpMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePopUpMenuActionPerformed
        int input = JOptionPane.showConfirmDialog (null, "Are you sure you wanna delete?","Warning",JOptionPane.YES_NO_OPTION);
        if(input == JOptionPane.YES_OPTION){
          deleteAccountRecord(); 
        }
        else{
            return;
        }      
    }//GEN-LAST:event_btnDeletePopUpMenuActionPerformed

    
    private void btnEditPopupMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPopupMenuActionPerformed

        DefaultTableModel model = (DefaultTableModel) tblAccountTable.getModel();
        //selected row from table
        int rowIndex = tblAccountTable.getSelectedRow();
        String selectedItem = tblAccountTable.getValueAt(rowIndex, 0).toString();
        
        //Selected row from listtable
        int row = tblList.getSelectedRow();
        String clickedItem = (tblList.getModel().getValueAt(row, 0).toString());
        
        String title = tblAccountTable.getValueAt(rowIndex, 0).toString();
        String username = tblAccountTable.getValueAt(rowIndex, 1).toString();
        String password = tblAccountTable.getValueAt(rowIndex, 2).toString();
        String url = tblAccountTable.getValueAt(rowIndex, 3).toString();
        String groupname = tblAccountTable.getValueAt(rowIndex, 4).toString(); 
        System.out.println("From updation Main : " + title + "username" + username + "pwd" +password+ "url" +url+ "group" +groupname);
        int groupID = handler.findGroupID(groupname);
        int accID = handler.findAccountId(LoginForm.getID(), groupID , title, username, password, url, groupname);
        AddAccount popUp = new AddAccount(accID, title, username, password, url, groupname);
        
        //AddAccount popUp = new AddAccount();
        popUp.pack();
        popUp.setLocationRelativeTo(null);
        popUp.setVisible(true);
        popUp.updateWelcomeTitleLabel("Edit Entry");
        popUp.setbuttonOK("UPDATE"); // to identify it as update operation
        popUp.updateEntryActionFillPerform();
        //populateAccountTableForEdit();
        popUp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 
        
        
        //actions to be performed on closure of "AddAccount.java" window, here load the table to reflect changes
       popUp.addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e)
          {
              //if (!groupItemSelected){
                //loadAllCategories();  
              //}
              //else
              //{
                String selectedItemFromComboBox = popUp.getSelectedItem(); //Coming from AddAccount.java class
                int selectedIndexFromComboBox = popUp.getSelectedIndex(); //Coming from AddAccount.java class
                
                fillAccountTable(selectedItemFromComboBox, ""); //Refresh table to show the selected value from combobox

                tblList.requestFocus();
                tblList.changeSelection(selectedIndexFromComboBox,0,false, false);
                
                
              //}   
                  System.out.println(selectedItemFromComboBox);
                e.getWindow().dispose();
          }
      });
        
    }//GEN-LAST:event_btnEditPopupMenuActionPerformed
    
    private void deleteGroup(){
        //DefaultTableModel model = (DefaultTableModel) tblAccountTable.getModel();
        //selected row from table
        if(!isListTableEmpty()){
        int rowIndex = tblList.getSelectedRow();
        String selectedItem = tblList.getValueAt(rowIndex, 0).toString();
        
        //Selected row from listtable
        int row = tblList.getSelectedRow();
        String clickedItem = (tblList.getModel().getValueAt(row, 0).toString());
        
        String groupname = tblList.getValueAt(rowIndex, 0).toString();

        int groupID = handler.findGroupID(groupname);
        System.out.println("group id"+ groupID);

        if(handler.deleteGroup(groupID)){
            
            JOptionPane.showMessageDialog(null, "Group deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            fillAccountTable(clickedItem, "");
            loadAllListTable();
            tblList.requestFocus();
            tblList.changeSelection(0,0,false, false);
        }
        else{
            JOptionPane.showMessageDialog(null, "Group was not deleted", "Failed", JOptionPane.ERROR_MESSAGE);
        }
        }
        
    }   
   // make popup menu work 
    private void tblListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseReleased

         if(!tblList.getSelectionModel().isSelectionEmpty()){
         if(evt.isPopupTrigger()){
            jPopupMenu2.show(evt.getComponent(), evt.getX(), evt.getY());
        }
         }
    }//GEN-LAST:event_tblListMouseReleased

    //Perform delete operation
    private void btnDeleteGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteGroupActionPerformed
        int input = JOptionPane.showConfirmDialog (null, "Are you sure you wanna delete?","Warning",JOptionPane.YES_NO_OPTION);
        if(input == JOptionPane.YES_OPTION){
          deleteGroup();  
        }
        else{
            return;
        }
    }//GEN-LAST:event_btnDeleteGroupActionPerformed

    private void tblListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblListKeyReleased
        int code=evt.getKeyChar(); 
        if(code==KeyEvent.VK_DELETE){
            int input = JOptionPane.showConfirmDialog (null, "Are you sure?","Warning",JOptionPane.YES_NO_OPTION);
            if(input == JOptionPane.YES_OPTION){
            deleteGroup();     
            }  else{
            return;
        }
        } 
    }//GEN-LAST:event_tblListKeyReleased

    private void tblAccountTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAccountTableKeyReleased
        int code=evt.getKeyChar(); 
        if(code==KeyEvent.VK_DELETE){
            int input = JOptionPane.showConfirmDialog (null, "Are you sure?","Warning",JOptionPane.YES_NO_OPTION);
            if(input == JOptionPane.YES_OPTION){
            deleteAccountRecord();    
            }  else{
            return;
        }
        } 
    }//GEN-LAST:event_tblAccountTableKeyReleased

    private void btnEditGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditGroupActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblAccountTable.getModel();
        //selected row from table
        int rowIndex = tblList.getSelectedRow();
        String selectedItem = tblList.getValueAt(rowIndex, 0).toString();
        
        String groupname = tblList.getValueAt(rowIndex, 0).toString(); 
        int groupID = handler.findGroupID(groupname);

        AddGroup popUp = new AddGroup(groupID, groupname);
        //AddAccount popUp = new AddAccount();
        popUp.pack();
        popUp.setLocationRelativeTo(null);
        popUp.setVisible(true);
        popUp.setGroupWelcomeTitle("Edit Entry");
        popUp.setGroupButton("UPDATE"); // to identify it as update operation
        popUp.editGroupAction();
        popUp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //actions to be performed on closure of "AddAccount.java" window, here load the table to reflect changes
       popUp.addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e)
          {
                fillListTable("");
                tblList.requestFocus();
                tblList.changeSelection(rowIndex,0,false, false);
  
                e.getWindow().dispose();
          }
      });
    }//GEN-LAST:event_btnEditGroupActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddEntry;
    private javax.swing.JButton btnAddGroup;
    private javax.swing.JMenuItem btnDeleteGroup;
    private javax.swing.JMenuItem btnDeletePopUpMenu;
    private javax.swing.JMenuItem btnEditGroup;
    private javax.swing.JMenuItem btnEditPopupMenu;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblWelcomeMessage;
    private javax.swing.JTable tblAccountTable;
    private javax.swing.JTable tblList;
    private javax.swing.JButton test;
    // End of variables declaration//GEN-END:variables
}
