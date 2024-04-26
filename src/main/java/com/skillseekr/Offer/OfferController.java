package com.skillseekr.Offer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.skillseekr.Models.Offers.*;
import java.io.IOException;
import java.net.URL;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.scene.control.Button;


public class OfferController {
    @FXML
    private AnchorPane contentPane;
    @FXML
    private Button AddOffer;
    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == AddOffer) {
            loadStage("/com/Skillseekr/Offer/addOffer.fxml");
        }
    }

    // Method to load a new stage
    private void loadStage(String fxml) {
        try {
            URL resourceUrl = getClass().getResource(fxml);
            if (resourceUrl == null) {
                throw new IllegalArgumentException("FXML file not found: " + fxml);
            }
            Parent root = FXMLLoader.load(resourceUrl);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleUsersButtonClick() {
        try {
            AnchorPane showOfferPane = FXMLLoader.load(getClass().getResource("/com/Skillseekr/Offer/showOffer.fxml"));
            contentPane.getChildren().setAll(showOfferPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
