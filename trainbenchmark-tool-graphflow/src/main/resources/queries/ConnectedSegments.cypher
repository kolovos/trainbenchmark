MATCH
  (segment1:Segment)-[:monitoredBy]->(sensor:Sensor),
  (segment1:Segment)-[:connectsTo]->(segment2),
  (segment2:Segment)-[:connectsTo]->(segment3),
  (segment3:Segment)-[:connectsTo]->(segment4),
  (segment4:Segment)-[:connectsTo]->(segment5),
  (segment5:Segment)-[:connectsTo]->(segment6:Segment),
  (segment2:Segment)-[:monitoredBy]->(sensor:Sensor),
  (segment3:Segment)-[:monitoredBy]->(sensor:Sensor),
  (segment4:Segment)-[:monitoredBy]->(sensor:Sensor),
  (segment5:Segment)-[:monitoredBy]->(sensor:Sensor),
  (segment6:Segment)-[:monitoredBy]->(sensor:Sensor)
RETURN sensor, segment1, segment2, segment3, segment4, segment5, segment6
