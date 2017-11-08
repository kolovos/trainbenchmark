// Q7. Most authoritative users on a given topic
/*
  :param { tag: 'Franz_Liszt' }
*/
MATCH
  (message1:Message)-[:hasTag]->(tag:Tag {name: $tag}),
  (message1)-[:hasCreator]->(person1:Person)
MATCH
  (message2:Message)-[:hasCreator]->(person1),
  (message2)-[:hasTag]->(tag),
  (person2:Person)-[:likes]->(message2),
  (message3:Message)-[:hasCreator]->(person2),
  (person3:Person)-[l:likes]->(message3)
RETURN
  person1.id,
  count(l)
//ORDER BY
//  authorityScore DESC,
//  person1.id ASC
//LIMIT 100
