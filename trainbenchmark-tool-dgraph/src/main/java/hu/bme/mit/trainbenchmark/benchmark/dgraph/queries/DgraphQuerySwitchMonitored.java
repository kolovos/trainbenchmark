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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class DgraphQuerySwitchMonitored<TDgraphDriver extends DgraphDriver> extends DgraphQuery<DgraphSwitchMonitoredMatch, TDgraphDriver> {

	public DgraphQuerySwitchMonitored(final TDgraphDriver driver) {
		super(RailwayQuery.SWITCHMONITORED, driver);
	}

	@Override
	public Collection<DgraphSwitchMonitoredMatch> evaluate() {
		final Collection<DgraphSwitchMonitoredMatch> matches = new ArrayList<>();

		final Collection<Vertex> switches = driver.getVertices(SWITCH);
		
		// (sw:Switch)
		for (final Vertex sw : switches) {
			final Iterable<Vertex> monitoredByVertices = () -> sw.vertices(Direction.OUT, MONITORED_BY);

			boolean hasSensor = false;
			for (final Vertex monitoredByVertex : monitoredByVertices) {
				if (monitoredByVertex.label().equals(SENSOR)) {
					hasSensor = true;
					break;
				}
			}

			if (!hasSensor) {
				matches.add(new DgraphSwitchMonitoredMatch(sw));
			}
		}

		return matches;
	}
}
