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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;

import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.DgraphTransformation;

public class DgraphTransformationRepairPosLength<TDgraphDriver extends DgraphDriver>
		extends DgraphTransformation<DgraphPosLengthMatch, TDgraphDriver> {

	public DgraphTransformationRepairPosLength(final TDgraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<DgraphPosLengthMatch> matches) {
		for (final DgraphPosLengthMatch plm : matches) {
			final Vertex segment = plm.getSegment();
			final Integer length = plm.getLength();
			try {
				segment.property(LENGTH, -length + 1);
			} catch (final IllegalStateException e) {
				// do nothing (vertex has been removed)
			}
		}
	}

}
