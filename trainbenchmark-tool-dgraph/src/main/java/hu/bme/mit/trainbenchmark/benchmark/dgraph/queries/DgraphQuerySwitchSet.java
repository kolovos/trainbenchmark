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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.util.DgraphUtil;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.Signal;

public class DgraphQuerySwitchSet<TDgraphDriver extends DgraphDriver> extends DgraphQuery<DgraphSwitchSetMatch, TDgraphDriver> {

	public DgraphQuerySwitchSet(final TDgraphDriver driver) {
		super(RailwayQuery.SWITCHSET, driver);
	}

	@Override
	public Collection<DgraphSwitchSetMatch> evaluate() {
		final Collection<DgraphSwitchSetMatch> matches = new ArrayList<>();

		final Collection<Vertex> routes = driver.getVertices(ROUTE);
		// (route:Route)
		for (final Vertex route : routes) {
			// (route:Route)-[:entry]->(semaphore:Semaphore)
			final Iterable<Vertex> semaphores = DgraphUtil.getAdjacentNodes(route, ENTRY, Direction.OUT, SEMAPHORE);

			for (final Vertex semaphore : semaphores) {
				// semaphore.signal = "GO"
				final Object signal = semaphore.property(SIGNAL).value();
				if (!Signal.GO.toString().equals(signal)) {
					continue;
				}
				// (route:Route)-[:follows]->(swP:SwitchPosition)
				final Iterable<Vertex> swPs = DgraphUtil.getAdjacentNodes(route, ModelConstants.FOLLOWS, Direction.OUT, SWITCHPOSITION);
				for (final Vertex swP : swPs) {
					// (swP:SwitchPosition)-[:target]->(sw:Switch)
					final Iterable<Vertex> sws = DgraphUtil.getAdjacentNodes(swP, ModelConstants.TARGET, Direction.OUT, SWITCH);

					for (final Vertex sw : sws) {
						final String currentPosition = (String) sw.property(ModelConstants.CURRENTPOSITION).value();
						final String position = (String) swP.property(ModelConstants.POSITION).value();

						if (!currentPosition.equals(position)) {
							matches.add(new DgraphSwitchSetMatch(semaphore, route, swP, sw, position, currentPosition));
							break;
						}
					}
				}
			}
		}

		return matches;
	}
}
