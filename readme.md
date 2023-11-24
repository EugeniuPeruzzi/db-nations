### Adding sql string from milestone 1

```sql
SELECT countries.name AS caontry_name, regions.name AS regions_name , continents.name AS continents_name
FROM countries
JOIN regions ON countries.region_id = regions.region_id
JOIN continents ON regions.continent_id = continents.continent_id
ORDER BY countries.name;
```