package DBAppsIntroduction;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "...");
        props.setProperty("password", "...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);
        PreparedStatement stmtSelectVillain = connection.prepareStatement("SELECT v.name FROM `villains` AS v WHERE v.id = ?;");

        int id = Integer.parseInt(scanner.nextLine());
        stmtSelectVillain.setInt(1, id);
        ResultSet resultSetSelectVillain = stmtSelectVillain.executeQuery();

        if (!resultSetSelectVillain.next()) {
            System.out.println("No such villain was found");
            return;
        }

        connection.setAutoCommit(false);

        PreparedStatement stmtSelectCountMinions = connection.prepareStatement(
                "SELECT COUNT(*) AS countMinions FROM `minions` AS m" +
                " JOIN `minions_villains` AS mv ON mv.minion_id = m.id" +
                " JOIN `villains` AS v ON v.id = mv.villain_id" +
                " WHERE v.id = ?;");

        stmtSelectCountMinions.setInt(1, id);
        ResultSet resultSetCountMinions = stmtSelectCountMinions.executeQuery();
        resultSetCountMinions.next();
        int countMinions = resultSetCountMinions.getInt("countMinions");

        try {
            PreparedStatement stmtDeleteVillainAndMinions = connection.prepareStatement(
                        "DELETE FROM `minions_villains` AS mv WHERE mv.villain_id = ?;");

            stmtDeleteVillainAndMinions.setInt(1, id);
            stmtDeleteVillainAndMinions.executeUpdate();

            PreparedStatement stmtDeleteVillain = connection.prepareStatement(
                    "DELETE FROM `villains` AS v WHERE v.id = ?;");

            stmtDeleteVillain.setInt(1, id);
            stmtDeleteVillain.executeUpdate();

            connection.commit();

            System.out.printf("%s was deleted%n" +
                    "%d minions released", resultSetSelectVillain.getString("v.name"), countMinions);

        } catch (SQLException e) {
            connection.rollback();
        }

        connection.close();
    }
}
