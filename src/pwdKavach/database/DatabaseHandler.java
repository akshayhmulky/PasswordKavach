/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwdKavach.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pwdKavach.ui.login.LoginForm;

/**
 *
 * @author Administrator
 */
public class DatabaseHandler {

    Connection conn = null;
    private static DatabaseHandler handler = null; //create DatabaseHandler object
    

    //constructor
    private DatabaseHandler() {
        createConnection(); // call below method
    }

    //create connection
    private void createConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:pwdKavach.db");
        } catch (SQLException e) {
            System.out.println("Create connection exception" + e.getMessage());
        }
    }
    
    //Dont allow to create instance from outside DatabaseHandler class
    public static DatabaseHandler getInstance()
    {
        if(handler == null){
            handler = new DatabaseHandler();
        }
        return handler;
    }
    
    
    //INSERT USERS (FOR SIGNUP FORM)
    public boolean insertUser(String username, String password)
    {
        
        PreparedStatement preparedStatement = null; // Preparedstatement protect from SQL injections
        ResultSet resultSet = null;
        try {
           //Use this whenever we are receving set from the database
           
           String countUsername = "SELECT COUNT(*) FROM USERS WHERE username = ?";
           preparedStatement = conn.prepareStatement(countUsername); //from our connection will prepare statement
           preparedStatement.setString(1, username);
           
           resultSet = preparedStatement.executeQuery(); // Gets the query back, in this case count of email
            
           if(resultSet.next()){ //If we have received query             
               if(resultSet.getInt(1) > 0){  // if we receive 1 , in case of already existing username
                   return false;
               }
              
              String insertQuery = "INSERT INTO USERS(username, password) VALUES(?, ?)";
              preparedStatement = conn.prepareStatement(insertQuery);
              preparedStatement.setString(1, username); // required for Insert query
              preparedStatement.setString(2, password);// required for Insert query
               
              int result = preparedStatement.executeUpdate(); //incase of insert, update , delete use executeUpdate, returns 1 or 0
              return (result == 1);
           }  //endif
           
        } catch (Exception e) {
            System.out.println("Insert User error" +e.getMessage());            
        }

        return false;
        
    }
    
    
    //Return id of user loggedin in case of succes login, or return -1
    //Check credentials for Login Form
    public int checkCredentials(String username, String password){
        
        String query = "SELECT id FROM USERS WHERE username = ? AND password = ?";
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                
              return resultSet.getInt(1); //Returns first paramenter value from select query (i.e id), which we can use to specify to check logged in user in Main window
            }
            
        } catch (SQLException e) {
            System.out.println("Check credentials error" +e.getMessage());
        }
