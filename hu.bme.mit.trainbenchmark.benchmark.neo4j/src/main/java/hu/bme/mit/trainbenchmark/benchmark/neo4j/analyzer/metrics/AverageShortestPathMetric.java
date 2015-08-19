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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.Metric;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;

import java.util.ArrayList;
import java.util.List;

public class AverageShortestPathMetric extends Metric {

	private List<Integer> shortestPaths;

	private int maxDepth;

	public AverageShortestPathMetric() {
		super(EdgeDirection.OUTGOING);
		shortestPaths = new ArrayList<Integer>();
		maxDepth = 50;
	}

	@Override
	public void calculate() {
		if (shortestPaths.size() == 0) {
			metricValue = 0;
			return;
		}
		int sum = 0;
		for (Integer distance : shortestPaths) {
			sum += distance;
		}
		metricValue = sum / shortestPaths.size();

	}

	public boolean add(Integer e) {
		return shortestPaths.add(e);
	}

	public void clear() {
		shortestPaths.clear();
		metricValue = 0;
	}

	@Override
	protected String getIdentifier() {
		return "AvgShortestPath";
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

}