package hu.bme.mit.trainbenchmark.benchmark.dgraph.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class DgraphDriverFactory extends DriverFactory<DgraphDriver> {

	@Override
	public DgraphDriver createInstance() throws Exception {
		return new DgraphDriver();
	}

}
