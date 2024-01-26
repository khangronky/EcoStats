/*2. Temperature and population change*/

/*2A*/

-- Display world (input: start year, end year)
SELECT t1.AvgTemp c1,
       t2.AvgTemp c2,
       ROUND(100.0 * (t2.AvgTemp - t1.AvgTemp) / ABS(t1.AvgTemp), 3) || "%" c3,
       t1.LandOceanAvgTemp c4,
       t2.LandOceanAvgTemp c5,
       ROUND(100.0 * (t2.LandOceanAvgTemp - t1.LandOceanAvgTemp) / ABS(t1.LandOceanAvgTemp), 3) || "%" c6,
       printf("%,d", t1.Population) c7,
       printf("%,d", t2.Population) c8,
       ROUND(100.0 * (t2.Population - t1.Population) / ABS(t1.Population), 3) || "%" c9
FROM (SELECT * FROM World WHERE Year = 1990) t1
JOIN (SELECT * FROM World WHERE Year = 2005) t2;

-- Display countries (input: start year, end year, sort category, sort order)
SELECT t1.CountryName c1,
       t2.AvgTemp c2,
       t3.AvgTemp c3,
       ROUND(100.0 * (t3.AvgTemp - t2.AvgTemp) / ABS(t2.AvgTemp), 3) || "%" c4,
       printf("%,d", t4.Population) c5,
       printf("%,d", t5.Population) c6,
       ROUND(100.0 * (t5.Population - t4.Population) / ABS(t4.Population), 3) || "%" c7
FROM Country t1
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = 2000) t2 ON t1.CountryID = t2.CountryID
LEFT JOIN (SELECT * FROM CountryTemp WHERE Year = 2005) t3 ON t1.CountryID = t3.CountryID
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = 2000) t4 ON t1.CountryID = t4.CountryID
LEFT JOIN (SELECT * FROM CountryPopulation WHERE Year = 2005) t5 ON t1.CountryID = t5.CountryID
ORDER BY CountryName ASC;

/*2B*/

-- Display countries having cities
SELECT DISTINCT CountryName FROM Country JOIN City ON Country.CountryID = City.CountryID;
-- Display cities (input: start year, end year, country name)
SELECT t1.CityName c1,
       t2.AvgTemp c2,
       t3.AvgTemp c3,
       ROUND(100.0 * (t3.AvgTemp - t2.AvgTemp) / ABS(t2.AvgTemp), 3) || "% (rank " || RANK() OVER (ORDER BY ROUND(100.0 * (t3.AvgTemp - t2.AvgTemp) / ABS(t2.AvgTemp), 3) DESC) || ")" c4,
       t2.MinTemp c5,
       t3.MinTemp c6,
       ROUND(100.0 * (t3.MinTemp - t2.MinTemp) / ABS(t2.MinTemp), 3) || "% (rank " || RANK() OVER (ORDER BY ROUND(100.0 * (t3.MinTemp - t2.MinTemp) / ABS(t2.MinTemp), 3) DESC) || ")" c7,
       t2.MaxTemp c8,
       t3.MaxTemp c9,
       ROUND(100.0 * (t3.MaxTemp - t2.MaxTemp) / ABS(t2.MaxTemp), 3) || "% (rank " || RANK() OVER (ORDER BY ROUND(100.0 * (t3.MaxTemp - t2.MaxTemp) / ABS(t2.MaxTemp), 3) DESC) || ")" c10 
FROM (SELECT * FROM Country JOIN City ON Country.CountryID = City.CountryID WHERE CountryName = "Vietnam") t1
LEFT JOIN (SELECT * FROM CityTemp WHERE Year = 1950) t2 ON t1.CityID = t2.CityID
LEFT JOIN (SELECT * FROM CityTemp WHERE Year = 2010) t3 ON t1.CityID = t3.CityID
ORDER BY CityName ASC;

-- Display countries having states
SELECT DISTINCT CountryName FROM Country JOIN State ON Country.CountryID = State.CountryID;
-- Display states (input: start year, end year, country name)
SELECT t1.StateName c1,
       t2.AvgTemp c2,
       t3.AvgTemp c3,
       ROUND(100.0 * (t3.AvgTemp - t2.AvgTemp) / ABS(t2.AvgTemp), 3) || "% (rank " || RANK() OVER (ORDER BY ROUND(100.0 * (t3.AvgTemp - t2.AvgTemp) / ABS(t2.AvgTemp), 3) DESC) || ")" c4,
       t2.MinTemp c5,
       t3.MinTemp c6,
       ROUND(100.0 * (t3.MinTemp - t2.MinTemp) / ABS(t2.MinTemp), 3) || "% (rank " || RANK() OVER (ORDER BY ROUND(100.0 * (t3.MinTemp - t2.MinTemp) / ABS(t2.MinTemp), 3) DESC) || ")" c7,
       t2.MaxTemp c8,
       t3.MaxTemp c9,
       ROUND(100.0 * (t3.MaxTemp - t2.MaxTemp) / ABS(t2.MaxTemp), 3) || "% (rank " || RANK() OVER (ORDER BY ROUND(100.0 * (t3.MaxTemp - t2.MaxTemp) / ABS(t2.MaxTemp), 3) DESC) || ")" c10 
FROM (SELECT * FROM Country JOIN State ON Country.CountryID = State.CountryID WHERE CountryName = "Brazil") t1
LEFT JOIN (SELECT * FROM StateTemp WHERE Year = 1950) t2 ON t1.StateID = t2.StateID
LEFT JOIN (SELECT * FROM StateTemp WHERE Year = 2010) t3 ON t1.StateID = t3.StateID
ORDER BY StateName ASC;