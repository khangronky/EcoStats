package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("help/routes"));
            config.addStaticFiles("public");
        }).start(7000);

        System.out.println("Our homepage is now located in: http://localhost:7000/html/Home.html"); 

        app.get(AvgTemp.URL, new AvgTemp());
        app.post(AvgTemp.URL, new AvgTemp());

        app.get(Change.URL, new Change());
        app.post(Change.URL, new Change());

        app.get(Data.URL, new Data());
        app.post(Data.URL, new Data());

        app.get(FAQ.URL, new FAQ());
        app.post(FAQ.URL, new FAQ());

        app.get(Home.URL, new Home());
        app.post(Home.URL, new Home());

        app.get(Mission.URL, new Mission());
        app.post(Mission.URL, new Mission());

        app.get(Similarity.URL, new Similarity());
        app.post(Similarity.URL, new Similarity());

        app.get(Test.URL, new Test());
        app.post(Test.URL, new Test());
    } 
}