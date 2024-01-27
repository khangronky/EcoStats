package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AvgTemp implements Handler{
    public static final String URL = "html/AvgTemp.html";

    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/AvgTemp.html");

        String database = "jdbc:sqlite:database/EcoStats.db";
    }
}
