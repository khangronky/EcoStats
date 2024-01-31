----- 3. Average temperature, Similarity in temperature and population

--- 3A. Average temperature

-- Display world (input: start year, time period)
SELECT c1 'Average temperature (1960-1970)', c2 'Land-Ocean average temperature (1960-1970)',
c3 'Average temperature (1970-1980)', c4 'Land-Ocean average temperature (1970-1980)',
c5 'Average temperature (1980-1990)', c6 'Land-Ocean average temperature (1980-1990)',
c7 'Average temperature (1990-2000)', c8 'Land-Ocean average temperature (1990-2000)',
c9 'Average temperature (2000-2010)', c10 'Land-Ocean average temperature (2000-2010)'
FROM (SELECT CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1,
CASE WHEN COUNT(LandOceanAvgTemp) = 10 + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c2
FROM World WHERE Year BETWEEN 1960 AND 1970) t1
JOIN (SELECT CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3,
CASE WHEN COUNT(LandOceanAvgTemp) = 10 + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c4
FROM World WHERE Year BETWEEN 1970 AND 1980) t2
JOIN (SELECT CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c5,
CASE WHEN COUNT(LandOceanAvgTemp) = 10 + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c6
FROM World WHERE Year BETWEEN 1980 AND 1990) t3
JOIN (SELECT CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c7,
CASE WHEN COUNT(LandOceanAvgTemp) = 10 + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c8
FROM World WHERE Year BETWEEN 1990 AND 2000) t4
JOIN (SELECT CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c9, 
CASE WHEN COUNT(LandOceanAvgTemp) = 10 + 1 THEN ROUND(AVG(LandOceanAvgTemp), 3) ELSE NULL END c10
FROM World WHERE Year BETWEEN 2000 AND 2010) t5;

-- Display countries (input: start year, time period, sort order)
SELECT CountryName 'Country name',
c1 'Average temperature (1920-1930)', c2 'Average temperature (1930-1940)',
c3 'Average temperature (1990-2000)', c4 'Average temperature (2000-2010)',
c5 'Average temperature (2003-2013)'
FROM Country t1
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
FROM CountryTemp WHERE Year BETWEEN 1920 AND 1930 GROUP BY CountryID) t2 ON t1.CountryID = t2.CountryID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
FROM CountryTemp WHERE Year BETWEEN 1930 AND 1940 GROUP BY CountryID) t3 ON t1.CountryID = t3.CountryID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
FROM CountryTemp WHERE Year BETWEEN 1990 AND 2000 GROUP BY CountryID) t4 ON t1.CountryID = t4.CountryID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c4
FROM CountryTemp WHERE Year BETWEEN 2000 AND 2010 GROUP BY CountryID) t5 ON t1.CountryID = t5.CountryID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c5
FROM CountryTemp WHERE Year BETWEEN 2003 AND 2013 GROUP BY CountryID) t6 ON t1.CountryID = t6.CountryID
ORDER BY CountryName ASC;

-- Display countries having cities
SELECT DISTINCT CountryName FROM Country JOIN City ON Country.CountryID = City.CountryID ORDER BY CountryName;
-- Display cities (input: start year, time period, sort order, country name)
SELECT CityName 'City name (Australia)',
c1 'Average temperature (1920-1930)', c2 'Average temperature (1930-1940)',
c3 'Average temperature (1990-2000)', c4 'Average temperature (2000-2010)',
c5 'Average temperature (2003-2013)'
FROM (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = 'Australia') t1
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
FROM CityTemp WHERE Year BETWEEN 1920 AND 1930 GROUP BY CityID) t2 ON t1.CityID = t2.CityID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
FROM CityTemp WHERE Year BETWEEN 1930 AND 1940 GROUP BY CityID) t3 ON t1.CityID = t3.CityID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
FROM CityTemp WHERE Year BETWEEN 1990 AND 2000 GROUP BY CityID) t4 ON t1.CityID = t4.CityID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c4
FROM CityTemp WHERE Year BETWEEN 2000 AND 2010 GROUP BY CityID) t5 ON t1.CityID = t5.CityID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c5
FROM CityTemp WHERE Year BETWEEN 2003 AND 2013 GROUP BY CityID) t6 ON t1.CityID = t6.CityID
ORDER BY CityName ASC;

