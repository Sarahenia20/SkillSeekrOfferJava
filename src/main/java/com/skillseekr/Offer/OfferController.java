package com.skillseekr.Offer;

import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.skillseekr.Models.Offers.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TableCell;
import com.skillseekr.Services.Offers.ServiceOffer;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import java.sql.SQLException;




public class OfferController {

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private MenuItem deleteMenuItem;
    private ServiceOffer serviceOffer;

    @FXML
    private Button AddOffer;

    @FXML
    private TableView<Offer> offerTableView;

    @FXML
    private TableColumn<Offer, Integer> idColumn;

    @FXML
    private TableColumn<Offer, String> titleColumn;

    @FXML
    private TableColumn<Offer, String> descriptionColumn;

    @FXML
    private TableColumn<Offer, String> authorColumn;

    @FXML
    private TableColumn<Offer, Date> createdAtColumn;

    @FXML
    private TableColumn<Offer, Motive> motiveColumn;

    @FXML
    private TableColumn<Offer, Type> typeColumn;

    @FXML
    private TableColumn<Offer, Location> locationColumn;

    @FXML
    private TableColumn<Offer, Status> statusColumn;

    @FXML
    private TableColumn<Offer, String> fileNameColumn;

    @FXML
    private TableColumn<Offer, List<Skill>> skillsColumn;

    @FXML
    private TableColumn<Offer, Void> actionColumn;

    @FXML
    private void initialize() {
        // Initialize TableView columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        motiveColumn.setCellValueFactory(new PropertyValueFactory<>("motive"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("file_name"));
        skillsColumn.setCellValueFactory(new PropertyValueFactory<>("skills"));

        // Set up action column
        setupActionColumn();

        // Load data into TableView
        loadDataIntoTableView();
    }

    // Method to set up the action column with "Edit" buttons
    // Method to set up the action column with "Edit" buttons
    private void setupActionColumn() {
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                // Set button style to pastel green
                editButton.setStyle("-fx-background-color: #77dd77; -fx-text-fill: white;");

                editButton.setOnAction((event) -> {
                    Offer offer = getTableView().getItems().get(getIndex());
                    loadEditOfferPage(offer);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    StackPane pane = new StackPane(editButton);
                    setGraphic(pane);
                }
            }
        });
    }

    // Method to load the edit offer page with the targeted offer
    private void loadEditOfferPage(Offer offer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Skillseekr/Offer/editOffer.fxml"));
            Parent root = loader.load();
            editOffer controller = loader.getController();
            controller.initData(offer); // Pass the offer data to the controller
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to load data into TableView
    private void loadDataIntoTableView() {
        try {
            serviceOffer = new ServiceOffer(); // Initialize the service
            List<Offer> offers = serviceOffer.show(); // Fetch data from the service
            offerTableView.getItems().addAll(offers); // Populate the TableView
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (e.g., show error message)
        }
    }

    // Method to handle button clicks
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
    public void deleteSelectedOffer() {
        Offer selectedOffer = offerTableView.getSelectionModel().getSelectedItem();
        if (selectedOffer != null) {
            try {
                serviceOffer.delete(selectedOffer);
                offerTableView.getItems().remove(selectedOffer);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
