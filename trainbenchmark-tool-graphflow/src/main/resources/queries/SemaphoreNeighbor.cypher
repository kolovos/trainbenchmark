MATCH
  (route1:Route)-[:exit]->(semaphore:Semaphore),
  (route1)-[:requires]->(sensor1:Sensor),
  (te1)-[:monitoredBy]->(sensor1),
  (te1)-[:connectsTo]->(te2),
  (te2)-[:monitoredBy]->(sensor2:Sensor),
  (route2:Route)-[:requires]->(sensor2)
WHERE NOT EXISTS((route2)-[:entry]->(semaphore))
  AND route1 <> route2
RETURN semaphore, route1, route2, sensor1, sensor2, te1, te2
