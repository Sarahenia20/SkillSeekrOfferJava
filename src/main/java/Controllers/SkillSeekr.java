package Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SkillSeekr implements Initializable {

    @FXML
    private Button btnUsers;

    @FXML
    private Button btnOffers;

    @FXML
    private Button btn_Timetable;

    @FXML
    private Button btnClaims;

    @FXML
    private Button btnProjects;

    @FXML
    private Button btnHire;

    private ImageView imageView;
    public void setImage(Image image) {
        imageView.setImage(image);
    }
    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnUsers) {
            loadStage("/home/fxml/Dashboard.fxml");
        } else if (mouseEvent.getSource() == btnOffers) {
            loadStage("/home/fxml/Students.fxml");
        } else if (mouseEvent.getSource() == btn_Timetable) {
            loadStage("/");

        } else if (mouseEvent.getSource() == btnClaims) {
        loadStage("/home/fxml/Timetable.fxml");

        } else if (mouseEvent.getSource() == btnProjects) {
        loadStage("/home/fxml/Timetable.fxml");

        } else if (mouseEvent.getSource() == btnHire) {
        loadStage("/home/fxml/Timetable.fxml");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load the image and set it to the ImageView
        Image image = new Image("/icons/SSWhite");
        setImage(image);
    }
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/home/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}