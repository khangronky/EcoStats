package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Similarity implements Handler{
    public static final String URL = "html/Similarity.html";
    
    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/Similarity.html");
    }
}