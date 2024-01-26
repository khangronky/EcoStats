package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class OurTeam implements Handler {
    public static final String URL = "html/OurTeam.html";

    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/OurTeam.html");
    }
}