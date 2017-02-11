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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;

import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.DgraphTransformation;

public class DgraphTransformationRepairSemaphoreNeighbor<TDgraphDriver extends DgraphDriver>
		extends DgraphTransformation<DgraphSemaphoreNeighborMatch, TDgraphDriver> {

	public DgraphTransformationRepairSemaphoreNeighbor(final TDgraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<DgraphSemaphoreNeighborMatch> matches) {
		for (final DgraphSemaphoreNeighborMatch snm : matches) {
			final Vertex semaphore = snm.getSemaphore();
			final Vertex route2 = snm.getRoute2();
			route2.addEdge(ENTRY, semaphore);
		}
	}

}
