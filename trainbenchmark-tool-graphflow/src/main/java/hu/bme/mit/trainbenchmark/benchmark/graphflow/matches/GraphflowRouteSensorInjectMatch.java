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

import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.util.Map;

public class GraphflowRouteSensorInjectMatch extends GraphflowMatch implements RouteSensorInjectMatch {

	public GraphflowRouteSensorInjectMatch(final Object[] tuple, final Map<String, Integer> columnNamesMapping) {
		super(tuple, columnNamesMapping);
	}

	@Override
	public Integer getRoute() {
		return (Integer) tuple[columnNamesMapping.get(QueryConstants.VAR_ROUTE)];
	}

	@Override
	public Integer getSensor() {
		return (Integer) tuple[columnNamesMapping.get(QueryConstants.VAR_SENSOR)];
	}

}
