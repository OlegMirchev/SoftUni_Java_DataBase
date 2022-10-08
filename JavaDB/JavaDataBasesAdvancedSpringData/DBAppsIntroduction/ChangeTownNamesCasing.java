package DBAppsIntroduction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "...");
        props.setProperty("password", "...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String country = scanner.nextLine();

        PreparedStatement stmtSelectTown = connection.prepareStatement("SELECT t.name FROM `towns` AS t WHERE t.country LIKE ?;");
        stmtSelectTown.setString(1, country);
        ResultSet resultSetSelectTown = stmtSelectTown.executeQuery();

        if (resultSetSelectTown.next()) {
            PreparedStatement stmtUpdateTown = connection.prepareStatement("UPDATE `towns` AS t" +
                    " SET t.name = upper(t.name)" +
                    " WHERE t.country LIKE ?;");

            stmtUpdateTown.setString(1, country);
            int countUpdateCountry = stmtUpdateTown.executeUpdate();

            System.out.printf("%d town names were affected.%n", countUpdateCountry);

            ResultSet newResultSetSelectTown = stmtSelectTown.executeQuery();

            List<String> listTowns = new ArrayList<>();

            while (newResultSetSelectTown.next()) {
                String nameTown = newResultSetSelectTown.getString("t.name");
                listTowns.add(nameTown);
            }

            System.out.println(listTowns);
        }else {
            System.out.println("No town names were affected.");
        }

        connection.close();
    }
}
