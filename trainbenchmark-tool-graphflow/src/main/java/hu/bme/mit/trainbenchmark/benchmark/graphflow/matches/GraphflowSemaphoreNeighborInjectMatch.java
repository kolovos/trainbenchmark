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
package hu.bme.mit.trainbenchmark.benchmark.graphflow.matches;

import ca.waterloo.dsg.graphflow.query.result.subgraph.Vertex;
import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborInjectMatch;

import java.util.Map;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;

public class GraphflowSemaphoreNeighborInjectMatch extends GraphflowMatch implements SemaphoreNeighborInjectMatch {

	public GraphflowSemaphoreNeighborInjectMatch(final Object[] tuple, final Map<String, Integer> columnNamesMapping) {
		super(tuple, columnNamesMapping);
	}

	@Override
	public Vertex getRoute() {
		return (Vertex) tuple[columnNamesMapping.get(VAR_ROUTE)];
	}

	@Override
	public Vertex getSemaphore() {
		return (Vertex) tuple[columnNamesMapping.get(VAR_SEMAPHORE)];
	}

}
