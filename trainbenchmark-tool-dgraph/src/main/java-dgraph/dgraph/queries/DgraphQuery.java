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

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class DgraphQuery<TMatch extends DgraphMatch, TDgraphDriver extends DgraphDriver> extends ModelQuery<TMatch, TDgraphDriver> {

	public DgraphQuery(final RailwayQuery query, final TDgraphDriver driver) {
		super(query, driver);
	}

	public static <TDgraphDriver extends DgraphDriver> DgraphQuery<?, TDgraphDriver> newInstance(final RailwayQuery query, final TDgraphDriver driver) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new DgraphQueryConnectedSegments<>(driver);
		case CONNECTEDSEGMENTS_INJECT:
			return new DgraphQueryConnectedSegmentsInject<>(driver);
		case POSLENGTH:
			return new DgraphQueryPosLength<>(driver);
		case POSLENGTH_INJECT:
			return new DgraphQueryPosLengthInject<>(driver);
		case ROUTESENSOR:
			return new DgraphQueryRouteSensor<>(driver);
		case ROUTESENSOR_INJECT:
			return new DgraphQueryRouteSensorInject<>(driver);
		case SEMAPHORENEIGHBOR:
			return new DgraphQuerySemaphoreNeighbor<>(driver);
		case SEMAPHORENEIGHBOR_INJECT:
			return new DgraphQuerySemaphoreNeighborInject<>(driver);
		case SWITCHMONITORED:
			return new DgraphQuerySwitchMonitored<>(driver);
		case SWITCHMONITORED_INJECT:
			return new DgraphQuerySwitchMonitoredInject<>(driver);
		case SWITCHSET:
			return new DgraphQuerySwitchSet<>(driver);
		case SWITCHSET_INJECT:
			return new DgraphQuerySwitchSetInject<>(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
