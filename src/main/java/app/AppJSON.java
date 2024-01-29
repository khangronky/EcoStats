package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class AppJSON {
    public static String getJSON (String database, String query) throws Exception {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(database);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            boolean first = true;
            String json = "[";
            while (resultSet.next()) {
                if (first == true) first = false; else json += ",";
                json += "{";
                for (int i = 1; i <= columnCount; i++) {
                    json += "\"" + resultSetMetaData.getColumnName(i) + "\":";
                    String columnType = resultSetMetaData.getColumnTypeName(i);
                    if (columnType.equals("INTEGER")) json += resultSet.getInt(i);
                    if (columnType.equals("REAL")) json += resultSet.getDouble(i);
                    if (columnType.equals("TEXT")) json += "\"" + resultSet.getString(i) + "\"";
                    if (i < columnCount) json += ",";
                }
                json += "}";   
            }
            json += "]";
            return json;
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