package Services.Hire;
import Models.Hire.Recrutement;
import Services.IServices;
import Utils.MyJDBC;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class ServiceRecrutement implements IServices<Recrutement> {
    public Connection connection;
    public Statement statement;


    public ServiceRecrutement() {
        connection = MyJDBC.getInstance().getConnection();
    }

    @Override
    public void add(Recrutement recrutement) throws SQLException {
        String query = "INSERT INTO recrutement (titre, description, date, statut) VALUES (?, ?, ?, ?)";
        PreparedStatement insertRecrutementStatement = null;
        insertRecrutementStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        insertRecrutementStatement.setString(1, recrutement.getTitre());
        insertRecrutementStatement.setString(2, recrutement.getDescription());
        insertRecrutementStatement.setDate(3, new Date(recrutement.getDate().getTime()));
        insertRecrutementStatement.setString(4, recrutement.getStatut());
        insertRecrutementStatement.executeUpdate();
    }


    @Override
    public void update(Recrutement recrutement) throws SQLException {
        String query = "UPDATE recrutement SET titre=?, description=?, date=?, statut=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, recrutement.getTitre());
            preparedStatement.setString(2, recrutement.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(recrutement.getDate().getTime()));
            preparedStatement.setString(4, recrutement.getStatut());
            preparedStatement.setInt(5, recrutement.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Recrutement recrutement) throws SQLException {
        String query = "DELETE FROM recrutement WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, recrutement.getId());
            preparedStatement.executeUpdate();
        } finally {
            // Close the PreparedStatement
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    @Override
    public List<Recrutement> show() throws SQLException {
        List<Recrutement> recrutements = new ArrayList<>();
        String query = "SELECT * FROM recrutement";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Recrutement recrutement = new Recrutement();
                recrutement.setId(resultSet.getInt("id"));
                recrutement.setTitre(resultSet.getString("titre"));
                recrutement.setDescription(resultSet.getString("description"));
                recrutement.setDate((resultSet.getDate("date")));
                recrutement.setStatut(resultSet.getString("statut"));

            }
        }
        return recrutements;
    }
}

