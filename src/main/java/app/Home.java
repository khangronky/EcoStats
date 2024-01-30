package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Home implements Handler {
    public static final String URL = "html/Home.html";

    @Override
    public void handle(Context context) throws Exception {
        if (context.method().equals("GET")) context.render("public/html/Home.html");
        if (context.method().equals("POST")) {

        }
    }
}