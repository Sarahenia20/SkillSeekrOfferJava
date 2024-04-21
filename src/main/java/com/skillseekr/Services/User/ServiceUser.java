package com.skillseekr.Services.User;

import com.skillseekr.Models.User.User;
import com.skillseekr.Services.IServices;
import com.skillseekr.Utils.MyJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser implements IServices<User> {
    public Connection connection;
    public Statement ste;

    public ServiceUser() {
        connection = MyJDBC.getInstance().getConnection();
    }

    public boolean authenticateUser(String email, String userPassword) throws SQLException {
        try {
            String query = "SELECT password FROM user WHERE email=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String passwordFromDB = resultSet.getString("password");
                    // Here you can directly compare the password from DB with the provided password
                    return userPassword.equals(passwordFromDB);
                }
                return false; // No user with the provided email found
            }
        } catch (SQLException e) {
            // Log the exception for debugging purposes
            System.err.println("Error authenticating user: " + e.getMessage());
            throw e; // Re-throw the exception to be handled in the controller
        }
    }

    @Override
    public void add(User user) throws SQLException {
        String email = user.getEmail();

        // Check if the user with the provided email already exists
        if (userExists(email)) {
            throw new SQLException("Error: User with email " + email + " already exists.");
        }

        // If user doesn't exist, proceed with adding the user
        String req = "INSERT INTO user (email, password, username, roles, is_verified) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setString(1, email);
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getUsername());
            pre.setString(4, user.getRoles());
            pre.setBoolean(5, user.getIs_verified());
            pre.executeUpdate();
        }
    }

    // Helper method to check if a user with the given email already exists
    public boolean userExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM user WHERE email=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0; // User exists if count is greater than 0
            }
            return false; // No user found with the provided email
        }
    }

    @Override
    public void update(User user) throws SQLException {
        String req = "UPDATE user SET email=?, password=?, username=?, roles=?, is_verified=? WHERE id=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setString(1, user.getEmail());
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getUsername());
            pre.setString(4, user.getRoles());
            pre.setBoolean(5, user.getIs_verified());
            pre.setInt(6, user.getId());
            pre.executeUpdate();
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        String req = "DELETE FROM user WHERE id=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, user.getId());
            pre.executeUpdate();
        }
    }

    @Override
    public List<User> show() throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "SELECT * FROM user";
        ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            int id = res.getInt("id");
            String email = res.getString("email");
            String roles = res.getString("roles");
            String password = res.getString("password");
            String username = res.getString("username");
            boolean is_verified = res.getBoolean("is_verified");
            User u = new User(id, email, roles, password, username, is_verified);
            users.add(u);
        }
        return users;
    }
}
