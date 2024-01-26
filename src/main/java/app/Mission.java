package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Mission implements Handler {
    public static final String URL = "html/Mission.html";

    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/Mission.html");
    }
}