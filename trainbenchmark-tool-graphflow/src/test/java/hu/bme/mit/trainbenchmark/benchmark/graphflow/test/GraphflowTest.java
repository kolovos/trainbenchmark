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

package hu.bme.mit.trainbenchmark.benchmark.graphflow.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.GraphflowBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.config.GraphflowBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.config.GraphflowBenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.config.GraphflowQueryExecutionStrategy;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class GraphflowTest extends TrainBenchmarkTest {

	@Parameterized.Parameters(name = "queryExecutionStrategy={0}")
	public static Iterable<? extends Object> data() {
		return Arrays.asList(
//			GraphflowQueryExecutionStrategy.CONTINUOUS,
			GraphflowQueryExecutionStrategy.ONE_TIME);
	}

	@Parameterized.Parameter(value = 0)
	public GraphflowQueryExecutionStrategy queryExecutionStrategy;

	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigBase bcb) throws Exception {
		final GraphflowBenchmarkConfig bc = new GraphflowBenchmarkConfigBuilder().setConfigBase(bcb)
			.setQueryExecutionStrategy(queryExecutionStrategy).createConfig();
		final GraphflowBenchmarkScenario scenario = new GraphflowBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
