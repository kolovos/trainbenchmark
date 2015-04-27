/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.sesame.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE2;
import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborMatch;

import org.openrdf.model.Resource;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;

public class SesameSemaphoreNeighborMatch extends SesameMatch implements SemaphoreNeighborMatch {

	public SesameSemaphoreNeighborMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public Resource getSemaphore() {
		return (Resource) bs.getValue(VAR_SEMAPHORE);
	}

	@Override
	public Resource getRoute1() {
		return (Resource) bs.getValue(VAR_ROUTE1);
	}

	@Override
	public Resource getRoute2() {
		return (Resource) bs.getValue(VAR_ROUTE2);
	}

	@Override
	public Resource getSensor1() {
		return (Resource) bs.getValue(VAR_SENSOR1);
	}

	@Override
	public Resource getSensor2() {
		return (Resource) bs.getValue(VAR_SENSOR2);
	}

	@Override
	public Resource getTe1() {
		return (Resource) bs.getValue(VAR_TE1);
	}

	@Override
	public Resource getTe2() {
		return (Resource) bs.getValue(VAR_TE2);
	}

	@Override
	public Value[] toArray() {
		return new Value[] { getSemaphore(), getRoute1(), getRoute2(), getSensor1(), getSensor2(), getTe1(), getTe2() };
	}

}
