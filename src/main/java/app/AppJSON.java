package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class AppJSON {
    public static String getJSON (String database, String query) throws Exception {
        String json = "[";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(database);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            boolean first = true;
            while (resultSet.next()) {
                if (first == true) first = false; else json += ",";
                json += "{";
                for (int i = 1; i <= columnCount; i++) {
                    json += "\"" + resultSetMetaData.getColumnName(i) + "\":";
                    json += "\"" + resultSet.getString(i) + "\"";
                    if (i < columnCount) {
                        json += ",";
                    }
                }
                json += "}";   
            }
            json += "]";
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return json;
    }
}