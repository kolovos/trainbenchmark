// Q9. Forum with related Tags
/*
  :param {
    tagClass1: 'TennisPlayer',
    tagClass2: 'President',
    threshold: 200
  }
*/
MATCH
  (forum:Forum)-[:containerOf]->(post1:Post),
  (post1)-[:hasTag]->(tag1:Tag),
  (tag1)-[:hasType]->(:TagClass {name: $tagClass1}),
  (forum)-[:containerOf]->(post2:Post),
  (post2)-[:hasTag]->(tag2:Tag),
  (tag2)-[:hasType]->(:TagClass {name: $tagClass2}),
  (forum)-[:hasMember]->(person:Person)
WITH
  forum,
  count(post1),
  count(post2),
  count(person)
WHERE members > 0
RETURN
  forum.id,
  count1,
  count2
//ORDER BY
//  abs(count2-count1) DESC,
//  forum.id ASC
//LIMIT 100