//        finally // for elmininating database lock issue
//        {
//            try {
//                preparedStatement.close();
//                resultSet.close();
//                
//            } catch (SQLException e) {
//            }
//        }
        
        return -1;
    }
    
    
   //get username of loggedin user 
    public String getUsername(int id)
    {
        String username = "";
        String query = "SELECT username FROM USERS WHERE id = ?";        
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        
        try {
            
         pStatement = conn.prepareStatement(query);
         pStatement.setInt(1, id); // id we pass as parameter
         
         resultSet = pStatement.executeQuery(); //required for all select query
         
         if(resultSet.next())
             {
                 username = resultSet.getString(1);
             }
        } catch (Exception e) {
            System.out.println("Get username error" +e.getMessage());
        }
//        finally // for elmininating database lock issue
//        {
//            try {
//                pStatement.close();
//                resultSet.close();
//                
//            } catch (Exception e) {
//            }
//        }
        return username; // Since contains return type as String in ( public String)
        
    }
    
    
    //Can be used to Select group-->display table data  OR in Search OR load data table
    public ResultSet getAccountResultSet(String group, String name)
    {
        String query = "SELECT * FROM ACCOUNT INNER JOIN USERS ON (ACCOUNT.id_user = Users.id) WHERE account.id_user = '" + LoginForm.getID() + "'";
        //String whereClause = "" ;
        //String whereClause = " WHERE";
        boolean connectionWhere = false;
        
        ArrayList<String> list = new ArrayList<>();
        
        System.out.println(LoginForm.getID());
        if(!name.isEmpty())
        {
            list.add(name);
            connectionWhere = true;
            query += " AND title LIKE ?";
            System.out.println("name not empty");
        }
        
        if(!group.isEmpty())
        {
            list.add(group);
            
            if(!connectionWhere)
            {
                //query += whereClause;
                query += " AND groupname LIKE ?";
                System.out.println("group not empty");
            }
//            else
//            {   
//                //query += whereClause;
//                query+= " AND";
//                query += " groupname LIKE ?";
//                System.out.println("everything empty");
//            }
        }
        
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        
        try {
            
            System.out.println(query);
            System.out.println(connectionWhere);
            System.out.println(list.size());
            
            preparedStatement = conn.prepareStatement(query);
            
            for(int i =0; i<list.size(); i++)
            {
                System.out.println(list.get(i));
                preparedStatement.setString(i + 1, "%"+list.get(i)+ "%");               
            }
            
            
            rs = preparedStatement.executeQuery();
            
        } catch (Exception e) {
            System.out.println("getAccountResultSet error" + e.getMessage());
            return null;
        }
      /*  finally // for elmininating database lock issue
        {
            try {
                preparedStatement.close();
                rs.close();
                
            } catch (Exception e) {
            }
        } */
        
        return rs;
    }
    
    
    //Insert into Accounts table
    public boolean insertIntoAccountsTable(int idUser, int idGroup, String title, String username, String password, String url, String group)
    {
        
        PreparedStatement statement = null;
        
        String query = "INSERT INTO ACCOUNT(id_user, id_group, title, username, password, url, groupname) VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, idUser);
            statement.setInt(2, idGroup);
            statement.setString(3, title);
            statement.setString(4, username);
            statement.setString(5, password);
            statement.setString(6, url);
            statement.setString(7, group);
            
            if(statement.executeUpdate() >0){
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Insert into Account table erorr" + e.getMessage());
            
        } 
//        finally // for elmininating database lock issue
//        {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//            }
//        }
           
       return false;
    }
    
    
    //Insert into Group table
        public boolean insertIntoGroupsTable(int idUser, String groupname)
    {
        
        PreparedStatement preparedStatement = null; // Preparedstatement protect from SQL injections
        ResultSet resultSet = null;
        try {
           //Use this whenever we are receving set from the database
           
           String countGroupName = "SELECT COUNT(*) FROM GROUPS WHERE groupname = ? AND id_user = '" + LoginForm.getID() + "'";
           preparedStatement = conn.prepareStatement(countGroupName); //from our connection will prepare statement
           
           preparedStatement.setString(1, groupname);
           
           resultSet = preparedStatement.executeQuery(); // Gets the query back, in this case count of email
            
           if(resultSet.next()){ //If we have received query             
               if(resultSet.getInt(1) > 0){  // if we receive 1 , in case of already existing username
                   return false;
               }
              
              String insertQuery = "INSERT INTO GROUPS(id_user, groupname) VALUES(?, ?)";
              preparedStatement = conn.prepareStatement(insertQuery);
              preparedStatement.setInt(1, idUser);
              preparedStatement.setString(2, groupname); // required for Insert query
               
              int result = preparedStatement.executeUpdate(); //incase of insert, update , delete use executeUpdate, returns 1 or 0
              return (result == 1);
           }  //endif
           
        } catch (Exception e) {
            System.out.println("Insert Group error" +e.getMessage());            
        }

        return false;
        
    }
    

    //get group list     
    public ResultSet getGroupResultSet(String group)
   {
      String query = "SELECT * FROM GROUPS INNER JOIN USERS ON (GROUPS.id_user = Users.id) WHERE GROUPS.id_user = '" + LoginForm.getID() + "'";
      ArrayList<String> list = new ArrayList<>();
   
    System.out.println("Group ID: "+LoginForm.getID());
//         
     if(!group.isEmpty())
    {
      list.add(group);
       query += " AND groupname LIKE ?";
      }
//        
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;
//        
   try {
            System.out.println("size of group:"+list.size());            
            preparedStatement = conn.prepareStatement(query);
            
            for(int i =0; i<list.size(); i++)
            {
                System.out.println(list.get(i));
                preparedStatement.setString(i + 1, "%"+list.get(i)+ "%");                
            }           
            rs = preparedStatement.executeQuery();
            System.out.println("group rs:" +rs.getString("groupname"));
          
        } catch (Exception e) {
            System.out.println("getAccountResultSet error" + e.getMessage());
            return null;
        }

       return rs;
//        
    }     
       
  
    //find group id
    public int findGroupID(String groupname){
        
        int groupID = -1;
        //"SELECT COUNT(*) FROM GROUPS WHERE groupname = ? AND id_user = '" + LoginForm.getID() + "'";
        String query = "SELECT id FROM GROUPS WHERE groupname = ? AND id_user = '" + LoginForm.getID() + "'";
        
        
        PreparedStatement statement = null;        
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, groupname);
            
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                groupID = resultSet.getInt(1);
                System.out.println("GroupID from handler"+groupID);
                
                if(groupID == 0){
                    System.out.println("groupId is 0");
                }
            }
            
        } catch (SQLException e) 
        {
        System.out.println("findGroupId error" + e.getMessage());
        }
        return groupID;
    }
    
    
    public int findAccountId(int idUser, int idGroup, String title, String username, String password, String url, String groupname){
        
       int id = -1;
        try {

            String query = "SELECT id FROM account WHERE" +
                    " id_user = ? AND" +
                    " id_group = ? AND" +
                    " title = ? AND" +
                    " username = ? AND" +
                    " password = ? AND" +
                    " url = ? AND" +
                    " groupname = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idUser);
            statement.setInt(2, idGroup);
            statement.setString(3, title);
            statement.setString(4, username);
            statement.setString(5, password);
            statement.setString(6, url);
            statement.setString(7, groupname);
            
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    
    public boolean deleteRecordFromAccount(int id){
        String query = "DELETE FROM account WHERE id = ?";
        
        try {
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        
        int result = statement.executeUpdate();
        return (result > 0);
            
        } catch (Exception e) 
        {
            System.out.println("Delete from Account error: " +e.getMessage());
        }
       return false;
    }
    
    
    
    
    
    
}
