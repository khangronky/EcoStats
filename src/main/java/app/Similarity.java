package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Similarity implements Handler{
    public static final String URL = "html/Similarity.html";
    
    @Override
    public void handle(Context context) throws Exception {
        if (context.method().equals("GET")) context.render("public/html/Similarity.html");
        if (context.method().equals("POST")) {
            int startingyear = 0;
            int period = 0;
            String viewby = "Cities";
            String countryname = "Thailand";
            String cityname = "";
            String statename = "";
            String simcategory = "Temperature";
            int numresults = 0;
            
            String database = "jdbc:sqlite:database/EcoStats.db";
            String query = "";
            String json = "";

            if (startingyear == 0 && period == 0 && viewby.equals("Countries") && countryname.equals("") && cityname.equals("") && statename.equals("") &&
            simcategory.equals("Temperature") && numresults == 0) {
                query = "SELECT CountryName FROM Country;";
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }

            if (startingyear != 0 && period != 0 && viewby.equals("Countries") && !countryname.equals("") && cityname.equals("") && statename.equals("") &&
            simcategory.equals("Temperature") && numresults != 0) {
                query = String.format("""
                SELECT t1.CountryName 'Country name',
                t2.AvgTemp || ' (' || t2.Year || ')' 'Average temperature (Starting year)',
                t3.AvgTemp || ' (' || t3.Year || ')' 'Average temperature (Ending year)'
                FROM Country t1
                LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN %d AND %d) t2 ON t1.CountryID = t2.CountryID
                LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN %d AND %d) t3 ON t1.CountryID = t3.CountryID AND t3.Year = t2.Year + %d
                JOIN (SELECT * FROM Country WHERE CountryName = '%s') t4
                LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = %d) t5 ON t4.CountryID = t5.CountryID
                LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = %d) t6 ON t4.CountryID = t6.CountryID
                WHERE ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3) IS NOT NULL
                ORDER BY ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3)
                LIMIT %d;            
                """,
                1750, 2013 - period,
                1750 + period, 2013, period,
                countryname,
                startingyear,
                startingyear + period,
                numresults);
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }

            if (startingyear == 0 && period == 0 && viewby.equals("Countries") && countryname.equals("") && cityname.equals("") && statename.equals("") &&
            simcategory.equals("Population") && numresults == 0) {
                query = "SELECT CountryName FROM Country;";
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }

            if(startingyear != 0 && period != 0 && viewby.equals("Countries") && !countryname.equals("") && cityname.equals("") && statename.equals("") &&
            simcategory.equals("Population") && numresults != 0) {
                query = String.format("""
                SELECT t1.CountryName 'Country name',
                printf('%%,d', t2.Population) || ' (' || t2.Year || ')' 'Population (Starting year)',
                printf('%%,d', t3.Population) || ' (' || t3.Year || ')' 'Population (Ending year)'
                FROM Country t1
                LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN %d AND %d) t2 ON t1.CountryID = t2.CountryID
                LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN %d AND %d) t3 ON t1.CountryID = t3.CountryID AND t3.Year = t2.Year + %d
                JOIN (SELECT * FROM Country WHERE CountryName = '%s') t4
                LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = %d) t5 ON t4.CountryID = t5.CountryID
                LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = %d) t6 ON t4.CountryID = t6.CountryID
                WHERE ROUND(SQRT(POWER(t2.Population - t5.Population, 2) + POWER(t3.Population - t6.Population, 2)), 3) IS NOT NULL
                ORDER BY ROUND(SQRT(POWER(t2.Population - t5.Population, 2) + POWER(t3.Population - t6.Population, 2)), 3)
                LIMIT %d;
                """,
                1750, 2013 - period,
                1750 + period, 2013, period,
                countryname,
                startingyear,
                startingyear + period,
                numresults);
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }

            if (startingyear == 0 && period == 0 && viewby.equals("Countries") && countryname.equals("") && cityname.equals("") && statename.equals("") &&
            simcategory.equals("Both") && numresults == 0) {
                query = "SELECT CountryName FROM Country;";
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }

            if(startingyear != 0 && period != 0 && viewby.equals("Countries") && !countryname.equals("") && cityname.equals("") && statename.equals("") &&
            simcategory.equals("Both") && numresults != 0) {
                query = String.format("""
                SELECT t1.CountryName 'Country name',
                t2.AvgTemp || ' (' || t2.Year || ')' 'Average temperature (Starting year)',
                t3.AvgTemp || ' (' || t3.Year || ')' 'Average temperature (Ending year)',
                printf('%%,d', t4.Population) || ' (' || t4.Year || ')' 'Population (Starting year)',
                printf('%%,d', t5.Population) || ' (' || t5.Year || ')' 'Population (Ending year)'
                FROM Country t1
                LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN %d AND %d) t2 ON t1.CountryID = t2.CountryID
                LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN %d AND %d) t3 ON t1.CountryID = t3.CountryID AND t3.Year = t2.Year + %d
                LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN %d AND %d) t4 ON t1.CountryID = t4.CountryID AND t4.Year = t2.Year
                LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN %d AND %d) t5 ON t1.CountryID = t5.CountryID AND t5.Year = t4.Year + %d
                JOIN (SELECT * FROM Country WHERE CountryName = '%s') t6
                LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = %d) t7 ON t6.CountryID = t7.CountryID
                LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = %d) t8 ON t6.CountryID = t8.CountryID
                LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = %d) t9 ON t7.CountryID = t9.CountryID
                LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = %d) t10 ON t6.CountryID = t10.CountryID
                WHERE ROUND(SQRT(POWER(t2.AvgTemp - t7.AvgTemp, 2) + POWER(t3.AvgTemp - t8.AvgTemp, 2) + POWER(t4.Population - t9.Population, 2) + POWER(t5.Population - t10.Population, 2)), 3) IS NOT NULL
                ORDER BY ROUND(SQRT(POWER(t2.AvgTemp - t7.AvgTemp, 2) + POWER(t3.AvgTemp - t8.AvgTemp, 2) + POWER(t4.Population - t9.Population, 2) + POWER(t5.Population - t10.Population, 2)), 3) 
                LIMIT %d;
                """,
                1750, 2013 - period,
                1750 + period, 2013, period,
                1960, 2013 - period,
                1960 + period, 2013, period,
                countryname,
                startingyear,
                startingyear + period,
                startingyear,
                startingyear + period,
                numresults);
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }

            if(startingyear == 0 && period == 0 && viewby.equals("Cities") && countryname.equals("") && cityname.equals("") && statename.equals("") &&
            simcategory.equals("Temperature") && numresults == 0) {
                query = "SELECT DISTINCT CountryName FROM Country JOIN City ON Country.CountryID = City.CountryID;";
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }

            if(startingyear == 0 && period == 0 && viewby.equals("Cities") && !countryname.equals("") && cityname.equals("") && statename.equals("") &&
            simcategory.equals("Temperature") && numresults == 0) {
                query = String.format("SELECT CityName FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = '%s';", countryname);
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }

            if(startingyear != 0 && period != 0 && viewby.equals("Cities") && !countryname.equals("") && !cityname.equals("") && statename.equals("") &&
            simcategory.equals("Temperature") && numresults != 0) {
                query = String.format("""
                SELECT t1.CityName 'City name (%s)',
                t2.AvgTemp || ' (' || t2.Year || ')' 'Average temperature (Starting year)',
                t3.AvgTemp || ' (' || t3.Year || ')' 'Average temperature (Ending year)'
                FROM (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT * FROM CityTemp WHERE Year BETWEEN %d AND %d) t2 ON t1.CityID = t2.CityID
                LEFT JOIN (SELECT * FROM CityTemp WHERE Year BETWEEN %d AND %d) t3 ON t1.CityID = t3.CityID AND t3.Year = t2.Year + %d
                JOIN (SELECT Country.*, City.* FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = '%s' AND CityName = '%s') t4
                LEFT JOIN (SELECT * FROM CityTemp WHERE Year = %d) t5 ON t4.CityID = t5.CityID
                LEFT JOIN (SELECT * FROM CityTemp WHERE Year = %d) t6 ON t4.CityID = t6.CityID
                WHERE ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3) IS NOT NULL
                ORDER BY ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3)
                LIMIT %d;           
                """,
                countryname,
                countryname,
                1750, 2013 - period,
                1750 + period, 2013, period,
                countryname, cityname,
                startingyear,
                startingyear + period,
                numresults);
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }
            
            if(startingyear == 0 && period == 0 && viewby.equals("States") && countryname.equals("") && cityname.equals("") && statename.equals("") &&
            simcategory.equals("Temperature") && numresults == 0) {
                query = "SELECT DISTINCT CountryName FROM Country JOIN State ON Country.CountryID = State.CountryID;";
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }

            if(startingyear == 0 && period == 0 && viewby.equals("States") && !countryname.equals("") && cityname.equals("") && statename.equals("") &&
            simcategory.equals("Temperature") && numresults == 0) {
                query = String.format("SELECT StateName FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = '%s';", countryname);
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }

            if(startingyear != 0 && period != 0 && viewby.equals("States") && !countryname.equals("") && cityname.equals("") && !statename.equals("") &&
            simcategory.equals("Temperature") && numresults != 0) {
                query = String.format("""
                SELECT t1.StateName 'State name (%s)',
                t2.AvgTemp || ' (' || t2.Year || ')' 'Average temperature (Starting year)',
                t3.AvgTemp || ' (' || t3.Year || ')' 'Average temperature (Ending year)'
                FROM (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT * FROM StateTemp WHERE Year BETWEEN %d AND %d) t2 ON t1.StateID = t2.StateID
                LEFT JOIN (SELECT * FROM StateTemp WHERE Year BETWEEN %d AND %d) t3 ON t1.StateID = t3.StateID AND t3.Year = t2.Year + %d
                JOIN (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = '%s' AND StateName = '%s') t4
                LEFT JOIN (SELECT * FROM StateTemp WHERE Year = %d) t5 ON t4.StateID = t5.StateID
                LEFT JOIN (SELECT * FROM StateTemp WHERE Year = %d) t6 ON t4.StateID = t6.StateID
                WHERE ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3) IS NOT NULL
                ORDER BY ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3)
                LIMIT %d;
                """, 
                countryname,
                countryname,
                1750, 2013 - period,
                1750 + period, 2013, period,
                countryname, statename, 
                startingyear,
                startingyear + period,
                numresults);
                System.out.println(query);
                json = AppJSON.getJSON(database, query);
            }
        }
    }
}