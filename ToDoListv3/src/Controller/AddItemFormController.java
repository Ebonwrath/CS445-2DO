package Controller;

import Database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.Task;



public class AddItemFormController {
 @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXButton saveTaskButton;

    @FXML
    private JFXTextField taskField;
    
    @FXML
    private DatePicker addDate;

    @FXML
    void initialize() {
        saveTaskButton.setOnAction(event -> {
            createTask();
            showLoginScreen();
        });
    }
 
    private void createTask() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        
        String taskName = taskField.getText();
        LocalDate date = addDate.getValue();
        String description = descriptionField.getText();
        
        Task task = new Task(date, description, taskName);
        
        databaseHandler.addTask(task);     
    }
    
    private void showLoginScreen() {
        //Take Users to addItem Screen
        saveTaskButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/taskList.fxml"));
            
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
