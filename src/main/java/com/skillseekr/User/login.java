package com.skillseekr.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;


public class login {

    @FXML
    private TextField emailField;

    @FXML
    private Button loginButton;

    @FXML
    private Pane loginPane;

    @FXML
    private VBox loginVBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private GridPane pnlHost;

    @FXML
    void handleRegisterClick(ActionEvent event) {

    }

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == loginButton) {
            // Load Main
            loadPage("/com/Skillseekr/SSMain.fxml");
        }
    }

    private void loadPage(String fxml) {
        try {
            URL resourceUrl = getClass().getResource(fxml);
            if (resourceUrl == null) {
                throw new IllegalArgumentException("FXML file not found: " + fxml);
            }
            Parent root = FXMLLoader.load(resourceUrl);
            Scene scene = loginButton.getScene(); // Get the scene from any UI element in the current scene
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
