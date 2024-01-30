package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Test implements Handler{
    public static final String URL = "html/Test.html";
    
    @Override
    public void handle(Context context) throws Exception {
        if (context.method().equals("GET")) context.render("public/html/Test.html");
        if (context.method().equals("POST")) {
            String requestBody = context.body();
            System.out.println("Received JSON on the server: " + requestBody);
            String json = """
            [
                1990,
                2010,
                "Hello",
                "ABC"
            ]
            """;
            context.result(json);
        }
    }
}