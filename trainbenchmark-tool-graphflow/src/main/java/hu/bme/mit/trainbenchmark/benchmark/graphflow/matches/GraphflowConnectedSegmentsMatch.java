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

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;

import java.util.Map;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT3;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT4;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT5;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT6;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;

public class GraphflowConnectedSegmentsMatch extends GraphflowMatch implements ConnectedSegmentsMatch {

	public GraphflowConnectedSegmentsMatch(final Object[] tuple, final Map<String, Integer> columnNamesMapping) {
		super(tuple, columnNamesMapping);
	}

	@Override
	public Integer getSensor() {
		return (Integer) tuple[columnNamesMapping.get(VAR_SENSOR)];
	}

	@Override
	public Integer getSegment1() {
		return (Integer) tuple[columnNamesMapping.get(VAR_SEGMENT1)];
	}

	@Override
	public Integer getSegment2() {
		return (Integer) tuple[columnNamesMapping.get(VAR_SEGMENT2)];
	}

	@Override
	public Integer getSegment3() {
		return (Integer) tuple[columnNamesMapping.get(VAR_SEGMENT3)];
	}

	@Override
	public Integer getSegment4() {
		return (Integer) tuple[columnNamesMapping.get(VAR_SEGMENT4)];
	}

	@Override
	public Integer getSegment5() {
		return (Integer) tuple[columnNamesMapping.get(VAR_SEGMENT5)];
	}

	@Override
	public Integer getSegment6() {
		return (Integer) tuple[columnNamesMapping.get(VAR_SEGMENT6)];
	}

}
