/*1. Welcome*/

/*1A. Landing Page*/
SELECT *
FROM
(SELECT MIN(Year) Year1, AvgTemp AvgTemp1 FROM World WHERE AvgTemp IS NOT NULL)
JOIN
(SELECT MAX(Year) Year2, AvgTemp AvgTemp2 FROM World WHERE AvgTemp IS NOT NULL)
JOIN
(SELECT COUNT(AvgTemp) NumYear FROM World WHERE AvgTemp IS NOT NULL);

SELECT *
FROM
(SELECT MIN(Year) Year1, LandOceanAvgTemp LandOceanAvgTemp1 FROM World WHERE LandOceanAvgTemp IS NOT NULL)
JOIN
(SELECT MAX(Year) Year2, LandOceanAvgTemp LandOceanAvgTemp2 FROM World WHERE LandOceanAvgTemp IS NOT NULL)
JOIN
(SELECT COUNT(AvgTemp) NumYear FROM World WHERE LandOceanAvgTemp IS NOT NULL);

SELECT *
FROM
(SELECT MIN(Year) Year1, printf("%,d", Population) Population1 FROM World WHERE Population IS NOT NULL)
JOIN
(SELECT MAX(Year) Year2, printf("%,d", Population) Population2 FROM World WHERE Population IS NOT NULL)
JOIN
(SELECT COUNT(Population) NumYear FROM World WHERE Population IS NOT NULL);

/*1B. Mission, Our team*/

-- Personas
SELECT * FROM Persona;

-- Our team
SELECT * FROM Student;