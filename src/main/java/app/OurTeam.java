package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OurTeam implements Handler {
    public static final String URL = "html/OurTeam.html";

    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/OurTeam.html");
    }
}