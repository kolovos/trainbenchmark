package hu.bme.mit.trainbenchmark.benchmark.graphflow.config;

import com.google.common.base.Preconditions;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class GraphflowBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<GraphflowBenchmarkConfig, GraphflowBenchmarkConfigBuilder> {

	private GraphflowQueryExecutionStrategy queryExecutionStrategy;

	public GraphflowBenchmarkConfigBuilder setQueryExecutionStrategy(GraphflowQueryExecutionStrategy queryExecutionStrategy) {
		this.queryExecutionStrategy = queryExecutionStrategy;
		return this;
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(queryExecutionStrategy);
	}

	@Override
	public GraphflowBenchmarkConfig createConfig() {
		checkNotNulls();
		return new GraphflowBenchmarkConfig(configBase, queryExecutionStrategy);
	}
}
