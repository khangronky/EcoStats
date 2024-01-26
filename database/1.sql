/*1. Welcome*/

/*1A. Landing Page*/
SELECT *
FROM
(SELECT MIN(Year) c1, AvgTemp c2 FROM World WHERE AvgTemp IS NOT NULL)
JOIN
(SELECT MAX(Year) c3, AvgTemp c4 FROM World WHERE AvgTemp IS NOT NULL)
JOIN
(SELECT COUNT(AvgTemp) c5 FROM World WHERE AvgTemp IS NOT NULL);

SELECT *
FROM
(SELECT MIN(Year) c1, LandOceanAvgTemp c2 FROM World WHERE LandOceanAvgTemp IS NOT NULL)
JOIN
(SELECT MAX(Year) c3, LandOceanAvgTemp c4 FROM World WHERE LandOceanAvgTemp IS NOT NULL)
JOIN
(SELECT COUNT(AvgTemp) c5 FROM World WHERE LandOceanAvgTemp IS NOT NULL);

SELECT *
FROM
(SELECT MIN(Year) c1, printf("%,d", Population) c2 FROM World WHERE Population IS NOT NULL)
JOIN
(SELECT MAX(Year) c3, printf("%,d", Population) c4 FROM World WHERE Population IS NOT NULL)
JOIN
(SELECT COUNT(Population) c5 FROM World WHERE Population IS NOT NULL);

/*1B. Mission*/

-- Personas
SELECT PID c1, Name c2, AttributeType c3, Description c4 FROM PersonaAttribute;

-- Our team
SELECT SID c1, Name c2, Email c3 FROM Student;