package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Data implements Handler {
    public static final String URL = "html/Data.html";

    @Override
    public void handle(Context context) throws Exception {
        if (context.method().equals("GET")) context.render("public/html/Data.html");
        if (context.method().equals("POST")) {}
    }
}