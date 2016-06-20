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

package hu.bme.mit.trainbenchmark.benchmark.drools6.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.drools6.Drools6BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.drools6.config.Drools6BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.BaseTest;

public class Drools6Test extends BaseTest {

	@Override
	protected BenchmarkResult runTest() throws Exception {
		final Drools6BenchmarkConfigWrapper dbcw = createDrools6BenchmarkConfigWrapper(bc);
		final Drools6BenchmarkScenario scenario = new Drools6BenchmarkScenario(dbcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}
	
	protected Drools6BenchmarkConfigWrapper createDrools6BenchmarkConfigWrapper(final BenchmarkConfigCore bc) {
		return new Drools6BenchmarkConfigWrapper(bc);
	}

}
