package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AvgTemp implements Handler{
    public static final String URL = "html/AvgTemp.html";

    @Override
    public void handle(Context context) throws Exception {
        if (context.method().equals("GET")) context.render("public/html/AvgTemp.html");   
        if (context.method().equals("POST")) {
            //String jsonin = "[1920,1940,1960,0,0,20,\"Countries\",\"\",\"1920\",\"Ascending\"]";
            String jsonin = context.body();
            String[] jsoninarray = jsonin.replace("[", "").replace("]", "").replace("\"", "").split(",");
            int[] startingyear = new int[5];
            for (int i = 0; i < 5; i++) {
                startingyear[i] = Integer.valueOf(jsoninarray[i]);
            }
            int period = Integer.valueOf(jsoninarray[5]);
            String viewby = jsoninarray[6];
            String countryname = jsoninarray[7];
            String sortcategory = jsoninarray[8];
            String sortorder = jsoninarray[9];
        
            String database = "jdbc:sqlite:database/EcoStats.db";
            String query = "";
            String jsonout = "";

            if (startingyear[0] != 0 && startingyear[1] == 0 && startingyear[2] == 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("World") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                query = String.format("""
                SELECT c1 'Average temperature (%d-%d)', c2 'Land-Ocean average temperature (%d-%d)'
                FROM (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c2
                FROM World WHERE Year BETWEEN %d AND %d) t1
                """,
                startingyear[0], startingyear[0] + period, startingyear[0], startingyear[0] + period,
                period,
                period,
                startingyear[0], startingyear[0] + period);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] == 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("World") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                query = String.format("""
                SELECT c1 'Average temperature (%d-%d)', c2 'Land-Ocean average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)', c4 'Land-Ocean average temperature (%d-%d)'
                FROM (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c2
                FROM World WHERE Year BETWEEN %d AND %d) t1
                JOIN (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c4
                FROM World WHERE Year BETWEEN %d AND %d) t2
                """,
                startingyear[0], startingyear[0] + period, startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period, startingyear[1], startingyear[1] + period,
                period,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                period,
                startingyear[1], startingyear[1] + period);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("World") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                query = String.format("""
                SELECT c1 'Average temperature (%d-%d)', c2 'Land-Ocean average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)', c4 'Land-Ocean average temperature (%d-%d)',
                c5 'Average temperature (%d-%d)', c6 'Land-Ocean average temperature (%d-%d)'
                FROM (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c2
                FROM World WHERE Year BETWEEN %d AND %d) t1
                JOIN (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c4
                FROM World WHERE Year BETWEEN %d AND %d) t2
                JOIN (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c5,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c6
                FROM World WHERE Year BETWEEN %d AND %d) t3
                """,
                startingyear[0], startingyear[0] + period, startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period, startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period, startingyear[2], startingyear[2] + period,
                period,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                period,
                startingyear[2], startingyear[2] + period);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] != 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("World") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                query = String.format("""
                SELECT c1 'Average temperature (%d-%d)', c2 'Land-Ocean average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)', c4 'Land-Ocean average temperature (%d-%d)',
                c5 'Average temperature (%d-%d)', c6 'Land-Ocean average temperature (%d-%d)',
                c7 'Average temperature (%d-%d)', c8 'Land-Ocean average temperature (%d-%d)'
                FROM (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c2
                FROM World WHERE Year BETWEEN %d AND %d) t1
                JOIN (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c4
                FROM World WHERE Year BETWEEN %d AND %d) t2
                JOIN (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c5,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c6
                FROM World WHERE Year BETWEEN %d AND %d) t3
                JOIN (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c7,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c8
                FROM World WHERE Year BETWEEN %d AND %d) t4
                """,
                startingyear[0], startingyear[0] + period, startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period, startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period, startingyear[2], startingyear[2] + period,
                startingyear[3], startingyear[3] + period, startingyear[3], startingyear[3] + period,
                period,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                period,
                startingyear[2], startingyear[2] + period,
                period,
                period,
                startingyear[3], startingyear[3] + period);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] != 0 && startingyear[4] != 0 &&
            period != 0 && viewby.equals("World") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                query = String.format("""
                SELECT c1 'Average temperature (%d-%d)', c2 'Land-Ocean average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)', c4 'Land-Ocean average temperature (%d-%d)',
                c5 'Average temperature (%d-%d)', c6 'Land-Ocean average temperature (%d-%d)',
                c7 'Average temperature (%d-%d)', c8 'Land-Ocean average temperature (%d-%d)',
                c9 'Average temperature (%d-%d)', c10 'Land-Ocean average temperature (%d-%d)'
                FROM (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c2
                FROM World WHERE Year BETWEEN %d AND %d) t1
                JOIN (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c4
                FROM World WHERE Year BETWEEN %d AND %d) t2
                JOIN (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c5,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c6
                FROM World WHERE Year BETWEEN %d AND %d) t3
                JOIN (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c7,
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c8
                FROM World WHERE Year BETWEEN %d AND %d) t4
                JOIN (SELECT CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c9, 
                CASE WHEN COUNT(LandOceanAvgTemp) = %d + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c10
                FROM World WHERE Year BETWEEN %d AND %d) t5;
                """,
                startingyear[0], startingyear[0] + period, startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period, startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period, startingyear[2], startingyear[2] + period,
                startingyear[3], startingyear[3] + period, startingyear[3], startingyear[3] + period,
                startingyear[4], startingyear[4] + period, startingyear[4], startingyear[4] + period,
                period,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                period,
                startingyear[2], startingyear[2] + period,
                period,
                period,
                startingyear[3], startingyear[3] + period,
                period,
                period,
                startingyear[4], startingyear[4] + period);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] == 0 && startingyear[2] == 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("Countries") && countryname.equals("") && !sortcategory.equals("") && !sortorder.equals("")) {       
                if (sortcategory.equals("Country name")) sortcategory = "CountryName";
                else for (int i = 0; i < 1; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";
                query = String.format("""
                SELECT CountryName 'Country name',
                c1 'Average temperature (%d-%d)'
                FROM Country t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t2 ON t1.CountryID = t2.CountryID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t3 ON t1.CountryID = t3.CountryID
                ORDER BY %s %s;
                """,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[0], startingyear[0] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] == 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("Countries") && countryname.equals("") && !sortcategory.equals("") && !sortorder.equals("")) {       
                if (sortcategory.equals("Country name")) sortcategory = "CountryName";
                else for (int i = 0; i < 2; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";             
                query = String.format("""
                SELECT CountryName 'Country name',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)'
                FROM Country t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t2 ON t1.CountryID = t2.CountryID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t3 ON t1.CountryID = t3.CountryID
                ORDER BY %s %s;
                """,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("Countries") && countryname.equals("") && !sortcategory.equals("") && !sortorder.equals("")) {       
                if (sortcategory.equals("Country name")) sortcategory = "CountryName";
                else for (int i = 0; i < 3; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";                
                query = String.format("""
                SELECT CountryName 'Country name',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)'
                FROM Country t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t2 ON t1.CountryID = t2.CountryID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t3 ON t1.CountryID = t3.CountryID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t4 ON t1.CountryID = t4.CountryID
                ORDER BY %s %s;
                """,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                startingyear[2], startingyear[2] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] != 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("Countries") && countryname.equals("") && !sortcategory.equals("") && !sortorder.equals("")) {       
                if (sortcategory.equals("Country name")) sortcategory = "CountryName";
                else for (int i = 0; i < 4; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";                
                query = String.format("""
                SELECT CountryName 'Country name',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)',
                c4 'Average temperature (%d-%d)'
                FROM Country t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t2 ON t1.CountryID = t2.CountryID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t3 ON t1.CountryID = t3.CountryID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t4 ON t1.CountryID = t4.CountryID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c4
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t5 ON t1.CountryID = t5.CountryID
                ORDER BY %s %s;
                """,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period,
                startingyear[3], startingyear[3] + period,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                startingyear[2], startingyear[2] + period,
                period,
                startingyear[3], startingyear[3] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] != 0 && startingyear[4] != 0 &&
            period != 0 && viewby.equals("Countries") && countryname.equals("") && !sortcategory.equals("") && !sortorder.equals("")) {       
                if (sortcategory.equals("Country name")) sortcategory = "CountryName";
                else for (int i = 0; i < 5; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";               
                query = String.format("""
                SELECT CountryName 'Country name',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)',
                c4 'Average temperature (%d-%d)',
                c5 'Average temperature (%d-%d)'
                FROM Country t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t2 ON t1.CountryID = t2.CountryID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t3 ON t1.CountryID = t3.CountryID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t4 ON t1.CountryID = t4.CountryID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c4
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t5 ON t1.CountryID = t5.CountryID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c5
                FROM CountryTemp WHERE Year BETWEEN %d AND %d GROUP BY CountryID) t6 ON t1.CountryID = t6.CountryID
                ORDER BY %s %s;
                """,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period,
                startingyear[3], startingyear[3] + period,
                startingyear[4], startingyear[4] + period,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                startingyear[2], startingyear[2] + period,
                period,
                startingyear[3], startingyear[3] + period,
                period,
                startingyear[4], startingyear[4] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] == 0 && startingyear[1] == 0 && startingyear[2] == 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period == 0 && viewby.equals("Cities") && countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                query = "SELECT DISTINCT CountryName FROM Country JOIN City ON Country.CountryID = City.CountryID;";
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] == 0 && startingyear[2] == 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("Cities") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                if (sortcategory.equals("City name")) sortcategory = "CityName";
                else for (int i = 0; i < 1; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";               
                query = String.format("""
                SELECT CityName 'City name (%s)',
                c1 'Average temperature (%d-%d)'
                FROM (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t2 ON t1.CityID = t2.CityID
                ORDER BY %s %s;
                """,
                countryname,
                startingyear[0], startingyear[0] + period,
                countryname,
                period,
                startingyear[0], startingyear[0] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] == 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("Cities") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                if (sortcategory.equals("City name")) sortcategory = "CityName";
                else for (int i = 0; i < 2; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";                
                query = String.format("""
                SELECT CityName 'City name (%s)',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)'
                FROM (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t2 ON t1.CityID = t2.CityID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t3 ON t1.CityID = t3.CityID
                ORDER BY %s %s;
                """,
                countryname,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                countryname,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("Cities") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                if (sortcategory.equals("City name")) sortcategory = "CityName";
                else for (int i = 0; i < 3; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";                
                query = String.format("""
                SELECT CityName 'City name (%s)',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)'
                FROM (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t2 ON t1.CityID = t2.CityID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t3 ON t1.CityID = t3.CityID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t4 ON t1.CityID = t4.CityID
                ORDER BY %s %s;
                """,
                countryname,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period,
                countryname,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                startingyear[2], startingyear[2] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] != 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("Cities") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                if (sortcategory.equals("City name")) sortcategory = "CityName";
                else for (int i = 0; i < 4; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";               
                query = String.format("""
                SELECT CityName 'City name (%s)',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)',
                c4 'Average temperature (%d-%d)'
                FROM (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t2 ON t1.CityID = t2.CityID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t3 ON t1.CityID = t3.CityID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t4 ON t1.CityID = t4.CityID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c4
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t5 ON t1.CityID = t5.CityID
                ORDER BY %s %s;
                """,
                countryname,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period,
                startingyear[3], startingyear[3] + period,
                countryname,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                startingyear[2], startingyear[2] + period,
                period,
                startingyear[3], startingyear[3] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] != 0 && startingyear[4] != 0 &&
            period != 0 && viewby.equals("Cities") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                if (sortcategory.equals("City name")) sortcategory = "CityName";
                else for (int i = 0; i < 5; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";              
                query = String.format("""
                SELECT CityName 'City name (%s)',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)',
                c4 'Average temperature (%d-%d)',
                c5 'Average temperature (%d-%d)'
                FROM (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t2 ON t1.CityID = t2.CityID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t3 ON t1.CityID = t3.CityID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t4 ON t1.CityID = t4.CityID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c4
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t5 ON t1.CityID = t5.CityID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c5
                FROM CityTemp WHERE Year BETWEEN %d AND %d GROUP BY CityID) t6 ON t1.CityID = t6.CityID
                ORDER BY %s %s;
                """,
                countryname,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period,
                startingyear[3], startingyear[3] + period,
                startingyear[4], startingyear[4] + period,
                countryname,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                startingyear[2], startingyear[2] + period,
                period,
                startingyear[3], startingyear[3] + period,
                period,
                startingyear[4], startingyear[4] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] == 0 && startingyear[1] == 0 && startingyear[2] == 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period == 0 && viewby.equals("States") && countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                query = "SELECT DISTINCT CountryName FROM Country JOIN State ON Country.CountryID = State.CountryID;";
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] == 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("States") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                if (sortcategory.equals("State name")) sortcategory = "StateName";
                else for (int i = 0; i < 1; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";           
                query = String.format("""
                SELECT StateName 'State name (%s)',
                c1 'Average temperature (%d-%d)'
                FROM (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t2 ON t1.StateID = t2.StateID
                ORDER BY %s %s;
                """,
                countryname,
                startingyear[0], startingyear[0] + period,
                countryname,
                period,
                startingyear[0], startingyear[0] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] == 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("States") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                if (sortcategory.equals("State name")) sortcategory = "StateName";
                else for (int i = 0; i < 2; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";                
                query = String.format("""
                SELECT StateName 'State name (%s)',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)'
                FROM (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t2 ON t1.StateID = t2.StateID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t3 ON t1.StateID = t3.StateID
                ORDER BY %s %s;
                """,
                countryname,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                countryname,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] == 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("States") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                if (sortcategory.equals("State name")) sortcategory = "StateName";
                else for (int i = 0; i < 3; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";             
                query = String.format("""
                SELECT StateName 'State name (%s)',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)'
                FROM (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t2 ON t1.StateID = t2.StateID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t3 ON t1.StateID = t3.StateID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t4 ON t1.StateID = t4.StateID
                ORDER BY %s %s;
                """,
                countryname,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period,
                countryname,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                startingyear[2], startingyear[2] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] != 0 && startingyear[4] == 0 &&
            period != 0 && viewby.equals("States") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                if (sortcategory.equals("State name")) sortcategory = "StateName";
                else for (int i = 0; i < 4; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";                
                query = String.format("""
                SELECT StateName 'State name (%s)',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)',
                c4 'Average temperature (%d-%d)'
                FROM (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t2 ON t1.StateID = t2.StateID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t3 ON t1.StateID = t3.StateID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t4 ON t1.StateID = t4.StateID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c4
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t5 ON t1.StateID = t5.StateID
                ORDER BY %s %s;
                """,
                countryname,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period,
                startingyear[3], startingyear[3] + period,
                countryname,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                startingyear[2], startingyear[2] + period,
                period,
                startingyear[3], startingyear[3] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }

            if (startingyear[0] != 0 && startingyear[1] != 0 && startingyear[2] != 0 && startingyear[3] != 0 && startingyear[4] != 0 &&
            period != 0 && viewby.equals("States") && !countryname.equals("") && sortcategory.equals("") && sortorder.equals("")) {
                if (sortcategory.equals("State name")) sortcategory = "StateName";
                else for (int i = 0; i < 5; i++) {
                    if (Integer.valueOf(sortcategory) == startingyear[i]) {
                        sortcategory = String.format("c%d", i + 1);
                        break;
                    }
                }
                if (sortorder.equals("Ascending")) sortorder = "ASC";
                if (sortorder.equals("Descending")) sortorder = "DESC";                
                query = String.format("""
                SELECT StateName 'State name (%s)',
                c1 'Average temperature (%d-%d)',
                c2 'Average temperature (%d-%d)',
                c3 'Average temperature (%d-%d)',
                c4 'Average temperature (%d-%d)',
                c5 'Average temperature (%d-%d)'
                FROM (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = '%s') t1
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t2 ON t1.StateID = t2.StateID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t3 ON t1.StateID = t3.StateID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t4 ON t1.StateID = t4.StateID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c4
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t5 ON t1.StateID = t5.StateID
                LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = %d + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c5
                FROM StateTemp WHERE Year BETWEEN %d AND %d GROUP BY StateID) t6 ON t1.StateID = t6.StateID
                ORDER BY %s %s;
                """,
                countryname,
                startingyear[0], startingyear[0] + period,
                startingyear[1], startingyear[1] + period,
                startingyear[2], startingyear[2] + period,
                startingyear[3], startingyear[3] + period,
                startingyear[4], startingyear[4] + period,
                countryname,
                period,
                startingyear[0], startingyear[0] + period,
                period,
                startingyear[1], startingyear[1] + period,
                period,
                startingyear[2], startingyear[2] + period,
                period,
                startingyear[3], startingyear[3] + period,
                period,
                startingyear[4], startingyear[4] + period,
                sortcategory, sortorder);
                System.out.println(query);
                jsonout = AppJSON.getJSON(database, query);
                context.result(jsonout);
            }
        }
    }
}