package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Mission implements Handler {
    public static final String URL = "html/Mission.html";

    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/Mission.html");

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:sqlite:database/EcoStats.db");
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);
        
                    String query = """
                SELECT *
                FROM
                Student;
            """;
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String SID = results.getString("SID");
                String Name = results.getString("Name");
                String Email = results.getString("Email");
                System.out.printf("Student ID: %s, Name: %s, Email: %s\n"
                ,SID, Name, Email);    
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