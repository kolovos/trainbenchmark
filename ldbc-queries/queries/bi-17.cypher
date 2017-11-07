// Q17. Friend triangles
/*
  :param { country: 'Spain' }
*/
MATCH (country:Country {name: $country})
MATCH
  (a:Person)-[:isLocatedIn]->(cityA:City)
  (cityA)-[:isPartOf]->(country)
MATCH
  (b:Person)-[:isLocatedIn]->(cityB:City),
  (cityB)-[:isPartOf]->(country)
MATCH
  (c:Person)-[:isLocatedIn]->(cityC:City),
  (cityC)-[:isPartOf]->(country)
MATCH
  (a)-[:knows]-(b),
  (b)-[:knows]-(c),
  (c)-[:knows]-(a)
WHERE a.id < b.id
  AND b.id < c.id
RETURN count(*)
// as a less elegant solution, count(a) also works
