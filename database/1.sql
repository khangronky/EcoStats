--- 1. Welcome

--- 1A. Landing Page
SELECT *
FROM (SELECT MIN(Year), LandOceanAvgTemp FROM World WHERE LandOceanAvgTemp IS NOT NULL)
JOIN (SELECT MAX(Year), LandOceanAvgTemp FROM World WHERE LandOceanAvgTemp IS NOT NULL)
JOIN (SELECT COUNT(LandOceanAvgTemp) FROM World WHERE LandOceanAvgTemp IS NOT NULL);

SELECT *
FROM (SELECT MIN(Year), printf('%,d', Population) FROM World WHERE Population IS NOT NULL)
JOIN (SELECT MAX(Year), printf('%,d', Population) FROM World WHERE Population IS NOT NULL)
JOIN (SELECT COUNT(Population) FROM World WHERE Population IS NOT NULL);

--- 1B. Mission

-- Personas
SELECT ImgLink, Name, AttributeType, Description 
FROM Persona
JOIN PersonaAttribute ON Persona.Name = PersonaAttribute.Name;

-- Our team
SELECT * FROM Student;