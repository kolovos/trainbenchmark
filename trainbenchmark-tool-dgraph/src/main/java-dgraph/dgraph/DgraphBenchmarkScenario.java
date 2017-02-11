package hu.bme.mit.trainbenchmark.benchmark.dgraph;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.comparators.DgraphMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.config.DgraphBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.operations.DgraphModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class DgraphBenchmarkScenario
		extends BenchmarkScenario<DgraphMatch, DgraphDriver, DgraphBenchmarkConfig> {

	public DgraphBenchmarkScenario(final DgraphBenchmarkConfig bc) throws Exception {
		super(new DgraphDriverFactory(), new DgraphModelOperationFactory<DgraphDriver>(), new DgraphMatchComparator(), bc);
	}

}
