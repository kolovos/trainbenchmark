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
package hu.bme.mit.trainbenchmark.benchmark.graphflow.queries;

import hu.bme.mit.trainbenchmark.benchmark.graphflow.GraphflowConstants;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.driver.GraphflowDriver;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class GraphflowQuery<TGraphflowMatch extends GraphflowMatch> extends ModelQuery<TGraphflowMatch, GraphflowDriver> {

	protected final RailwayQuery query;
	protected final String queryDefinition;

	public GraphflowQuery(final GraphflowDriver driver, final String workspaceDir, final RailwayQuery query)
			throws IOException {
		super(query, driver);

		this.query = query;
		this.queryDefinition = FileUtils.readFileToString(new File(
				workspaceDir + GraphflowConstants.CYPHER_DIR + "queries/" + query + "." + GraphflowConstants.QUERY_EXTENSION));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TGraphflowMatch> evaluate() throws IOException {
		return (Collection<TGraphflowMatch>) driver.runQuery(query, queryDefinition);
	}

}
