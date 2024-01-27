package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Mission implements Handler {
    public static final String URL = "html/Mission.html";

    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/Mission.html");
        String database = "jdbc:sqlite:database/EcoStats.db";

        String personaquery = "SELECT * FROM Persona";
        ArrayList<ArrayList<String>> personatable = AppDB.getTable(database, personaquery);
        AppDB.printTable(personatable);
        
        String studentquery = "SELECT * FROM Student";
        ArrayList<ArrayList<String>> studenttable = AppDB.getTable(database, studentquery);
        AppDB.printTable(studenttable);
    }
}