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
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.DgraphTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class DgraphTransformationInjectRouteSensor<TDgraphDriver extends DgraphDriver>
		extends DgraphTransformation<DgraphRouteSensorInjectMatch, TDgraphDriver> {

	public DgraphTransformationInjectRouteSensor(final TDgraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<DgraphRouteSensorInjectMatch> matches) {
		for (final DgraphRouteSensorInjectMatch match : matches) {
			final Iterable<Edge> requiress = () -> match.getRoute().edges(Direction.OUT, ModelConstants.REQUIRES);
			for (final Edge requires : requiress) {
				if (requires.inVertex().equals(match.getSensor())) {
					requires.remove();
				}
			}
		}
	}

}
