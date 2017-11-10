CONTINUOUSLY MATCH
  (route:Route)-[:entry]->(semaphore:Semaphore),
  (route)-[:follows]->(swP:SwitchPosition),
  (swP)-[:target]->(sw:Switch)
WHERE semaphore.signal = "GO"
  AND route.active = true
  AND sw.currentPosition <> swP.position
FILE 'results.out';
