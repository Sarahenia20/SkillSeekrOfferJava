package com.skillseekr.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import com.skillseekr.Models.User.User;
import com.skillseekr.Services.User.ServiceUser;
import javafx.scene.control.CheckBox;


public class addUser {
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailId;

    @FXML
    private CheckBox isVerifiedId;

    @FXML
    private TextField passwordId;

    @FXML
    private ComboBox<String> roleId;

    @FXML
    private TextField usernameId;

    @FXML
    void addUser(ActionEvent event) {
        ServiceUser serviceUser = new ServiceUser();
        User user = new User();
        String username = usernameId.getText().trim();
        String email = emailId.getText().trim();
        String password = passwordId.getText();

        if (username.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter your username.");
            return;
        }

        user.setUsername(username);

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter your email address.");
            return;
        } else if (!email.contains("@")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Email address must contain '@'.");
            return;
        }

        user.setEmail(email);

        if (password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter your password.");
            return;
        }

        user.setPassword(password);

        // Set isVerified based on CheckBox state
        boolean isVerified = isVerifiedId.isSelected(); // Changed from isverifiedId
        user.setIs_verified(isVerified);

        String selectedRole = roleId.getValue();
        if (selectedRole == null || selectedRole.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a role.");
            return; // Stop processing if role is not selected
        }

        // Correspondance des rôles sélectionnés avec les valeurs attendues
        String roleValue;
        switch (selectedRole) {
            case "Administrateur":
                roleValue = "[\"ROLE_ADMIN\"]";
                break;
            case "Recruteur":
                roleValue = "[\"ROLE_RECRUTEUR\"]";
                break;
            case "Freelancer":
                roleValue = "[\"ROLE_FREELANCER\"]" ;
                break;
            default:
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid role selected.");
                return; // Stop processing if an invalid role is selected
        }

        user.setRoles(roleValue);

        try {
            serviceUser.add(user);
            showAlert(Alert.AlertType.CONFIRMATION, "Success", "User added");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

}
