package DBAppsIntroduction;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "...");
        props.setProperty("password", "...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmtSelectId = connection.prepareStatement("SELECT m.name, m.age FROM `minions` AS m " +
                " WHERE m.id = ? OR m.id = ? OR m.id = ?;");

        int[] inputId = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        stmtSelectId.setInt(1, inputId[0]);
        stmtSelectId.setInt(2, inputId[1]);
        stmtSelectId.setInt(3, inputId[2]);

        ResultSet resultSetMinionsId = stmtSelectId.executeQuery();

        if (!resultSetMinionsId.next()) {
            System.out.println("Not existed id");
        }

        PreparedStatement stmtUpdateMinionsNameAndAge = connection.prepareStatement(
                "UPDATE `minions` AS m" +
                " SET m.name = lower(m.name), m.age = m.age + 1" +
                " WHERE m.id = ? OR m.id = ? OR m.id = ?;");

        stmtUpdateMinionsNameAndAge.setInt(1, inputId[0]);
        stmtUpdateMinionsNameAndAge.setInt(2, inputId[1]);
        stmtUpdateMinionsNameAndAge.setInt(3, inputId[2]);

        stmtUpdateMinionsNameAndAge.executeUpdate();

        PreparedStatement stmtSelectAllMinions = connection.prepareStatement("SELECT m.name, m.age FROM `minions` AS m ORDER BY m.id ASC;");

        ResultSet resultSetAllMinions = stmtSelectAllMinions.executeQuery();

        while (resultSetAllMinions.next()) {
            System.out.println(resultSetAllMinions.getString("m.name") + " " + resultSetAllMinions.getInt("m.age"));
        }

        connection.close();
    }
}
