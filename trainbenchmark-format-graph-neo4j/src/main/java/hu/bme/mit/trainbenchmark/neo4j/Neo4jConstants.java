
package hu.bme.mit.trainbenchmark.neo4j;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.*;

public class Neo4jConstants {

	public static final String MODEL_EXTENSION = "graphml";

	public static final Label labelRoute = Label.label(ROUTE);
	public static final Label labelSegment = Label.label(SEGMENT);
	public static final Label labelSemaphore = Label.label(SEMAPHORE);
	public static final Label labelSensor = Label.label(SENSOR);
	public static final Label labelSwitch = Label.label(SWITCH);
	public static final Label labelSwitchPosition = Label.label(SWITCHPOSITION);
	public static final Label labelTrackElement = Label.label(TRACKELEMENT);

	public static final RelationshipType relationshipTypeConnectsTo = RelationshipType.withName(CONNECTS_TO);
	public static final RelationshipType relationshipTypeEntry = RelationshipType.withName(ENTRY);
	public static final RelationshipType relationshipTypeExit = RelationshipType.withName(EXIT);
	public static final RelationshipType relationshipTypeFollows = RelationshipType.withName(FOLLOWS);
	public static final RelationshipType relationshipTypeGathers = RelationshipType.withName(GATHERS);
	public static final RelationshipType relationshipTypeMonitoredBy = RelationshipType.withName(MONITORED_BY);
	public static final RelationshipType relationshipTypeTarget = RelationshipType.withName(TARGET);

}
