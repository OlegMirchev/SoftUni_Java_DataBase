package DBAppsIntroduction;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "...");
        props.setProperty("password", "...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);
        PreparedStatement stmt = connection.prepareStatement(
                "SELECT v.name, COUNT(DISTINCT m.id) AS 'minion_count'" +
                " FROM `minions` AS m" +
                " JOIN `minions_villains` AS mv ON mv.minion_id = m.id" +
                " JOIN `villains` AS v ON v.id = mv.villain_id" +
                " GROUP BY v.id" +
                " HAVING `minion_count` > 15" +
                " ORDER BY `minion_count` DESC;");

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("v.name") + " " + resultSet.getString("minion_count"));
        }

        connection.close();
    }
}
