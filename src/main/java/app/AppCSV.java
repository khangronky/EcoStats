package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class AppCSV {
    public static String getCSV (String database, String query) throws Exception {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(database);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            String CSV = "";
            for (int i = 1; i <= columnCount; i++) {
                CSV += resultSetMetaData.getColumnName(i);
                if (i < columnCount) CSV += ","; else CSV += "\n";
            }
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    CSV += resultSet.getString(i);
                    if (i < columnCount) CSV += ","; else CSV += "\n";
                } 
            }
            return CSV;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return "";
    }
}