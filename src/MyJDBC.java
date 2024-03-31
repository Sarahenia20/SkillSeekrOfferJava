import java.sql.*;

public class MyJDBC {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skillseekr", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM offer");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("title"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}