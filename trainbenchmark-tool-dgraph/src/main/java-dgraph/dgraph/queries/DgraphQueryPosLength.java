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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphPosLengthMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class DgraphQueryPosLength<TDgraphDriver extends DgraphDriver> extends DgraphQuery<DgraphPosLengthMatch, TDgraphDriver> {

	public DgraphQueryPosLength(final TDgraphDriver driver) {
		super(RailwayQuery.POSLENGTH, driver);
	}

	@Override
	public Collection<DgraphPosLengthMatch> evaluate() {
		final Collection<DgraphPosLengthMatch> matches = new ArrayList<>();

		final Collection<Vertex> segments = driver.getVertices(SEGMENT);

		// (segment:Segment)
		for (final Vertex segment : segments) {
			final Integer length = (Integer) segment.property(LENGTH).value();

			// segment.length <= 0
			if (length <= 0) {
				matches.add(new DgraphPosLengthMatch(segment, length));
			}
		}

		return matches;
	}
}
