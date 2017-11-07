// Q11. Unrelated replies
// blacklists are omitted for now
/*
  :param {
    country: 'Pakistan'
  }
*/
MATCH
  (city:City)-[:isPartOf]->(country:Country {name: $country}),
  (person:Person)-[:isLocatedIn]->(city),
  (message:Message)-[:hasCreator]->(person),
  (reply:Comment)-[:replyOf]->(message),
  (message)-[:hasTag]->(tag:Tag),
  (fan:Person)-[:likes]->(reply)
WHERE NOT (reply)-[:hasTag]->(tag)
RETURN
  person.id,
  tag.name,
  count(fan) AS countLikes,
  count(reply) AS countReplies,
  reply.content
//ORDER BY
//  countLikes DESC,
//  person.id ASC,
//  tag.name ASC
//LIMIT 100
