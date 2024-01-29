package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Home implements Handler {
    public static final String URL = "html/Home.html";

    @Override
    public void handle(Context context) throws Exception {
        if (context.method().equals("GET")) context.render("public/html/Home.html");
        if (context.method().equals("POST")) {
            String database = "jdbc:sqlite:database/EcoStats.db";
            String query = "";
            String json = "";

            query = """
            SELECT *
            FROM (SELECT MIN(Year), AvgTemp FROM World WHERE AvgTemp IS NOT NULL)
            JOIN (SELECT MAX(Year), AvgTemp FROM World WHERE AvgTemp IS NOT NULL)
            JOIN (SELECT COUNT(AvgTemp) FROM World WHERE AvgTemp IS NOT NULL);
            """;
            json = AppJSON.getJSON(database, query);

            query = """
            SELECT *
            FROM (SELECT MIN(Year), printf('%,d', Population) FROM World WHERE Population IS NOT NULL)
            JOIN (SELECT MAX(Year), printf('%,d', Population) FROM World WHERE Population IS NOT NULL)
            JOIN (SELECT COUNT(Population) FROM World WHERE Population IS NOT NULL);
            """;
            json = AppJSON.getJSON(database, query);
        }
    }
}