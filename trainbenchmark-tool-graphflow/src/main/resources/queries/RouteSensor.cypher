MATCH (route:Route)-[:follows]->(swP),
      (swP)-[:target]->(sw),
      (sw)-[:monitoredBy]->(sen:Sensor)
RETURN route, swP, sw, sen
