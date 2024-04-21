package com.skillseekr.User;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.skillseekr.Models.User.User;
import com.skillseekr.Services.User.ServiceUser;


public class signUp {

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Button signupButton;

    @FXML
    private CheckBox termsCheckBox;

    @FXML
    private TextField usernameField;

    private ServiceUser userService; // Assuming you have a ServiceUser class for user operations

    @FXML
    public void initialize() {
        userService = new ServiceUser(); // Initialize the ServiceUser instance
    }

    @FXML
    public void validateInfo() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String selectedRole = roleComboBox.getValue();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || selectedRole == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields.");
            return;
        }

        if (!email.contains("@")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Email address must contain '@'.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
            return;
        }

        if (!termsCheckBox.isSelected()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please accept the terms and conditions.");
            return;
        }

        // Check if user with the provided email already exists
        try {
            if (userService.userExists(email)) {
                showAlert(Alert.AlertType.ERROR, "Error", "User with email " + email + " already exists.");
                return;
            }

            // Create a new User object and set its properties
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setRoles(getRoleValue(selectedRole)); // Get the role value based on the selected role

            userService.add(user); // Add the user using the ServiceUser instance

            showAlert(Alert.AlertType.CONFIRMATION, "Success", "User registered successfully.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to register user: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String getRoleValue(String selectedRole) {
        switch (selectedRole) {
            case "Administrateur":
                return "[\"ROLE_ADMIN\"]";
            case "Recruteur":
                return "[\"ROLE_RECRUTEUR\"]";
            case "Freelancer":
                return "[\"ROLE_FREELANCER\"]";
            default:
                return ""; // Handle other cases accordingly
        }
    }
}