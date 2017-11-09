MATCH
  (segment1:Segment)-[:monitoredBy]->(sensor:Sensor),
  (segment1:Segment)-[:connectsTo]->(segment2),
  (segment2:Segment)-[:connectsTo]->(segment3),
  (segment3:Segment)-[:connectsTo]->(segment4),
  (segment4:Segment)-[:connectsTo]->(segment5),
  (segment5)-[:connectsTo]->(segment6:Segment),
  (segment2)-[:monitoredBy]->(sensor)
//  (segment3)-[:monitoredBy]->(sensor)
//  (segment4)-[:monitoredBy]->(sensor),
//  (segment5)-[:monitoredBy]->(sensor),
//  (segment6)-[:monitoredBy]->(sensor)
RETURN sensor, segment1, segment2, segment3, segment4, segment5, segment6
