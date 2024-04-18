package Controllers.Offer;



import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class OfferIndex implements Initializable {


    private ImageView imageView;
    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public OfferIndex()
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load the image and set it to the ImageView
        Image image = new Image("/icons/SSWhite");
        setImage(image);
    }




}