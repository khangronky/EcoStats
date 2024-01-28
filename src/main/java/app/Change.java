package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Change implements Handler {
    public static final String URL = "html/Change.html";

    @Override
    public void handle(Context context) throws Exception {
        context.render("public/html/Change.html");

        int startingyear = 0;
        int endingyear = 0;
        String viewby = "Cities";
        String countryname = "";
        String sortcategory = "";
        String sortorder = "";

        String database = "jdbc:sqlite:database/EcoStats.db";
        String query = "";
        String json = "";

        if (startingyear != 0 && endingyear != 0 && viewby.equals("World") && countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
            query = String.format("""
            SELECT t1.AvgTemp 'Average temperature (%d)',
            t2.AvgTemp 'Average temperature (%d)',
            ROUND(100.0 * (t2.AvgTemp - t1.AvgTemp) / ABS(t1.AvgTemp), 3) || '%%' 'Average temperature change',
            t1.LandOceanAvgTemp 'Land-Ocean average temperature (%d)',
            t2.LandOceanAvgTemp 'Land-Ocean average temperature (%d)',
            ROUND(100.0 * (t2.LandOceanAvgTemp - t1.LandOceanAvgTemp) / ABS(t1.LandOceanAvgTemp), 3) || '%%' 'Land-Ocean average temperature change',
            printf('%%,d', t1.Population) 'Population (%d)',
            printf('%%,d', t2.Population) 'Population (%d)',
            ROUND(100.0 * (t2.Population - t1.Population) / ABS(t1.Population), 3) || '%%' 'Population change'
            FROM (SELECT * FROM World WHERE Year = %d) t1
            JOIN (SELECT * FROM World WHERE Year = %d) t2;
            """,
            startingyear,
            endingyear,
            startingyear,
            endingyear,
            startingyear,
            endingyear,
            startingyear,
            endingyear);
            System.out.println(query);
            json = AppJSON.getJSON(database, query);
        }
        
        if (startingyear != 0 && endingyear != 0 && viewby.equals("Countries") && countryname.equals("") && !sortcategory.equals("") && !sortorder.equals("")) {
            if (sortcategory.equals("Temperature")) sortcategory = "ROUND(100.0 * (t3.AvgTemp - t2.AvgTemp) / ABS(t2.AvgTemp), 3)";
            if (sortcategory.equals("Population")) sortcategory = "ROUND(100.0 * (t3.Population - t2.Population) / ABS(t2.Population), 3)";
            if (sortorder.equals("Ascending")) sortorder = "ASC";
            if (sortorder.equals("Descending")) sortorder = "DESC";

            query = String.format("""
            SELECT t1.CountryName 'Country name',
            t2.AvgTemp 'Average temperature (%d)',
            t3.AvgTemp 'Average temperature (%d)',
            ROUND(100.0 * (t3.AvgTemp - t2.AvgTemp) / ABS(t2.AvgTemp), 3) || '%%' 'Average temperature change',
            printf('%%,d', t4.Population) 'Population (%d)',
            printf('%%,d', t5.Population) 'Population (%d)',
            ROUND(100.0 * (t5.Population - t4.Population) / ABS(t4.Population), 3) || '%%' 'Population change'
            FROM Country t1
            LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = %d) t2 ON t1.CountryID = t2.CountryID
            LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = %d) t3 ON t1.CountryID = t3.CountryID
            LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = %d) t4 ON t1.CountryID = t4.CountryID
            LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = %d) t5 ON t1.CountryID = t5.CountryID
            ORDER BY %s %s;
            """,
            startingyear,
            endingyear,
            startingyear,
            endingyear,
            startingyear,
            endingyear,
            startingyear,
            endingyear,
            sortcategory, sortorder);
            System.out.println(query);
            json = AppJSON.getJSON(database, query);
        }

        if (startingyear == 0 && endingyear == 0 && viewby.equals("Cities") && countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
            query = "SELECT DISTINCT CountryName FROM Country JOIN City ON Country.CountryID = City.CountryID;";
            System.out.println(query);
            json = AppJSON.getJSON(database, query);
        }

        if (startingyear != 0 && endingyear != 0 && viewby.equals("Cities") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
            query = String.format("""
            SELECT t1.CityName 'City name (%s)',
            t2.AvgTemp 'Average temperature (%d)',
            t3.AvgTemp 'Average temperature (%d)',
            ROUND(100.0 * (t3.AvgTemp - t2.AvgTemp) / ABS(t2.AvgTemp), 3) || '%%' 'Average temperature change',
            t2.MinTemp 'Minimum temperature (%d)',
            t3.MinTemp 'Minimum temperature (%d)',
            ROUND(100.0 * (t3.MinTemp - t2.MinTemp) / ABS(t2.MinTemp), 3) || '%%' 'Minimum temperature change',
            t2.MaxTemp 'Maximum temperature (%d)',
            t3.MaxTemp 'Maximum temperature (%d)',
            ROUND(100.0 * (t3.MaxTemp - t2.MaxTemp) / ABS(t2.MaxTemp), 3) || '%%' 'Maximum temperature change'
            FROM (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = '%s') t1
            LEFT JOIN (SELECT * FROM CityTemp WHERE Year = %d) t2 ON t1.CityID = t2.CityID
            LEFT JOIN (SELECT * FROM CityTemp WHERE Year = %d) t3 ON t1.CityID = t3.CityID
            ORDER BY CityName ASC; 
            """,
            countryname,
            startingyear,
            endingyear,
            startingyear,
            endingyear,
            startingyear,
            endingyear,
            countryname,
            startingyear,
            endingyear);
            System.out.println(query);
            json = AppJSON.getJSON(database, query);
        }

        if (startingyear == 0 && endingyear == 0 && viewby.equals("States") && countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
            query = "SELECT DISTINCT CountryName FROM Country JOIN State ON Country.CountryID = State.CountryID;";
            System.out.println(query);
            json = AppJSON.getJSON(database, query);
        } 

        if (startingyear != 0 && endingyear != 0 && viewby.equals("States") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
            query = String.format("""
            SELECT t1.StateName 'State name (%s)',
            t2.AvgTemp 'Average temperature (%d)',
            t3.AvgTemp 'Average temperature (%d)',
            ROUND(100.0 * (t3.AvgTemp - t2.AvgTemp) / ABS(t2.AvgTemp), 3) || '%%' 'Average temperature change',
            t2.MinTemp 'Minimum temperature (%d)',
            t3.MinTemp 'Minimum temperature (%d)',
            ROUND(100.0 * (t3.MinTemp - t2.MinTemp) / ABS(t2.MinTemp), 3) || '%%' 'Minimum temperature change',
            t2.MaxTemp 'Maximum temperature (%d)',
            t3.MaxTemp 'Maximum temperature (%d)',
            ROUND(100.0 * (t3.MaxTemp - t2.MaxTemp) / ABS(t2.MaxTemp), 3) || '%%' 'Maximum temperature change'
            FROM (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = '%s') t1
            LEFT JOIN (SELECT * FROM StateTemp WHERE Year = %d) t2 ON t1.StateID = t2.StateID
            LEFT JOIN (SELECT * FROM StateTemp WHERE Year = %d) t3 ON t1.StateID = t3.StateID
            ORDER BY StateName ASC; 
            """,
            countryname,
            startingyear,
            endingyear,
            startingyear,
            endingyear,
            startingyear,
            endingyear,
            countryname,
            startingyear,
            endingyear);
            System.out.println(query);
            json = AppJSON.getJSON(database, query);
        }
    }
}