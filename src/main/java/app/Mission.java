package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Mission implements Handler {
    public static final String URL = "html/Mission.html";

    @Override
    public void handle(Context context) throws Exception {
        if (context.method().equals("GET")) context.render("public/html/Mission.html");
        if (context.method().equals("POST")) {
            String database = "jdbc:sqlite:database/EcoStats.db";
            String query = "";
            String json = "";

            query = "SELECT * FROM Persona";
            json = AppJSON.getJSON(database, query);
            
            query = "SELECT * FROM Student";
            json = AppJSON.getJSON(database, query);
        }
    }
}