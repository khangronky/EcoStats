package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Home implements Handler {
    public static final String URL = "html/Home.html";

    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/Home.html");
        String database = "jdbc:sqlite:database/EcoStats.db";
        
        String landoceanavgtempquery = """
            SELECT *
            FROM
            (SELECT MIN(Year), AvgTemp FROM World WHERE AvgTemp IS NOT NULL)
            JOIN
            (SELECT MAX(Year), AvgTemp FROM World WHERE AvgTemp IS NOT NULL)
            JOIN
            (SELECT COUNT(AvgTemp) FROM World WHERE AvgTemp IS NOT NULL);
        """;
        ArrayList<ArrayList<String>> landoceanavgtemptable = AppDB.getTable(database, landoceanavgtempquery);
        String year1 = landoceanavgtemptable.get(1).get(0);
        String landoceanavgtemp1 = landoceanavgtemptable.get(1).get(1);
        String year2 = landoceanavgtemptable.get(1).get(2);
        String landoceanavgtemp2 = landoceanavgtemptable.get(1).get(3);
        String numyear = landoceanavgtemptable.get(1).get(4);
        System.out.println("We have found " + numyear + " annual average temperatures on our planet: from " + landoceanavgtemp1 + "°C in " + year1 + " to " + landoceanavgtemp2 + "°C in " + year2 + ".");

        String populationquery = """
            SELECT *
            FROM
            (SELECT MIN(Year), printf("%,d", Population) FROM World WHERE Population IS NOT NULL)
            JOIN
            (SELECT MAX(Year), printf("%,d", Population) FROM World WHERE Population IS NOT NULL)
            JOIN
            (SELECT COUNT(Population) FROM World WHERE Population IS NOT NULL);
        """;
        ArrayList<ArrayList<String>> populationtable = AppDB.getTable(database, populationquery);
        year1 = populationtable.get(1).get(0);
        String population1 = populationtable.get(1).get(1);
        year2 = populationtable.get(1).get(2);
        String population2 = populationtable.get(1).get(3);
        numyear = populationtable.get(1).get(4);
        System.out.println("We have also collected data of global population within " + numyear + " years: from " + population1 + " in " + year1 + " to " + population2 + " in " + year2 + ".");
    }
}