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
package hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.inject;

import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.DgraphTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

public class DgraphTransformationInjectConnectedSegments<TDgraphDriver extends DgraphDriver>
		extends DgraphTransformation<DgraphConnectedSegmentsInjectMatch, TDgraphDriver> {

	public DgraphTransformationInjectConnectedSegments(final TDgraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<DgraphConnectedSegmentsInjectMatch> matches) {
		for (final DgraphConnectedSegmentsInjectMatch match : matches) {
			// create (segment2) node
			final Vertex segment2 = driver.getGraph().addVertex(ModelConstants.SEGMENT);
			segment2.property(ModelConstants.LENGTH, TrainBenchmarkConstants.DEFAULT_SEGMENT_LENGTH);

			// (segment2)-[:monitoredBy]->(sensor)
			segment2.addEdge(ModelConstants.MONITORED_BY, match.getSensor());

			// (segment1)-[:connectsTo]->(segment2)
			match.getSegment1().addEdge(ModelConstants.CONNECTS_TO, segment2);
			// (segment2)-[:connectsTo]->(segment3)
			segment2.addEdge(ModelConstants.CONNECTS_TO, match.getSegment3());

			// remove (segment1)-[:connectsTo]->(segment3)
			final Iterable<Edge> connectsToEdges = () -> match.getSegment1().edges(Direction.OUT,
					ModelConstants.CONNECTS_TO);
			for (final Edge connectsToEdge : connectsToEdges) {
				if (connectsToEdge.inVertex().equals(match.getSegment3())) {
					connectsToEdge.remove();
				}
			}
		}
	}

}
