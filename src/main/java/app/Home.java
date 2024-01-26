package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Home implements Handler {
    public static final String URL = "html/Home.html";

    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/Home.html");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database/EcoStats.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = """
                SELECT *
                FROM
                (SELECT MIN(Year) Year1, AvgTemp AvgTemp1 FROM World WHERE AvgTemp IS NOT NULL)
                JOIN
                (SELECT MAX(Year) Year2, AvgTemp AvgTemp2 FROM World WHERE AvgTemp IS NOT NULL)
                JOIN
                (SELECT COUNT(AvgTemp) NumYear FROM World WHERE AvgTemp IS NOT NULL);
            """;
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String Year1 = results.getString("Year1");
                String AvgTemp1 = results.getString("AvgTemp1");
                String Year2 = results.getString("Year2");
                String AvgTemp2 = results.getString("AvgTemp2");
                String NumYear = results.getString("NumYear");
                System.out.printf("We have found %s annual average temperatures on our planet: from %s°C in %s to %s°C in %s.\n",
                NumYear, AvgTemp1, Year1, AvgTemp2, Year2);    
            }

            query = """
                SELECT *
                FROM
                (SELECT MIN(Year) Year1, LandOceanAvgTemp LandOceanAvgTemp1 FROM World WHERE LandOceanAvgTemp IS NOT NULL)
                JOIN
                (SELECT MAX(Year) Year2, LandOceanAvgTemp LandOceanAvgTemp2 FROM World WHERE LandOceanAvgTemp IS NOT NULL)
                JOIN
                (SELECT COUNT(AvgTemp) NumYear FROM World WHERE LandOceanAvgTemp IS NOT NULL);
            """;
            results = statement.executeQuery(query);
            while (results.next()) {
                String Year1 = results.getString("Year1");
                String LandOceanAvgTemp1 = results.getString("LandOceanAvgTemp1");
                String Year2 = results.getString("Year2");
                String LandOceanAvgTemp2 = results.getString("LandOceanAvgTemp2");
                String NumYear = results.getString("NumYear");
                System.out.printf("We have found %s annual average temperatures on our planet: from %s°C in %s to %s°C in %s.\n",
                NumYear, LandOceanAvgTemp1, Year1, LandOceanAvgTemp2, Year2);    
            }

            query = """
                SELECT *
                FROM
                (SELECT MIN(Year) Year1, printf("%,d", Population) Population1 FROM World WHERE Population IS NOT NULL)
                JOIN
                (SELECT MAX(Year) Year2, printf("%,d", Population) Population2 FROM World WHERE Population IS NOT NULL)
                JOIN
                (SELECT COUNT(Population) NumYear FROM World WHERE Population IS NOT NULL);
            """;
            results = statement.executeQuery(query);
            while (results.next()) {
                String Year1 = results.getString("Year1");
                String Population1 = results.getString("Population1");
                String Year2 = results.getString("Year2");
                String Population2 = results.getString("Population2");
                String NumYear = results.getString("NumYear");
                System.out.printf("We have also collected data of global population within %s years: from %s in %s to %s in %s.\n",
                NumYear, Population1, Year1, Population2, Year2);
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