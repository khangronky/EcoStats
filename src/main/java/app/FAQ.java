package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class FAQ implements Handler {
    public static final String URL = "html/FAQ.html";

    @Override
    public void handle(Context context) throws Exception {
        if (context.method().equals("GET")) context.render("public/html/FAQ.html");
        if (context.method().equals("POST")) {}
    }
}