package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class AppDB {
    public static ArrayList<ArrayList<String>> getTable(String database, String query) throws Exception {
        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(database);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet results = statement.executeQuery(query);
            ResultSetMetaData rsmd = results.getMetaData();
            int columnCount = rsmd.getColumnCount();

            ArrayList<String> headerrow = new ArrayList<String>();
            for (int i = 1; i <= columnCount; i++) {   
                headerrow.add(rsmd.getColumnName(i));
            }
            table.add(headerrow);

            while (results.next()) {
                ArrayList<String> row = new ArrayList<String>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(results.getString(i));
                }
                table.add(row);
            }

            statement.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return table;
    }

    public static void printTable(ArrayList<ArrayList<String>> table) {
        for (ArrayList<String> row : table) {
            for (String column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
    }
}