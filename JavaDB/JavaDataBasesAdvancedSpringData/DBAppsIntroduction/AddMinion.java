package DBAppsIntroduction;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class AddMinion {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "...");
        props.setProperty("password", "...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String[] minion = scanner.nextLine().split("\\s+");
        String villain = scanner.nextLine().split("\\s+")[1];

        PreparedStatement stmtSelectTown = connection.prepareStatement(
                "SELECT t.id FROM `towns` AS t" +
                        " WHERE t.name LIKE ?;");
        String town = minion[3];
        stmtSelectTown.setString(1, town);
        ResultSet resultSetSelectTown = stmtSelectTown.executeQuery();

        if (!resultSetSelectTown.next()) {
            PreparedStatement stmtInsertTown = connection.prepareStatement(
                    "INSERT INTO `towns`(`name`)" +
                            " VALUES(?);");

            stmtInsertTown.setString(1, town);
            stmtInsertTown.executeUpdate();

            ResultSet newResultSetSelectTown = stmtSelectTown.executeQuery();
            newResultSetSelectTown.next();

            System.out.printf("Town %s was added to the database.%n", town);
        } else {
            System.out.println(resultSetSelectTown.getInt("t.id"));
        }

        PreparedStatement stmtSelectVillain = connection.prepareStatement("SELECT v.id FROM `villains` AS v" +
                " WHERE v.name LIKE ?;");

        stmtSelectVillain.setString(1, villain);
        ResultSet resultSetSelectVillain = stmtSelectVillain.executeQuery();

        if (!resultSetSelectVillain.next()) {
            PreparedStatement stmtInsertVillain = connection.prepareStatement("INSERT INTO `villains`(`name`, `evilness_factor`)" +
                    " VALUES(?, ?);");

            stmtInsertVillain.setString(1, villain);
            stmtInsertVillain.setString(2, "evil");
            stmtInsertVillain.executeUpdate();

            ResultSet newResultSetSelectVillain = stmtSelectVillain.executeQuery();
            newResultSetSelectVillain.next();

            System.out.printf("Villain %s was added to the database.%n", villain);
        }else {
            System.out.println(resultSetSelectVillain.getInt("v.id"));
        }

        PreparedStatement stmtSelectMinion = connection.prepareStatement("SELECT m.id FROM `minions` AS m" +
                " WHERE m.name LIKE ?;");

        stmtSelectMinion.setString(1, minion[1]);
        ResultSet resultSetSelectMinion = stmtSelectMinion.executeQuery();

        if (!resultSetSelectMinion.next()) {
            PreparedStatement stmtInsertMinion = connection.prepareStatement("INSERT INTO `minions`(`name`, `age`)" +
                    " VALUES(?, ?);");

            stmtInsertMinion.setString(1, minion[1]);
            stmtInsertMinion.setInt(2, Integer.parseInt(minion[2]));
            stmtInsertMinion.executeUpdate();

            ResultSet newResultSetSelectMinion = stmtSelectMinion.executeQuery();
            newResultSetSelectMinion.next();

            System.out.printf("Successfully added %s to be minion of %s.", minion[1], villain);
        }else {
            System.out.println(resultSetSelectMinion.getInt("m.id"));
        }

        connection.close();
    }
}
