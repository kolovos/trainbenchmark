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
package hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.repair;

import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.DgraphTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class DgraphTransformationRepairConnectedSegments<TDgraphDriver extends DgraphDriver>
		extends DgraphTransformation<DgraphConnectedSegmentsMatch, TDgraphDriver> {

	public DgraphTransformationRepairConnectedSegments(final TDgraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<DgraphConnectedSegmentsMatch> matches) {
		for (final DgraphConnectedSegmentsMatch csm : matches) {
			// delete segment2 with all its relationships
			final Vertex segment2 = csm.getSegment2();
			segment2.remove();

			// (segment1)-[:connectsTo]->(segment3)
			csm.getSegment1().addEdge(ModelConstants.CONNECTS_TO, csm.getSegment3());
		}
	}

}
