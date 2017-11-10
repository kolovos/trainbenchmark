CONTINUOUSLY MATCH
  (route:Route)-[:follows]->(swP:SwitchPosition),
  (swP)-[:target]->(sw:switch),
  (sw)-[:monitoredBy]->(sen:Sensor)
FILE 'results.out';
