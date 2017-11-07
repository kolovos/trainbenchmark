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
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class GraphflowDriver extends Driver {

	public GraphflowDriver() throws IOException {
		super();


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
		// TODO
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

		final ServerQueryString queryString = ServerQueryString.newBuilder().setQuery("MATCH (n) RETURN n").build();
		final QueryProcessor processor = new QueryProcessor();
		final QueryResult queryResult = processor.process(queryString);

		/**
		 * MATCH
		 *   (p1)-[K]->(p2),
		 *   (p2)-[L]->(c)
		 *
		 * MATCH
		 *   (p2)-[L]->(c),
		 *   (p1)-[K]->(p2)
		 */

		return results;
	}

	public void runTransformation(final String transformationDefinition, final Map<String, Object> parameters)
			throws IOException {
		// TODO
	}

}
