package com.ldbc.impls.workloads.ldbc.snb.cypher;

import com.ldbc.impls.workloads.ldbc.snb.cypher.bi.CypherBiQueryStore;

import java.io.IOException;
import java.util.Map;

public class CypherPoolingDbConnectionStore extends CypherDriverConnectionStore {

	//protected final QueryProcessor queryProcessor;

	public CypherPoolingDbConnectionStore(Map<String, String> properties, CypherBiQueryStore store) {
		super(properties, store);

		//queryProcessor =
	}

	@Override
	public void close() throws IOException {

	}
}
