/*3. Average temperature, Similarity in temperature and population*/

/*3A. Average temperature*/

-- Display world (input: start year, time period)
SELECT t1.AvgTemp1, t1.LandOceanAvgTemp1, t2.AvgTemp2, t2.LandOceanAvgTemp2, t3.AvgTemp3, t3.LandOceanAvgTemp3, t4.AvgTemp4, t4.LandOceanAvgTemp4, t5.AvgTemp5, t5.LandOceanAvgTemp5
FROM (SELECT CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp1, CASE WHEN COUNT(LandOceanAvgTemp) = 10 + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END LandOceanAvgTemp1 
FROM World WHERE Year BETWEEN 1960 AND 1960 + 10) t1
JOIN (SELECT CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp2, CASE WHEN COUNT(LandOceanAvgTemp) = 10 + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END LandOceanAvgTemp2
FROM World WHERE Year BETWEEN 1970 AND 1970 + 10) t2
JOIN (SELECT CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp3, CASE WHEN COUNT(LandOceanAvgTemp) = 10 + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END LandOceanAvgTemp3
FROM World WHERE Year BETWEEN 1980 AND 1980 + 10) t3
JOIN (SELECT CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp4, CASE WHEN COUNT(LandOceanAvgTemp) = 10 + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END LandOceanAvgTemp4
FROM World WHERE Year BETWEEN 1990 AND 1990 + 10) t4
JOIN (SELECT CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp5, CASE WHEN COUNT(LandOceanAvgTemp) = 10 + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END LandOceanAvgTemp5
FROM World WHERE Year BETWEEN 2000 AND 2000 + 10) t5;

-- Display countries (input: start year, time period, sort order)
SELECT t1.CountryName, t2.AvgTemp1, t3.AvgTemp2, t4.AvgTemp3, t5.AvgTemp4, t6.AvgTemp5
FROM Country t1
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp1 FROM CountryTemp WHERE Year BETWEEN 1920 AND 1920 + 10 GROUP BY CountryID) t2
ON t1.CountryID = t2.CountryID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp2 FROM CountryTemp WHERE Year BETWEEN 1930 AND 1930 + 10 GROUP BY CountryID) t3
ON t1.CountryID = t3.CountryID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp3 FROM CountryTemp WHERE Year BETWEEN 1990 AND 1990 + 10 GROUP BY CountryID) t4
ON t1.CountryID = t4.CountryID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp4 FROM CountryTemp WHERE Year BETWEEN 2000 AND 2000 + 10 GROUP BY CountryID) t5
ON t1.CountryID = t5.CountryID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp5 FROM CountryTemp WHERE Year BETWEEN 2003 AND 2003 + 10 GROUP BY CountryID) t6
ON t1.CountryID = t6.CountryID
ORDER BY CountryName ASC;

-- Display countries having cities
SELECT DISTINCT CountryName FROM Country JOIN City ON Country.CountryID = City.CountryID;
-- Display cities (input: start year, time period, sort order, country name)
SELECT t1.CityName, t2.AvgTemp1, t3.AvgTemp2, t4.AvgTemp3, t5.AvgTemp4, t6.AvgTemp5
FROM (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = "Australia") t1
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp1 FROM CityTemp WHERE Year BETWEEN 1920 AND 1920 + 10 GROUP BY CityID) t2
ON t1.CityID = t2.CityID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp2 FROM CityTemp WHERE Year BETWEEN 1930 AND 1930 + 10 GROUP BY CityID) t3
ON t1.CityID = t3.CityID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp3 FROM CityTemp WHERE Year BETWEEN 1990 AND 1990 + 10 GROUP BY CityID) t4
ON t1.CityID = t4.CityID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp4 FROM CityTemp WHERE Year BETWEEN 2000 AND 2000 + 10 GROUP BY CityID) t5
ON t1.CityID = t5.CityID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp5 FROM CityTemp WHERE Year BETWEEN 2003 AND 2003 + 10 GROUP BY CityID) t6
ON t1.CityID = t6.CityID
ORDER BY CityName ASC;

-- Display countries having states
SELECT DISTINCT CountryName FROM Country JOIN State ON Country.CountryID = State.CountryID;
-- Display states (input: start year, time period, sort order, country name)
SELECT t1.StateName, t2.AvgTemp1, t3.AvgTemp2, t4.AvgTemp3, t5.AvgTemp4, t6.AvgTemp5
FROM (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = "United States") t1
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp1 FROM StateTemp WHERE Year BETWEEN 1920 AND 1920 + 10 GROUP BY StateID) t2
ON t1.StateID = t2.StateID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp2 FROM StateTemp WHERE Year BETWEEN 1930 AND 1930 + 10 GROUP BY StateID) t3
ON t1.StateID = t3.StateID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp3 FROM StateTemp WHERE Year BETWEEN 1990 AND 1990 + 10 GROUP BY StateID) t4
ON t1.StateID = t4.StateID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp4 FROM StateTemp WHERE Year BETWEEN 2000 AND 2000 + 10 GROUP BY StateID) t5
ON t1.StateID = t5.StateID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END AvgTemp5 FROM StateTemp WHERE Year BETWEEN 2003 AND 2003 + 10 GROUP BY StateID) t6
ON t1.StateID = t6.StateID
ORDER BY StateName ASC;

/*3B. Similarity in temperature and population*/

----- Similarity in temperature

-- Display countries (input: start year, time period, country name, number of most similar results)
SELECT t1.CountryName,
       t2.AvgTemp || " (" || t2.Year || ")" AvgTemp1,
       t3.AvgTemp || " (" || t3.Year || ")" AvgTemp2,
       ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3) SimScore