-- Display countries having states
SELECT DISTINCT CountryName FROM Country JOIN State ON Country.CountryID = State.CountryID ORDER BY CountryName;
-- Display states (input: start year, time period, sort order, country name)
SELECT StateName 'State name (United States)',
c1 'Average temperature (1920-1930)', c2 'Average temperature (1930-1940)',
c3 'Average temperature (1990-2000)', c4 'Average temperature (2000-2010)',
c5 'Average temperature (2003-2013)'
FROM (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = 'United States') t1
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c1
FROM StateTemp WHERE Year BETWEEN 1920 AND 1930 GROUP BY StateID) t2 ON t1.StateID = t2.StateID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c2
FROM StateTemp WHERE Year BETWEEN 1930 AND 1940 GROUP BY StateID) t3 ON t1.StateID = t3.StateID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c3
FROM StateTemp WHERE Year BETWEEN 1990 AND 2000 GROUP BY StateID) t4 ON t1.StateID = t4.StateID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c4
FROM StateTemp WHERE Year BETWEEN 2000 AND 2010 GROUP BY StateID) t5 ON t1.StateID = t5.StateID
LEFT JOIN (SELECT *, CASE WHEN COUNT(AvgTemp) = 10 + 1 THEN ROUND(AVG(AvgTemp), 3) ELSE NULL END c5
FROM StateTemp WHERE Year BETWEEN 2003 AND 2013 GROUP BY StateID) t6 ON t1.StateID = t6.StateID
ORDER BY StateName ASC;

----- 3B. Similarity in temperature and population

--- Similarity in temperature

-- Display countries
SELECT CountryName FROM Country;
-- Display countries (input: start year, time period, country name, number of most similar results)
SELECT t1.CountryName 'Country name',
t2.AvgTemp || ' (' || t2.Year || ')' 'Average temperature (Starting year)',
t3.AvgTemp || ' (' || t3.Year || ')' 'Average temperature (Ending year)'
FROM Country t1
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN 1750 AND 2003) t2 ON t1.CountryID = t2.CountryID
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN 1760 AND 2013) t3 ON t1.CountryID = t3.CountryID AND t3.Year = t2.Year + 10
JOIN (SELECT * FROM Country WHERE CountryName = 'Denmark') t4
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = 2000) t5 ON t4.CountryID = t5.CountryID
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = 2010) t6 ON t4.CountryID = t6.CountryID
WHERE ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3) IS NOT NULL
ORDER BY ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3)
LIMIT 10;

-- Display countries having cities
SELECT DISTINCT CountryName FROM Country JOIN City ON Country.CountryID = City.CountryID;
-- Display cities of a selected country
SELECT CityName FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = 'Argentina';
-- Display cities (input: start year, time period, country name, city name, number of most similar results)
SELECT t1.CityName 'City name (Argentina)',
t2.AvgTemp || ' (' || t2.Year || ')' 'Average temperature (Starting year)',
t3.AvgTemp || ' (' || t3.Year || ')' 'Average temperature (Ending year)'
FROM (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = 'Argentina') t1
LEFT JOIN (SELECT * FROM CityTemp WHERE Year BETWEEN 1750 AND 2003) t2 ON t1.CityID = t2.CityID
LEFT JOIN (SELECT * FROM CityTemp WHERE Year BETWEEN 1760 AND 2013) t3 ON t1.CityID = t3.CityID AND t3.Year = t2.Year + 10
JOIN (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = 'Argentina' AND CityName = 'Catamarca') t4
LEFT JOIN (SELECT * FROM CityTemp WHERE Year = 2000) t5 ON t4.CityID = t5.CityID
LEFT JOIN (SELECT * FROM CityTemp WHERE Year = 2010) t6 ON t4.CityID = t6.CityID
WHERE ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3) IS NOT NULL
ORDER BY ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3)
LIMIT 10;

