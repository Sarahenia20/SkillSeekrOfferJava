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
import javafx.scene.control.TableCell;
import com.skillseekr.Services.Offers.ServiceOffer;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import java.io.File;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.IOUtils;





public class showOffer {

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
    private Button downloadButton;

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
        refreshTablePeriodically();
    }

    private void setupActionColumn() {
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button downloadButton = new Button("Download");

            {
                // Set button styles
                editButton.setStyle("-fx-background-color: #77dd77; -fx-text-fill: white;");
                downloadButton.setStyle("-fx-background-color: #ff69b4; -fx-text-fill: white;");

                // Add download logic here
                downloadButton.setOnAction((event) -> {
                    Offer offer = getTableView().getItems().get(getIndex());
                    if (offer.getFile_name() == null) {
                        // Show alert error
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("No attached file to download for this offer");
                        alert.showAndWait();
                    } else {
                        try {
                            downloadMatchingFile(offer.getFile_name(), getMatchingDownloadUrl(offer.getFile_name()));
                        } catch (IOException e) {
                            e.printStackTrace();
                            // Show error message to user
                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("Offer is Downloaded Successfully");
                        alert.showAndWait();
                    }
                });

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
                    HBox hbox = new HBox(5); // 5 is the space between buttons
                    hbox.getChildren().add(editButton);
                    hbox.getChildren().add(downloadButton);
                    setGraphic(hbox);
                }
            }
        });
    }

    // Method to download the file
    private static void downloadMatchingFile(String fileName, String downloadUrl) throws IOException {
        // Check if downloaded file already exists (optional)
        File downloadedFile = new File(System.getProperty("user.home") + "/Downloads/SkillSeekrDD/" + fileName);
        if (downloadedFile.exists()) {
            System.out.println("File " + fileName + " already exists in Downloads folder.");
            return;
        }

        // Download the file
        URL url = new URL(downloadUrl);
        IOUtils.copy(url.openStream(), new FileOutputStream(downloadedFile));
        System.out.println("File " + fileName + " downloaded successfully.");
    }

    // Method to retrieve download URL based on filename (replace with your logic)
    private static String getMatchingDownloadUrl(String fileName) {
        String filePath = new File("src/main/resources/Public/" + fileName).toURI().toString();
        return filePath;
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


    // Method to load a new stage
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


    // Method to refresh the table
    private void refreshTable() {
        offerTableView.getItems().clear();
        try {
            serviceOffer = new ServiceOffer(); // Initialize the service
            List<Offer> offers = serviceOffer.show(); // Fetch data from the service
            offerTableView.getItems().addAll(offers); // Populate the TableView
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (e.g., show error message)
        }
    }

    // Method to refresh the table every 3 seconds
    private void refreshTablePeriodically() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // Sleep for 5 seconds
                    Platform.runLater(this::refreshTable); // Refresh the table on the JavaFX thread
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}