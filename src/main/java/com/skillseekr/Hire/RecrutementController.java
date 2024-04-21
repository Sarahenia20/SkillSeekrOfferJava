package com.skillseekr.Hire;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;


public class RecrutementController {

    @FXML
    private AnchorPane contentPane;

    @FXML
    private void handleUsersButtonClick() {
        try {
            AnchorPane showUserPane = FXMLLoader.load(getClass().getResource("/Recrutement.fxml"));
            contentPane.getChildren().setAll(showUserPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
