package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mission implements Handler {
    public static final String URL = "html/Mission.html";

    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/Mission.html");
        System.out.println("Mission page loaded");
    }
}