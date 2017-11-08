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
import ca.waterloo.dsg.graphflow.server.ServerQueryString;
import com.google.common.collect.ImmutableList;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GraphflowDriver extends Driver {

	protected final String modelDir;

	public GraphflowDriver(final String modelDir) throws IOException {
		super();
		this.modelDir = modelDir;
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

		final String fileFormat = modelDir + "/railway-inject-%d-%s.csv";
		final String loadVerticesFormat = "load vertices with label '%s' from csv '%s' separator ',';";
		final String loadEdgesFormat    = "load edges    with type  '%s' from csv '%s' separator ',';";

		final int size = 1;

		// TODO
		for (final String vertexLabel : vertexLabels) {
			final String filename = String.format(fileFormat, size, vertexLabel);
			final String loadCommand = String.format(loadVerticesFormat, vertexLabel, filename);
			System.out.println(loadCommand);
		}
		for (final String edgeType: edgeTypes) {
			final String filename = String.format(fileFormat, size, edgeType);
			final String loadCommand = String.format(loadEdgesFormat, edgeType, filename);
			System.out.println(loadCommand);
		}
	}

	@Override
	public String getPostfix() {
		return "cypher";
	}

	@Override
	public Number generateNewVertexId() throws Exception {
		return null;
	}

	public Collection<GraphflowMatch> runQuery(final RailwayQuery query, final String queryDefinition) throws IOException {
		final Collection<GraphflowMatch> results = new ArrayList<>();

		final ServerQueryString serverQueryString = ServerQueryString.newBuilder().setQuery(queryDefinition).build();
		final QueryProcessor processor = new QueryProcessor();
		final QueryResult queryResult = processor.process(serverQueryString);

		System.out.println(queryDefinition);
		System.out.println(queryResult.toString());

		return results;
	}

	public void runTransformation(final String transformationDefinition, final Map<String, Object> parameters)
			throws IOException {
		throw new UnsupportedOperationException("TODO: Implement transformations for Graphflow	");
	}

}