FROM Country t1
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN 1750 AND 2013 - 10) t2 ON t1.CountryID = t2.CountryID
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN 1750 + 10 AND 2013) t3 ON t1.CountryID = t3.CountryID AND t3.Year = t2.Year + 10
JOIN (SELECT * FROM Country WHERE CountryName = "Denmark") t4
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = 2000) t5 ON t4.CountryID = t5.CountryID
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = 2000 + 10) t6 ON t4.CountryID = t6.CountryID
WHERE SimScore IS NOT NULL
ORDER BY SimScore
LIMIT 10;

-- Display countries having cities
SELECT DISTINCT CountryName FROM Country JOIN City ON Country.CountryID = City.CountryID;
-- Display table (input: start year, time period, country name, city name, number of most similar results)
SELECT t1.CityName,
       t2.AvgTemp || " (" || t2.Year || ")" AvgTemp1,
       t3.AvgTemp || " (" || t3.Year || ")" AvgTemp2,
       ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3) SimScore
FROM (SELECT Country.*, City.* FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = "Argentina") t1
LEFT JOIN (SELECT * FROM CityTemp WHERE Year BETWEEN 1750 AND 2013 - 10) t2 ON t1.CityID = t2.CityID
LEFT JOIN (SELECT * FROM CityTemp WHERE Year BETWEEN 1750 + 10 AND 2013) t3 ON t1.CityID = t3.CityID AND t3.Year = t2.Year + 10
JOIN (SELECT Country.*, City.* FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = "Argentina" AND CityName = "Catamarca") t4
LEFT JOIN (SELECT * FROM CityTemp WHERE Year = 2000) t5 ON t4.CityID = t5.CityID
LEFT JOIN (SELECT * FROM CityTemp WHERE Year = 2000 + 10) t6 ON t4.CityID = t6.CityID
WHERE SimScore IS NOT NULL
ORDER BY SimScore
LIMIT 10;

-- Display countries having states
SELECT DISTINCT CountryName FROM Country JOIN State ON Country.CountryID = State.CountryID;
-- Display states (input: start year, time period, country name, state name, number of most similar results)
SELECT t1.StateName,
       t2.AvgTemp || " (" || t2.Year || ")" AvgTemp1,
       t3.AvgTemp || " (" || t3.Year || ")" AvgTemp2,
       ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3) SimScore
FROM (SELECT Country.*, State.* FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = "United States") t1
LEFT JOIN (SELECT * FROM StateTemp WHERE Year BETWEEN 1750 AND 2013 - 10) t2 ON t1.StateID = t2.StateID
LEFT JOIN (SELECT * FROM StateTemp WHERE Year BETWEEN 1750 + 10 AND 2013) t3 ON t1.StateID = t3.StateID AND t3.Year = t2.Year + 10
JOIN (SELECT Country.*, State.* FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = "United States" AND StateName = "Nevada") t4
LEFT JOIN (SELECT * FROM StateTemp WHERE Year = 2000) t5 ON t4.StateID = t5.StateID
LEFT JOIN (SELECT * FROM StateTemp WHERE Year = 2000 + 10) t6 ON t4.StateID = t6.StateID
WHERE SimScore IS NOT NULL
ORDER BY SimScore
LIMIT 10;

----- Similarity in population

-- Display countries (input: start year, time period, country name, number of most similar results)
SELECT t1.CountryName,
       printf("%,d", t2.Population) || " (" || t2.Year || ")" Population1,
       printf("%,d", t3.Population) || " (" || t3.Year || ")" Population2,
       ROUND(SQRT(POWER(t2.Population - t5.Population, 2) + POWER(t3.Population - t6.Population, 2)), 3) SimScore
FROM Country t1
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN 1960 AND 2013 - 10) t2 ON t1.CountryID = t2.CountryID
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN 1960 + 10 AND 2013) t3 ON t1.CountryID = t3.CountryID AND t3.Year = t2.Year + 10
JOIN (SELECT * FROM Country WHERE CountryName = "Denmark") t4
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = 1970) t5 ON t4.CountryID = t5.CountryID
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = 1970 + 10) t6 ON t4.CountryID = t6.CountryID
WHERE SimScore IS NOT NULL
ORDER BY SimScore
LIMIT 10;

----- Similarity in temperature and population

-- Display countries (input: start year, time period, country name, number of most similar results)
SELECT t1.CountryName,
       t2.AvgTemp || " (" || t2.Year || ")" AvgTemp1,
       t3.AvgTemp || " (" || t3.Year || ")" AvgTemp2,
       t4.Population || " (" || t4.Year || ")" Population1,
       t5.Population || " (" || t5.Year || ")" Population2,
       ROUND(SQRT(POWER(t2.AvgTemp - t7.AvgTemp, 2) + POWER(t3.AvgTemp - t8.AvgTemp, 2) + POWER(t4.Population - t9.Population, 2) + POWER(t5.Population - t10.Population, 2)), 3) SimScore
FROM Country t1
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN 1750 AND 2013 - 10) t2 ON t1.CountryID = t2.CountryID
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN 1750 + 10 AND 2013) t3 ON t1.CountryID = t3.CountryID AND t3.Year = t2.Year + 10
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN 1960 AND 2013 - 10) t4 ON t1.CountryID = t4.CountryID
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN 1960 + 10 AND 2013) t5 ON t1.CountryID = t5.CountryID AND t5.Year = t2.Year + 10
JOIN (SELECT * FROM Country WHERE CountryName = "Vietnam") t6
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = 1980) t7 ON t6.CountryID = t7.CountryID
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = 1980 + 10) t8 ON t6.CountryID = t8.CountryID
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = 1980) t9 ON t7.CountryID = t9.CountryID
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = 1980 + 10) t10 ON t6.CountryID = t10.CountryID
WHERE SimScore IS NOT NULL
ORDER BY SimScore
LIMIT 10;