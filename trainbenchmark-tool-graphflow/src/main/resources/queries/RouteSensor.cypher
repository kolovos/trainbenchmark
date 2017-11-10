MATCH (route:Route)-[:follows]->(swP:SwitchPosition),
      (swP)-[:target]->(sw:Switch),
      (sw)-[:monitoredBy]->(sen:Sensor)
RETURN route, swP, sw, sen
