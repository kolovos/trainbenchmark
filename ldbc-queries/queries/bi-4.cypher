// Q4. Popular topics in a country
/*
  :param {
    tagClass: 'MusicalArtist',
    country: 'Burma'
  }
*/
MATCH
  (city:City)-[:isPartOf]->(country {name: $country}),
  (person:Person)-[:isLocatedIn]->(city:City),
  (forum:Forum)-[:hasModerator]->(person:Person),
  (forum)-[:containerOf]->(post:Post),
  (post)-[:hasTag]->(tag:Tag),
  (tag)-[:hasType]->(tagClass:TagClass {name: $tagClass})
RETURN
  forum.id,
  forum.title,
  forum.creationDate,
  person.id,
  count(post) AS postCount
//  count(DISTINCT post) AS postCount


//ORDER BY
//  postCount DESC,
//  forum.id ASC
//LIMIT 20
