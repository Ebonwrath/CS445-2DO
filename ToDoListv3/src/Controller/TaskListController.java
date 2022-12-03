package Controller;

import Database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Task;


public class TaskListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton createTaskButton;

    @FXML
    private JFXButton deleteTaskButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXListView<Task> taskListView;
    
    ObservableList<Task> list = FXCollections.observableArrayList();

    private DatabaseHandler databaseHandler;
    
    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();
        Task task = new Task();
        ResultSet taskRow = databaseHandler.getTask(task);
        
        try {
            while(taskRow.next()) {
                String taskName = taskRow.getString("task");
                LocalDate taskDate = (LocalDate) taskRow.getObject("datecreated");
                String taskDescription = taskRow.getString("description");
                
                list.add(new Task(taskDate, taskDescription, taskName));
                taskListView.setItems(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        logoutButton.setOnAction(event -> {
            showLoginScreen();
        });
        
        createTaskButton.setOnAction(event -> {
            showTaskCreationScreen();
        });
        
    }
    
    private void showLoginScreen() {
        //Take Users to addItem Screen
        logoutButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/login.fxml"));
            
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    private void showTaskCreationScreen() {
        //Take Users to addItem Screen
        createTaskButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/addItemForm.fxml"));
            
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
}
