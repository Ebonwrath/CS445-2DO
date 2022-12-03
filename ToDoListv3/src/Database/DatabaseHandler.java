package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Task;
import model.User;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/"
                + dbName;
        
        Class.forName("com.mysql.jdbc.Driver");
        
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        
        return dbConnection;
    }
    
    //Write
    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USERS_TABLE + "(" + Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME + "," + Const.USERS_USERNAME + ","
                + Const.USERS_PASSWORD  + ")" + "VALUES(?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            
            preparedStatement.setString(1, user.getFname());
            preparedStatement.setString(2, user.getLname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet getUser(User user) {
        ResultSet resultSet = null;
        if(!user.getUsername().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_USERNAME + "=?" + " AND " + Const.USERS_PASSWORD
                    + "=?";
            
            try {
                //Select all from users where username = user input and password = user input
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                
               resultSet = preparedStatement.executeQuery(); 
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            System.out.println("Please enter your credentials");
        }
        return resultSet;
    }
    
    public void addTask(Task task) {
        String insert = "INSERT INTO " + Const.TASKS_TABLE + "(" + Const.TASKS_TASK + ","
                + Const.TASKS_DATE + "," + Const.TASKS_DESCRIPTION + ")" + "VALUES(?,?,?)";
        
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            
            preparedStatement.setString(1, task.getTask());
            preparedStatement.setObject(2, task.getDatecreated());
            preparedStatement.setString(3, task.getDescription());
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet getTask(Task task) {
        ResultSet resultSet = null;
        if(!task.getTask().equals("") || !task.getDatecreated().equals(null) || !task.getDescription().equals("")) {
            String query = "SELECT * FROM " + Const.TASKS_TABLE + " WHERE " + Const.TASKS_TASK + "=?" + " AND " + Const.TASKS_DATE
                    + "=?" + " AND " + Const.TASKS_DESCRIPTION + "=?";
            
            try {
                //Select all from users where username = user input and password = user input
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, task.getTask());
                preparedStatement.setObject(2, task.getDatecreated());
                preparedStatement.setString(3, task.getDescription());
                
               resultSet = preparedStatement.executeQuery(); 
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            System.out.println("No tasks");
        }
        return resultSet;
    }
    //Read
    
    //Update
    
    //Delete
}
