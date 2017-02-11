package hu.bme.mit.trainbenchmark.benchmark.dgraph.test;

import org.junit.Test;

import io.dgraph.client.DgraphClient;
import io.dgraph.client.DgraphResult;
import io.dgraph.client.GrpcDgraphClient;

public class BasicTest {

	@Test
	public void test() {
		final DgraphClient dgraphClient = GrpcDgraphClient.newInstance("localhost", 8080);
		final DgraphResult result = dgraphClient
				.query("{me(_xid_: alice) { name _xid_ follows { name _xid_ follows {name _xid_ } } }}");
		System.out.println(result.toJsonObject().toString());
	}

}
