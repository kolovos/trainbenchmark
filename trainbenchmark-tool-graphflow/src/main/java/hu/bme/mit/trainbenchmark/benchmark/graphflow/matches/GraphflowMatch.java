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

import hu.bme.mit.trainbenchmark.benchmark.matches.BaseMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Arrays;
import java.util.Map;

public abstract class GraphflowMatch extends BaseMatch {

	protected final Object[] tuple;
	protected final Map<String, Integer> columnNamesMapping;

	public GraphflowMatch(final Object[] tuple, Map<String, Integer> columnNamesMapping) {
		this.tuple = tuple;
		this.columnNamesMapping = columnNamesMapping;
	}

	@Override
	public String toString() {
		return "GraphflowMatch [match=" + Arrays.toString(toArray()) + "]";
	}

	public static GraphflowMatch createMatch(final RailwayQuery query, final Object[] tuple, final Map<String, Integer> columnNamesMapping) {
		switch (query) {
			case CONNECTEDSEGMENTS:
				return new GraphflowConnectedSegmentsMatch(tuple, columnNamesMapping);
			case CONNECTEDSEGMENTS_INJECT:
				return new GraphflowConnectedSegmentsInjectMatch(tuple, columnNamesMapping);
			case POSLENGTH:
				return new GraphflowPosLengthMatch(tuple, columnNamesMapping);
			case POSLENGTH_INJECT:
				return new GraphflowPosLengthInjectMatch(tuple, columnNamesMapping);
			case ROUTESENSOR:
				return new GraphflowRouteSensorMatch(tuple, columnNamesMapping);
			case ROUTESENSOR_INJECT:
				return new GraphflowRouteSensorInjectMatch(tuple, columnNamesMapping);
			case SEMAPHORENEIGHBOR:
				return new GraphflowSemaphoreNeighborMatch(tuple, columnNamesMapping);
			case SEMAPHORENEIGHBOR_INJECT:
				return new GraphflowSemaphoreNeighborInjectMatch(tuple, columnNamesMapping);
			case SWITCHMONITORED:
				return new GraphflowSwitchMonitoredMatch(tuple, columnNamesMapping);
			case SWITCHMONITORED_INJECT:
				return new GraphflowSwitchMonitoredInjectMatch(tuple, columnNamesMapping);
			case SWITCHSET:
				return new GraphflowSwitchSetMatch(tuple, columnNamesMapping);
			case SWITCHSET_INJECT:
				return new GraphflowSwitchSetInjectMatch(tuple, columnNamesMapping);
			default:
				throw new UnsupportedOperationException("Query not supported: " + query);
		}
	}

}
