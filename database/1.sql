/*1. Welcome*/

/*1A. Landing Page*/
SELECT *, t2.c3 - t1.c1 + 1 c5
FROM
(SELECT MIN(Year) c1, AvgTemp c2 FROM World WHERE AvgTemp IS NOT NULL) t1
JOIN
(SELECT MAX(Year) c3, AvgTemp c4 FROM World WHERE AvgTemp IS NOT NULL) t2;
 
SELECT *, t2.c3 - t1.c1 + 1 c5
FROM
(SELECT MIN(Year) c1, LandOceanAvgTemp c2 FROM World WHERE LandOceanAvgTemp IS NOT NULL) t1
JOIN
(SELECT MAX(Year) c3, LandOceanAvgTemp c4 FROM World WHERE LandOceanAvgTemp IS NOT NULL) t2;

SELECT *, t2.c3 - t1.c1 + 1 c5
FROM
(SELECT MIN(Year) c1, Population c2 FROM World WHERE Population IS NOT NULL) t1
JOIN
(SELECT MAX(Year) c3, Population c4 FROM World WHERE Population IS NOT NULL) t2;

/*1B. Mission, Our team*/

-- Personas
SELECT * FROM Persona;

-- Our team
SELECT * FROM Student;