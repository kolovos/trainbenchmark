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
package hu.bme.mit.trainbenchmark.benchmark.dgraph.queries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.util.DgraphUtil;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class DgraphQueryConnectedSegmentsInject<TDgraphDriver extends DgraphDriver>
		extends DgraphQuery<DgraphConnectedSegmentsInjectMatch, TDgraphDriver> {

	public DgraphQueryConnectedSegmentsInject(final TDgraphDriver driver) {
		super(RailwayQuery.CONNECTEDSEGMENTS_INJECT, driver);
	}

	@Override
	public Collection<DgraphConnectedSegmentsInjectMatch> evaluate() throws IOException {
		final Collection<DgraphConnectedSegmentsInjectMatch> matches = new ArrayList<>();

		final Collection<Vertex> sensors = driver.getVertices(ModelConstants.SENSOR);
		for (final Vertex sensor : sensors) {
			// (sensor:Sensor)<-[:sensor]-(segment1:Segment)
			final Iterable<Vertex> segment1s = DgraphUtil.getAdjacentNodes(sensor, ModelConstants.MONITORED_BY, Direction.IN, ModelConstants.SEGMENT);
			for (final Vertex segment1 : segment1s) {
				// (segment1:Segment)-[:connectsTo]->(segment3:Segment)
				// (segment3:Segment)-[:monitoredBy]->(sensor:Sensor)
				final Iterable<Vertex> segment3s = DgraphUtil.getAdjacentNodes(segment1, ModelConstants.CONNECTS_TO, Direction.OUT,
						ModelConstants.SEGMENT);
				for (final Vertex segment3 : segment3s) {
					if (!DgraphUtil.isConnected(segment3, sensor, ModelConstants.MONITORED_BY)) {
						continue;
					}

					final Map<String, Object> match = new HashMap<>();
					match.put(QueryConstants.VAR_SENSOR, sensor);
					match.put(QueryConstants.VAR_SEGMENT1, segment1);
					match.put(QueryConstants.VAR_SEGMENT3, segment3);
					matches.add(new DgraphConnectedSegmentsInjectMatch(sensor, segment1, segment3));
				}
			}
		}
		return matches;
	}

}
