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

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.util.DgraphUtil;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class DgraphQueryConnectedSegments<TDgraphDriver extends DgraphDriver>
		extends DgraphQuery<DgraphConnectedSegmentsMatch, TDgraphDriver> {

	public DgraphQueryConnectedSegments(final TDgraphDriver driver) {
		super(RailwayQuery.CONNECTEDSEGMENTS, driver);
	}

	@Override
	public Collection<DgraphConnectedSegmentsMatch> evaluate() throws IOException {
		final Collection<DgraphConnectedSegmentsMatch> matches = new ArrayList<>();

		final Collection<Vertex> sensors = driver.getVertices(ModelConstants.SENSOR);
		for (final Vertex sensor : sensors) {
			// (sensor:Sensor)<-[:monitoredBy]-(segment1:Segment)
			final Iterable<Vertex> segment1s = DgraphUtil.getAdjacentNodes(sensor, ModelConstants.MONITORED_BY, Direction.IN, ModelConstants.SEGMENT);
			for (final Vertex segment1 : segment1s) {
				// (segment1:Segment)-[:connectsTo]->(segment2:Segment)
				// (segment2:Segment)-[:monitoredBy]->(sensor:Sensor)
				final Iterable<Vertex> segment2s = DgraphUtil.getAdjacentNodes(segment1, ModelConstants.CONNECTS_TO, Direction.OUT,
						ModelConstants.SEGMENT);
				for (final Vertex segment2 : segment2s) {
					if (!DgraphUtil.isConnected(segment2, sensor, ModelConstants.MONITORED_BY)) {
						continue;
					}

					// (segment2:Segment)-[:connectsTo]->(segment3:Segment)
					// (segment3:Segment)-[:monitoredBy]->(sensor:Sensor)
					final Iterable<Vertex> segment3s = DgraphUtil.getAdjacentNodes(segment2, ModelConstants.CONNECTS_TO, Direction.OUT,
							ModelConstants.SEGMENT);
					for (final Vertex segment3 : segment3s) {
						if (!DgraphUtil.isConnected(segment3, sensor, ModelConstants.MONITORED_BY)) {
							continue;
						}

						// (segment3:Segment)-[:connectsTo]->(segment4:Segment)
						// (segment4:Segment)-[:monitoredBy]->(sensor:Sensor)
						final Iterable<Vertex> segment4s = DgraphUtil.getAdjacentNodes(segment3, ModelConstants.CONNECTS_TO, Direction.OUT,
								ModelConstants.SEGMENT);
						for (final Vertex segment4 : segment4s) {
							if (!DgraphUtil.isConnected(segment4, sensor, ModelConstants.MONITORED_BY)) {
								continue;
							}

							// (segment4:Segment)-[:connectsTo]->(segment5:Segment)
							// (segment5:Segment)-[:monitoredBy]->(sensor:Sensor)
							final Iterable<Vertex> segment5s = DgraphUtil.getAdjacentNodes(segment4, ModelConstants.CONNECTS_TO, Direction.OUT,
									ModelConstants.SEGMENT);
							for (final Vertex segment5 : segment5s) {
								if (!DgraphUtil.isConnected(segment5, sensor, ModelConstants.MONITORED_BY)) {
									continue;
								}

								// (segment5:Segment)-[:connectsTo]->(segment6:Segment)
								// (segment6:Segment)-[:monitoredBy]->(sensor:Sensor)
								final Iterable<Vertex> segment6s = DgraphUtil.getAdjacentNodes(segment5, ModelConstants.CONNECTS_TO, Direction.OUT,
										ModelConstants.SEGMENT);
								for (final Vertex segment6 : segment6s) {
									if (!DgraphUtil.isConnected(segment6, sensor, ModelConstants.MONITORED_BY)) {
										continue;
									}

									matches.add(new DgraphConnectedSegmentsMatch(sensor, segment1, segment2, segment3, segment4, segment5, segment6));
								}
							}
						}
					}
				}
			}
		}

		return matches;

	}

}
