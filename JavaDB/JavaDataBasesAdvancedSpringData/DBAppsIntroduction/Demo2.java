package DBAppsIntroduction;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Demo2 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "...");
        props.setProperty("password", "...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);
        PreparedStatement stmt = connection.prepareStatement("SELECT u.user_name, u.first_name, u.last_name, COUNT(u.id) FROM users AS u " +
                " JOIN users_games AS ug ON ug.user_id = u.id" +
                " JOIN games AS g ON g.id = ug.game_id" +
                " WHERE u.user_name = ?" +
                " GROUP BY u.id");

        String userName = scanner.nextLine();
        stmt.setString(1, userName);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            System.out.printf("User: %s%n" +
                    "%s %s has played %d games", resultSet.getString("user_name"), resultSet.getString("first_name"),
                    resultSet.getString("last_name"), resultSet.getInt("COUNT(u.id)"));
        }else {
            System.out.println("No such user exists");
        }

        connection.close();
    }
}
