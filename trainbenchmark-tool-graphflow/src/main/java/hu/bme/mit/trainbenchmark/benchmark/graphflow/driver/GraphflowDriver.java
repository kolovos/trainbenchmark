/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.graphflow.driver;

import ca.waterloo.dsg.graphflow.query.QueryProcessor;
import ca.waterloo.dsg.graphflow.query.result.QueryResult;
import ca.waterloo.dsg.graphflow.query.result.Tuples;
import ca.waterloo.dsg.graphflow.server.ServerQueryString;
import com.google.common.collect.ImmutableList;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphflowDriver extends Driver {

	protected final String modelPath;
	protected final QueryProcessor processor = new QueryProcessor();

	public GraphflowDriver(final String modelPath) throws IOException {
		super();
		this.modelPath = modelPath;
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();
	}

	@Override
	public void destroy() {
	}

	@Override
	public void read(final String modelPath) {
		final List<String> vertexLabels = ImmutableList.of(
			"Region", "Route", "Segment", "Semaphore", "Sensor", "Switch", "SwitchPosition"
		);
		final List<String> edgeTypes = ImmutableList.of(
			"connectsTo", "entry", "exit", "follows", "monitoredBy", "requires", "target"
		);

		final String fileFormat = modelPath + "-%s.csv";
		final String loadVerticesFormat = "load vertices with label '%s' from csv '%s' separator ',';";
		final String loadEdgesFormat    = "load edges    with type  '%s' from csv '%s' separator ',';";

		for (final String vertexLabel : vertexLabels) {
			final String filename = String.format(fileFormat, vertexLabel);
			final String loadCommand = String.format(loadVerticesFormat, vertexLabel, filename);
			runCypher(loadCommand);
		}
		for (final String edgeType: edgeTypes) {
			final String filename = String.format(fileFormat, edgeType);
			final String loadCommand = String.format(loadEdgesFormat, edgeType, filename);
			System.out.println(loadCommand);
			runCypher(loadCommand);
		}
	}

	/**
	 *
	 * @return empty string as CSV files have different postfixes based on their label/type
	 */
	@Override
	public String getPostfix() {
		return "";
	}

	@Override
	public Number generateNewVertexId() throws Exception {
		return null;
	}

	public Collection<GraphflowMatch> runQuery(final RailwayQuery query, final String queryDefinition) throws IOException {
		final Collection<GraphflowMatch> results = new ArrayList<>();

		// the Train Benchmark queries always return with a Tuples object
		final QueryResult queryResult = runCypher(queryDefinition);
		final Tuples tuples = (Tuples) queryResult;

		final String[] columnNames = tuples.getColumnNames();
		final List<String> columnNamesList = Arrays.asList(columnNames);
		final Map<String, Integer> columnNamesMapping = IntStream.range(0, columnNamesList.size())
			.boxed()
			.collect(Collectors.toMap(columnNamesList::get, i -> i));

		for (final Object[] tuple : tuples.getTuples()) {
			results.add(GraphflowMatch.createMatch(query, tuple, columnNamesMapping));
		}

		return results;
	}

	public void runTransformation(final String transformationDefinition, final Map<String, Object> parameters)
			throws IOException {
//		throw new UnsupportedOperationException("TODO: Implement transformations for Graphflow");
		// just NOP
	}

	public QueryResult runCypher(String cypher) {
		final ServerQueryString serverQueryString = ServerQueryString.newBuilder().setQuery(cypher).build();
		final QueryResult result = processor.process(serverQueryString);
		return result;
	}

}
