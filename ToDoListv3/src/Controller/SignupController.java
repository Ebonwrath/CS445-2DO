/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

/**
 * FXML Controller class
 *
 * @author trham
 */
public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXTextField signUpFirstName;

    @FXML
    private JFXTextField signUpLastName;

    @FXML
    private JFXTextField signUpPassword;

    @FXML
    private JFXTextField signUpUsername;

    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            createUser();
            showLoginScreen();
        });
    }
 
    private void createUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        
        String fname = signUpFirstName.getText();
        String lname = signUpLastName.getText();
        String username = signUpUsername.getText();
        String password = signUpPassword.getText();
        
        User user = new User(fname, lname, username, password);
        
        databaseHandler.signUpUser(user);     
    }
    
    private void showLoginScreen() {
        //Take Users to addItem Screen
        signUpButton.getScene().getWindow().hide();
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
}
