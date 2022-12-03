package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.DatabaseHandler;
import animations.Shaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 *
 * @author trham
 */
public class LoginController {
    
     @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXButton loginSignupButton;

    @FXML
    private JFXTextField loginUsername;
    
    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();
      
        loginSignupButton.setOnAction(event -> {
            //Take Users to Signup Screen
            loginSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/signup.fxml"));
            
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        
        loginButton.setOnAction(event -> {
            
            String loginText = loginUsername.getText().trim();
            String loginPwd = loginPassword.getText().trim();
            
            User user = new User();
            user.setUsername(loginText);
            user.setPassword(loginPwd);
            
            ResultSet userRow = databaseHandler.getUser(user);
            
            int counter = 0;
            
            try {
                while(userRow.next()) {
                    counter++;
                    String name = userRow.getString("fname");
                    System.out.println("Welcome " + name);
                }
                
                if(counter == 1) {
                    showAddItemScreen();
                }
                else {
                    Shaker shakerUsername = new Shaker(loginUsername);
                    Shaker shakerPwd = new Shaker(loginPassword);
                    shakerUsername.shake();
                    shakerPwd.shake();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void showAddItemScreen() {
        //Take Users to addItem Screen
        loginSignupButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/addItem.fxml"));
            
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
