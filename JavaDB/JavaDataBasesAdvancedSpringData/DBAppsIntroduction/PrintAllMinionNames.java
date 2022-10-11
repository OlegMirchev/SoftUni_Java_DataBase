package DBAppsIntroduction;

import java.sql.*;
import java.util.Properties;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {

        Properties props = new Properties();
        props.setProperty("user", "...");
        props.setProperty("password", "...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmtSelectMinionsASC = connection.prepareStatement(
                "SELECT m.id, m.name FROM `minions` AS m" +
                " ORDER BY m.id ASC;");

        ResultSet resultSetMinionsASC = stmtSelectMinionsASC.executeQuery();

        PreparedStatement stmtSelectMinionsDESC = connection.prepareStatement(
                "SELECT m.id, m.name FROM `minions` AS m" +
                        " ORDER BY m.id DESC;");

        ResultSet resultSetMinionsDESC = stmtSelectMinionsDESC.executeQuery();

        int id = 0;
        while (resultSetMinionsASC.next() && resultSetMinionsDESC.next()) {
            if (resultSetMinionsASC.getInt("m.id") == (id)) {
                return;
            }

            id = resultSetMinionsDESC.getInt("m.id");

            System.out.println(resultSetMinionsASC.getString("m.name"));
            System.out.println(resultSetMinionsDESC.getString("m.name"));
        }

        connection.close();
    }
}