-- Display countries having states
SELECT DISTINCT CountryName FROM Country JOIN State ON Country.CountryID = State.CountryID;
-- Display states of a selected country
SELECT StateName FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = 'United States';
-- Display states (input: start year, time period, country name, state name, number of most similar results)
SELECT t1.StateName 'State name (United States)',
t2.AvgTemp || ' (' || t2.Year || ')' 'Average temperature (Starting year)',
t3.AvgTemp || ' (' || t3.Year || ')' 'Average temperature (Ending year)'
FROM (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = 'United States') t1
LEFT JOIN (SELECT * FROM StateTemp WHERE Year BETWEEN 1750 AND 2003) t2 ON t1.StateID = t2.StateID
LEFT JOIN (SELECT * FROM StateTemp WHERE Year BETWEEN 1760 AND 2013) t3 ON t1.StateID = t3.StateID AND t3.Year = t2.Year + 10
JOIN (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = 'United States' AND StateName = 'Nevada') t4
LEFT JOIN (SELECT * FROM StateTemp WHERE Year = 2000) t5 ON t4.StateID = t5.StateID
LEFT JOIN (SELECT * FROM StateTemp WHERE Year = 2010) t6 ON t4.StateID = t6.StateID
WHERE ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3) IS NOT NULL
ORDER BY ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3)
LIMIT 10;

--- Similarity in population

-- Display countries
SELECT CountryName FROM Country;
-- Display countries (input: start year, time period, country name, number of most similar results)
SELECT t1.CountryName 'Country name',
printf('%,d', t2.Population) || ' (' || t2.Year || ')' 'Population (Starting year)',
printf('%,d', t3.Population) || ' (' || t3.Year || ')' 'Population (Ending year)'
FROM Country t1
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN 1960 AND 2003) t2 ON t1.CountryID = t2.CountryID
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN 1970 AND 2013) t3 ON t1.CountryID = t3.CountryID AND t3.Year = t2.Year + 10
JOIN (SELECT * FROM Country WHERE CountryName = 'Denmark') t4
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = 1970) t5 ON t4.CountryID = t5.CountryID
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = 1980) t6 ON t4.CountryID = t6.CountryID
WHERE ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3) IS NOT NULL
ORDER BY ROUND(SQRT(POWER(t2.AvgTemp - t5.AvgTemp, 2) + POWER(t3.AvgTemp - t6.AvgTemp, 2)), 3)
LIMIT 10;

--- Similarity in temperature and population

-- Display countries
SELECT CountryName FROM Country;
-- Display countries (input: start year, time period, country name, number of most similar results)
SELECT t1.CountryName 'Country name',
t2.AvgTemp || ' (' || t2.Year || ')' 'Average temperature (Starting year)',
t3.AvgTemp || ' (' || t3.Year || ')' 'Average temperature (Ending year)',
printf('%,d', t4.Population) || ' (' || t4.Year || ')' 'Population (Starting year)',
printf('%,d', t5.Population) || ' (' || t5.Year || ')' 'Population (Ending year)'
FROM Country t1
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN 1750 AND 2003) t2 ON t1.CountryID = t2.CountryID
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year BETWEEN 1760 AND 2013) t3 ON t1.CountryID = t3.CountryID AND t3.Year = t2.Year + 10
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN 1960 AND 2003) t4 ON t1.CountryID = t4.CountryID AND t4.Year = t2.Year
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year BETWEEN 1970 AND 2013) t5 ON t1.CountryID = t5.CountryID AND t5.Year = t4.Year + 10
JOIN (SELECT * FROM Country WHERE CountryName = 'Vietnam') t6
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = 1980) t7 ON t6.CountryID = t7.CountryID
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = 1990) t8 ON t6.CountryID = t8.CountryID
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = 1980) t9 ON t7.CountryID = t9.CountryID
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = 1990) t10 ON t6.CountryID = t10.CountryID
WHERE ROUND(SQRT(POWER(t2.AvgTemp - t7.AvgTemp, 2) + POWER(t3.AvgTemp - t8.AvgTemp, 2) + POWER(t4.Population - t9.Population, 2) + POWER(t5.Population - t10.Population, 2)), 3) IS NOT NULL
ORDER BY ROUND(SQRT(POWER(t2.AvgTemp - t7.AvgTemp, 2) + POWER(t3.AvgTemp - t8.AvgTemp, 2) + POWER(t4.Population - t9.Population, 2) + POWER(t5.Population - t10.Population, 2)), 3) 
LIMIT 10;