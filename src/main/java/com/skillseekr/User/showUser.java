package com.skillseekr.User;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
import com.skillseekr.Models.User.User;
import com.skillseekr.Services.User.ServiceUser;
import javafx.scene.control.Button;


public class showUser {
    @FXML
    private TableColumn<User, String> emailCol;

    @FXML
    private TableColumn<User, String> roleCol;

    @FXML
    private TableColumn<User, String> usernameCol;

    @FXML
    private TableColumn<User, Integer> verificationCol;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, Void> actionCol;

    @FXML
    private void getAddView(MouseEvent event) {
        try {
            // Ouvrir la fenêtre d'ajout d'utilisateur
            Parent parent = FXMLLoader.load(getClass().getResource("/addUser.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait(); // Attendre que la fenêtre d'ajout soit fermée

            // Après la fermeture de la fenêtre d'ajout, actualiser la TableView
            refreshTableView();
        } catch (IOException ex) {
            Logger.getLogger(showUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void initialize() {
        ServiceUser serviceUser = new ServiceUser();
        try {
            List<User> users = serviceUser.show();
            ObservableList<User> observableList = FXCollections.observableList(users);
            tableView.setItems(observableList);

            // Set cell value factories for other columns
            usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            roleCol.setCellValueFactory(cellData -> {
                String role = cellData.getValue().getRoles();
                switch (role) {
                    case "[\"ROLE_ADMIN\"]":
                        return new SimpleStringProperty("Administrateur");
                    case "[\"ROLE_RECRUTEUR\"]":
                        return new SimpleStringProperty("Recruteur");
                    case "[\"ROLE_FREELANCER\"]":
                        return new SimpleStringProperty("Freelancer");
                    default:
                        return new SimpleStringProperty(role);
                }
            });
            verificationCol.setCellValueFactory(cellData ->
                    new SimpleIntegerProperty(cellData.getValue().getIs_verified() ? 1 : 0).asObject());

            // Add edit and delete buttons to action column
            actionCol.setCellFactory(new Callback<>() {
                @Override
                public TableCell<User, Void> call(TableColumn<User, Void> param) {
                    return new TableCell<>() {
                        private final HBox manageBtn = new HBox();
                        private final Button editButton = new Button("Edit");
                        private final Button deleteButton = new Button("Delete");
                        {
                            //  CSS pour les icônes
                            deleteButton.getStyleClass().add("Delete");
                            editButton.getStyleClass().add("Edit");

                            deleteButton.setOnMouseClicked(event -> {
                                User user = getTableView().getItems().get(getIndex());
                                ServiceUser userService = new ServiceUser();
                                try {
                                    userService.delete(user);
                                    refreshTableView();
                                } catch (SQLException ex) {
                                    Logger.getLogger(showUser.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });


                            editButton.setOnMouseClicked(event -> {
                                User user = getTableView().getItems().get(getIndex());
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editUser.fxml"));
                                try {
                                    Parent root = loader.load();
                                    editUser editUserController = loader.getController();
                                    editUserController.initData(user); // Pass the user data to the edit controller
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(root));
                                    stage.initStyle(StageStyle.UTILITY);
                                    stage.showAndWait();
                                    refreshTableView(); // Refresh the table after editing
                                } catch (IOException ex) {
                                    Logger.getLogger(showUser.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });




                            manageBtn.getChildren().addAll(editButton, deleteButton);
                            manageBtn.setStyle("-fx-alignment: center;");
                            HBox.setMargin(deleteButton, new Insets(2, 2, 0, 10));
                            HBox.setMargin(editButton, new Insets(2, 3, 0, 12));
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (!empty) {
                                setGraphic(manageBtn);
                            } else {
                                setGraphic(null);
                            }
                        }
                    };
                }
            });
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'initialisation de la table : " + e.getMessage());
        }
    }

    private void refreshTableView() {
        tableView.getItems().clear();
        ServiceUser serviceUser = new ServiceUser();
        try {
            List<User> users = serviceUser.show();
            ObservableList<User> observableList = FXCollections.observableList(users);
            tableView.setItems(observableList);
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'actualisation de la table : " + e.getMessage());
        }
    }
}
