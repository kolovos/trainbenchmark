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

import java.util.ArrayList;
import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.util.DgraphUtil;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class DgraphQueryRouteSensor<TDgraphDriver extends DgraphDriver> extends DgraphQuery<DgraphRouteSensorMatch, TDgraphDriver> {

	public DgraphQueryRouteSensor(final TDgraphDriver driver) {
		super(RailwayQuery.ROUTESENSOR, driver);
	}

	@Override
	public Collection<DgraphRouteSensorMatch> evaluate() {
		final Collection<DgraphRouteSensorMatch> matches = new ArrayList<>();

		final Collection<Vertex> routes = driver.getVertices(ModelConstants.ROUTE);
		for (final Vertex route : routes) {
			// (route:Route)-[:follows]->(swP:switchPosition)
			final Iterable<Vertex> swPs = DgraphUtil.getAdjacentNodes(route, ModelConstants.FOLLOWS, Direction.OUT, ModelConstants.SWITCHPOSITION);
			for (final Vertex swP : swPs) {
				// (swP:switchPosition)-[:target]->(sw:Switch)
				final Iterable<Vertex> sws = DgraphUtil.getAdjacentNodes(swP, ModelConstants.TARGET, Direction.OUT, ModelConstants.SWITCH);
				for (final Vertex sw : sws) {
					// (sw:Switch)-[:sensor]->(sensor:Sensor)
					final Iterable<Vertex> sensors = DgraphUtil.getAdjacentNodes(sw, ModelConstants.MONITORED_BY, Direction.OUT, ModelConstants.SENSOR);
					for (final Vertex sensor : sensors) {
						// (sensor:Sensor)<-[:requires]-(route:Route) NAC
						if (!DgraphUtil.isConnected(route, sensor, ModelConstants.REQUIRES)) {
							matches.add(new DgraphRouteSensorMatch(route, sensor, swP, sw));
						}
					}
				}
			}
		}

		return matches;
	}
}
