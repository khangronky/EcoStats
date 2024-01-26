package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class App {
    public static final int         JAVALIN_PORT    = 7001;
    public static final String      DIR         = "/public";
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
            config.addStaticFiles(DIR);
        }).start(JAVALIN_PORT);
        
        app.get(Home.URL, new Home());
        app.get(Mission.URL, new Mission());
        app.get(OurTeam.URL, new OurTeam());
        app.get(Data.URL, new Data());
        app.get(Change.URL, new Change());
        app.get(AvgTemp.URL, new AvgTemp());
        app.get(Similarity.URL, new Similarity());
        app.get(FAQ.URL, new FAQ());
    } 
}