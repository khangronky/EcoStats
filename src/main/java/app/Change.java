package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Change implements Handler{
    public static final String URL = "html/Change.html";
    
    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/Change.html");
    }
}