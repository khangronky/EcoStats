package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class FAQ implements Handler {
    public static final String URL = "html/FAQ.html";

    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/FAQ.html");
    }
}