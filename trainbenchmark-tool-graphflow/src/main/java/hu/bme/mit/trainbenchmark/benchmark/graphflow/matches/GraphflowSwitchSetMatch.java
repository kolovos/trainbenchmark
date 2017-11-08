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

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.util.Map;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;

public class GraphflowSwitchSetMatch extends GraphflowMatch implements SwitchSetMatch {

	public GraphflowSwitchSetMatch(final Object[] tuple, final Map<String, Integer> columnNamesMapping) {
		super(tuple, columnNamesMapping);
	}

	@Override
	public Integer getSemaphore() {
		return (Integer) tuple[columnNamesMapping.get(VAR_SEMAPHORE)];
	}

	@Override
	public Integer getRoute() {
		return (Integer) tuple[columnNamesMapping.get(VAR_ROUTE)];
	}

	@Override
	public Integer getSwP() {
		return (Integer) tuple[columnNamesMapping.get(VAR_SWP)];
	}

	@Override
	public Integer getSw() {
		return (Integer) tuple[columnNamesMapping.get(VAR_SW)];
	}

	public String getCurrentPosition() {
		return (String) tuple[columnNamesMapping.get(QueryConstants.VAR_CURRENTPOSITION)];
	}

	public String getPosition() {
		return (String) tuple[columnNamesMapping.get(QueryConstants.VAR_POSITION)];
	}

}
