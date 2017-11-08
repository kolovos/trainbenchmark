MATCH
    (route:Route)-[:follows]->(swP:SwitchPosition),
    (swP)-[:target]->(sw:Switch),
    (sw)-[:monitoredBy]->(sensor:Sensor)
WHERE NOT EXISTS((route)-[:requires]->(sensor))
RETURN route, sensor, swP, sw
