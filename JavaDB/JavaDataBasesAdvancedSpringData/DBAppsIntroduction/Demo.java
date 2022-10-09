package DBAppsIntroduction;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "...");
        props.setProperty("password", "...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees AS e WHERE e.salary > ?");

        String salary = scanner.nextLine();
        stmt.setDouble(1, Double.parseDouble(salary));
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
        }

        connection.close();
    }
}
