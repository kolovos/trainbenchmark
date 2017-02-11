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
package hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.inject;

import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.DgraphTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class DTransformationInjectSwitchMonitored<TDgraphDriver extends DgraphDriver>
		extends DgraphTransformation<DgraphSwitchMonitoredInjectMatch, TDgraphDriver> {

	public DTransformationInjectSwitchMonitored(final TDgraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<DgraphSwitchMonitoredInjectMatch> matches) {
		for (final DgraphSwitchMonitoredInjectMatch match : matches) {
			final Iterable<Edge> monitoredBys = () -> match.getSw().edges(Direction.OUT, ModelConstants.MONITORED_BY);
			for (final Edge monitoredBy : monitoredBys) {
				monitoredBy.remove();
			}
		}
	}

}
