package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppDB {
    public static final String DATABASE = "jdbc:sqlite:database/EcoStats.db";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = """
                        SELECT *, t2.c3 - t1.c1 + 1 c5
                        FROM
                        (SELECT MIN(Year) c1, AvgTemp c2 FROM World WHERE AvgTemp IS NOT NULL) t1
                        JOIN
                        (SELECT MAX(Year) c3, AvgTemp c4 FROM World WHERE AvgTemp IS NOT NULL) t2;
                    """;
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String c1 = results.getString("c1");
                String c2 = results.getString("c2");
                String c3 = results.getString("c3");
                String c4 = results.getString("c4");
                String c5 = results.getString("c5");
                System.out.println(c1 + " " + c2 + " " + c3 + " " + c4 + " " + c5);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}