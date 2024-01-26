package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("help/routes"));
            config.addStaticFiles("public");
        }).start(7001);
        System.out.println("Listening on http://localhost:7001/html/Home.html");    
        app.get(AvgTemp.URL, new AvgTemp());
        app.get(Change.URL, new Change());
        app.get(Data.URL, new Data());
        app.get(FAQ.URL, new FAQ());
        app.get(Home.URL, new Home());
        app.get(Mission.URL, new Mission());
        app.get(Similarity.URL, new Similarity());
    } 
}