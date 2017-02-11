package hu.bme.mit.trainbenchmark.benchmark.dgraph.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class DgraphBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<DgraphBenchmarkConfig, DgraphBenchmarkConfigBuilder> {

	@Override
	public DgraphBenchmarkConfig createConfig() {
		checkNotNulls();
		return new DgraphBenchmarkConfig(configBase);
	}
}
