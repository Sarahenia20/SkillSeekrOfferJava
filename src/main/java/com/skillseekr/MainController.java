package com.skillseekr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


public class MainController {
    @FXML
    private Button btnUsers;

    @FXML
    private Button btnOffers;

    @FXML
    private Button btnCalendar;

    @FXML
    private Button btnClaims;

    @FXML
    private Button btnProjects;

    @FXML
    private Button btnHire;


    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnOffers) {
            // Load Offers page
            loadStage("/com/Skillseekr/Offer/Offer.fxml");
        } else if ((mouseEvent.getSource() == btnHire)) {
            loadStage("/com/Skillseekr/Hire/Recrutement.fxml");
        }
        else if ((mouseEvent.getSource() == btnCalendar)) {
            loadStage("/com/Skillseekr/Calendar.fxml");
        }
    }

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
}

