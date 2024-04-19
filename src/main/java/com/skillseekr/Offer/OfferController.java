package com.skillseekr.Offer;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.Date;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import Models.Offers.Offer;
import Services.Offers.ServiceOffer;
import Models.Offers.Skill;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ContextMenu;



public class OfferController implements Initializable {

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
    private TableColumn<Offer, Date> created_atColumn;

    @FXML
    private TableColumn<Offer, String> motiveColumn;

    @FXML
    private TableColumn<Offer, String> typeColumn;

    @FXML
    private TableColumn<Offer, String> locationColumn;

    @FXML
    private TableColumn<Offer, String> statusColumn;

    @FXML
    private TableColumn<Offer, String> file_nameColumn;

    @FXML
    private TableColumn<Offer, String> skillsColumn;

    @FXML
    public ContextMenu contextMenu;
    @FXML
    private ServiceOffer serviceOffer;
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceOffer = new ServiceOffer();

        // Set up table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        created_atColumn.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        motiveColumn.setCellValueFactory(new PropertyValueFactory<>("motive"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        file_nameColumn.setCellValueFactory(new PropertyValueFactory<>("file_name"));
        skillsColumn.setCellValueFactory(cellData -> {
            List<Skill> skills = cellData.getValue().getSkills();
            StringBuilder skillsString = new StringBuilder();
            for (Skill skill : skills) {
                skillsString.append(skill.getSkill()).append(", ");
            }
            // Remove the last comma and space
            if (skillsString.length() > 0) {
                skillsString.setLength(skillsString.length() - 2);
            }
            return new SimpleStringProperty(skillsString.toString());
        });
        // Populate TableView with data
        try {
            List<Offer> offers = serviceOffer.show();
            offerTableView.getItems().addAll(offers);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
        }
    }

}
