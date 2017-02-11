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

import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.DgraphTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.Position;

public class DTransformationInjectSwitchSet<TDgraphDriver extends DgraphDriver>
		extends DgraphTransformation<DgraphSwitchSetInjectMatch, TDgraphDriver> {

	public DTransformationInjectSwitchSet(final TDgraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<DgraphSwitchSetInjectMatch> matches) {
		for (final DgraphSwitchSetInjectMatch match : matches) {
			final Vertex sw = match.getSw();
			final String currentPositionString = (String) sw.property(ModelConstants.CURRENTPOSITION).value();
			final Position currentPosition = Position.valueOf(currentPositionString);
			final Position newCurrentPosition = Position.values()[(currentPosition.ordinal() + 1) % Position.values().length];
			sw.property(ModelConstants.CURRENTPOSITION, newCurrentPosition.toString());
		}
	}

}
