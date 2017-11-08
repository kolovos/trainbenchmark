package com.ldbc.impls.workloads.ldbc.snb.graphflow;

import com.ldbc.driver.DbException;
import com.ldbc.impls.workloads.ldbc.snb.bi.test.LdbcSnbBiQueryTest;
import com.ldbc.impls.workloads.ldbc.snb.graphflow.bi.GraphflowBiDb;

import java.util.HashMap;
import java.util.Map;

public class LdbcSnbBiGraphflowTest extends LdbcSnbBiQueryTest {

	private final String endpoint = "bolt://localhost:7687";
	private final String user = "neo4j";
	private final String password = "admin";
	private final String queryDir = "queries/";

	public LdbcSnbBiGraphflowTest() throws DbException {
		super(new GraphflowBiDb());
	}

	@Override
	protected final Map<String, String> getProperties() {
		Map<String, String> properties = new HashMap<>();
		properties.put("endpoint", endpoint);
		properties.put("user", user);
		properties.put("password", password);
		properties.put("queryDir", queryDir);
		properties.put("printQueryNames", "true");
		properties.put("printQueryStrings", "false");
		properties.put("printQueryResults", "false");
		return properties;
	}

}
