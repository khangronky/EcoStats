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
            System.out.println(requestBody);
            String response = """
            abc,def,ghi
            123,456,789  
            """;
            context.result(response);
        }
    }
}