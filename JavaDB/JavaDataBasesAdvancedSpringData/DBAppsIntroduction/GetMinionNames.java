package DBAppsIntroduction;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "...");
        props.setProperty("password", "...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);
        PreparedStatement stmtVillains = connection.prepareStatement(
                "SELECT v.name FROM `villains` AS v" +
                " WHERE v.id = ?;");
        int id = Integer.parseInt(scanner.nextLine());
        stmtVillains.setInt(1, id);
        ResultSet resultSetV = stmtVillains.executeQuery();

        if (resultSetV.next()) {
            System.out.printf("Villain: %s%n", resultSetV.getString("v.name"));

            printMinions(connection, id);
        }else {
            System.out.printf("No villain with ID %d exists in the database.", id);
        }

        connection.close();
    }

    private static void printMinions(Connection connection, int id) throws SQLException {
        PreparedStatement stmtMinions = connection.prepareStatement(
                "SELECT m.name, m.age" +
                " FROM `minions` AS m" +
                " JOIN `minions_villains` AS mv ON mv.minion_id = m.id" +
                " JOIN `villains` AS v ON v.id = mv.villain_id" +
                " WHERE v.id = ?" +
                " GROUP BY m.id;");

        stmtMinions.setInt(1, id);
        ResultSet resultSetM = stmtMinions.executeQuery();

        int index = 1;

        while (resultSetM.next()) {
            System.out.println(index + ". " + resultSetM.getString("m.name") + " " + resultSetM.getInt("m.age"));

            index++;
        }
    }
}
