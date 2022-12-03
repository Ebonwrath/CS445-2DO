/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import animations.Shaker;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author trham
 */
public class AddItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addButton;
    
    @FXML
    private Label noTaskLabel;
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    void initialize() {
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker buttonShaker = new Shaker(addButton);
            buttonShaker.shake();
            
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), addButton);
            FadeTransition labelTransition = new FadeTransition(Duration.millis(2000), noTaskLabel);
            System.out.println("Mouse Clicked");
            addButton.relocate(0, 20);
            noTaskLabel.relocate(0, 85);
            
            addButton.setOpacity(0);
            noTaskLabel.setOpacity(0);
            
            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();
            
            labelTransition.setFromValue(1f);
            labelTransition.setToValue(0f);
            labelTransition.setCycleCount(1);
            labelTransition.setAutoReverse(false);
            labelTransition.play();
            
            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/view/addItemForm.fxml"));
                rootPane.getChildren().setAll(formPane);
            } catch (IOException ex) {
                Logger.getLogger(AddItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    
}
