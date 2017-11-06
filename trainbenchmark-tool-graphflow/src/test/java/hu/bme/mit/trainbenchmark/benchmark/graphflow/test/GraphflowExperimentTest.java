//package hu.bme.mit.trainbenchmark.benchmark.graphflow.test;
//
//
//import ca.waterloo.dsg.graphflow.graph.GraphDBState;
//import ca.waterloo.dsg.graphflow.query.operator.InMemoryOutputSink;
//import ca.waterloo.dsg.graphflow.query.output.MatchQueryOutput;
//import ca.waterloo.dsg.graphflow.query.parser.StructuredQueryParser;
//import ca.waterloo.dsg.graphflow.query.planner.CreateQueryPlanner;
//import ca.waterloo.dsg.graphflow.query.planner.OneTimeMatchQueryPlanner;
//import ca.waterloo.dsg.graphflow.query.plans.OneTimeMatchQueryPlan;
//import ca.waterloo.dsg.graphflow.query.structuredquery.StructuredQuery;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.Arrays;
//import java.util.List;
//import java.util.StringJoiner;
//
///**
// * End-to-end tests of the different types of filter queries. Each query matches a triangle
// * pattern against the following graph.
// * <ul>
// * <li>Vertex IDs: 0, 1, 3, 4 , 5</li>
// * <li>Each vertex is of type PERSON and has properties name(string), age(int), views(int).</li>
// * <li>Each vertex is of type FOLLOWS and has the property views(int).</li>
// * </ul>
// * Edges: Form 3 interconnected triangles
// * <ul>
// * <li>0 -> 1, 1 -> 3, 3 -> 0</li>
// * <li>3 -> 4, 4 -> 1, 1 -> 3</li>
// * <li>4 -> 1, 1 -> 5, 5 -> 4</li>
// * </ul>
// */
//public class GraphflowExperimentTest {
//
//	@Before
//	public void setUp() {
//		GraphDBState.reset();
//		constructGraph();
//	}
//
//	@Test
//	public void testSwitchSet(){
//
//		System.out.println("\n==========SWITCH_SET==========");
//
//		String matchQuery = "MATCH (route:Route)-[:entry]->(semaphore:Semaphore), " +
//			"(route)-[:follows]->(swP:SwitchPosition), " +
//			"(swP:SwitchPosition)-[:target]->(sw:Switch) " +
//			"WHERE semaphore.signal = \"GO\" " +
//			"AND route.active = true " +
//			"AND sw.currentPosition <> swP.position " +
//			"RETURN semaphore, route, swP, sw, sw.currentPosition, swP.position";
//
//		run(matchQuery);
//	}
//
//	@Test
//	public void testSwitchSetInject(){
//
//		System.out.println("\n==========SWITCH_INJECT==========");
//
//		String matchQuery = "MATCH (sw:Switch)->(ignoreme) RETURN sw";
//
//		run(matchQuery);
//	}
//
//	@Test
//	public void testSwitchMonitoredInject(){
//
//		System.out.println("\n==========SWITCH_MONITORED_INJECT==========");
//
//		String matchQuery = "MATCH (sw:Switch)->(ignoreme) RETURN sw";
//
//		run(matchQuery);
//	}
//
//	@Test
//	public void testSwitchMonitored(){
//
//		System.out.println("\n==========SWITCH_MONITORED==========");
//
//		String matchQuery = "MATCH (sw:Switch)-[:monitoredBy]->(ignoreme) RETURN sw";
//
//		run(matchQuery);
//	}
//
//	@Test
//	public void testSemaphoreNeighborInject(){
//
//		System.out.println("\n==========SEMAPHORENEIGHBOR_INJECT==========");
//
//		String matchQuery = "MATCH (route:Route)-[:entry]->(semaphore:Semaphore) RETURN route, semaphore";
//
//		run(matchQuery);
//	}
//
//
////	MATCH
////		(semaphore:Semaphore)<-[:exit]-(route1:Route)-[:requires]->(sensor1:Sensor),
////		(sensor1)<-[:monitoredBy]-(te1)-[:connectsTo]->(te2)-[:monitoredBy]->(sensor2:Sensor)<-[:requires]-(route2:Route)
////	WHERE NOT (semaphore)<-[:entry]-(route2)
////	AND route1 <> route2
////	RETURN DISTINCT semaphore, route1, route2, sensor1, sensor2, te1, te2
//
//	@Test
//	public void testSemaphoreNeighbor(){
//
//		System.out.println("\n==========SWITCH_INJECT==========");
//
//		// note that Graphflow only supports a single label for each node
//		String matchQuery = "MATCH (route1:Route)-[:exit]->(semaphore:Semaphore), " +
//			"(route1)-[:requires]->(sensor1:Sensor), " +
//			"(te1)-[:monitoredBy]->(sensor1) " + // this already causes 0 results
////			"(te1)-[:connectsTo]->(te2)," +
////			"(te2)-[:monitoredBy]->(sensor2:Sensor)," +
////			"(route2:Route)-[:requires]->(sensor2) " +
////			"WHERE NOT (route2)-[:entry]->(semaphore) " +
////			"AND route1.id <> route2.id " + // Graphflow cannot compare whole nodes and id attributes are not loaded yet
////			"RETURN semaphore, route1, route2, sensor1, sensor2, te1, te2;";
//            "RETURN route1, semaphore";
//
//		System.out.println(matchQuery);
//
//		run(matchQuery);
//	}
//
//	private List<String> run(String query) {
//		InMemoryOutputSink inMemoryOutputSink = new InMemoryOutputSink();
//		((OneTimeMatchQueryPlan) new OneTimeMatchQueryPlanner(new StructuredQueryParser().parse(
//			query), inMemoryOutputSink).plan()).execute();
//
//        System.out.println("RESULT: " + inMemoryOutputSink.getResults().size());
//        System.out.println("  " + inMemoryOutputSink.getResults());
//        return inMemoryOutputSink.getResults();
//	}
//
//	private void runTest(String query, Object[][] expectedResults) {
//		InMemoryOutputSink inMemoryOutputSink = new InMemoryOutputSink();
//		((OneTimeMatchQueryPlan) new OneTimeMatchQueryPlanner(new StructuredQueryParser().parse(
//			query), inMemoryOutputSink).plan()).execute();
//		InMemoryOutputSink expectedResultOutputSink = getInMemoryOutputSinkForMotifs(
//			expectedResults);
//		Assert.assertArrayEquals(expectedResultOutputSink.getResults().toArray(),
//			inMemoryOutputSink.getResults().toArray());
//	}
//
//	public static void initializeGraphPermanentlyWithProperties(String createQuery) {
//		StructuredQuery structuredQuery = new StructuredQueryParser().parse(createQuery);
//		new CreateQueryPlanner(structuredQuery).plan().execute();
//	}
//
//	public static InMemoryOutputSink getInMemoryOutputSinkForMotifs(Object[][] results) {
//		MatchQueryOutput.MatchQueryResultType[] matchQueryResultTypes = new MatchQueryOutput.MatchQueryResultType[results.length];
//		Arrays.fill(matchQueryResultTypes, MatchQueryOutput.MatchQueryResultType.MATCHED);
//		return getInMemoryOutputSinkForMotifs(results, matchQueryResultTypes);
//	}
//
//	public static InMemoryOutputSink getInMemoryOutputSinkForMotifs(Object[][] results,
//																	MatchQueryOutput.MatchQueryResultType[] matchQueryResultTypes) {
//		InMemoryOutputSink inMemoryOutputSink = new InMemoryOutputSink();
//		StringJoiner joiner;
//		for (int i = 0; i < results.length; i++) {
//			joiner = new StringJoiner(" ");
//			for (Object element : results[i]) {
//				joiner.add(element.toString());
//			}
//			inMemoryOutputSink.append(joiner.toString());
//		}
//		return inMemoryOutputSink;
//	}
//
//	private void constructGraph() {
//		final File cypherFile = new File("../models/railway-repair-1.graphflow");
//
//		try {
//			BufferedReader bufferedReader = new BufferedReader(new FileReader(cypherFile));
//			String line;
//
//			while ((line = bufferedReader.readLine()) != null) {
//				new CreateQueryPlanner(new StructuredQueryParser().parse(line)).plan().execute();
//			}
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//	}
//}
