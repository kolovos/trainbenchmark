package com.ldbc.impls.workloads.ldbc.snb.graphflow;

import com.ldbc.driver.DbException;
import com.ldbc.impls.workloads.ldbc.snb.bi.test.LdbcSnbBiQueryTest;
import com.ldbc.impls.workloads.ldbc.snb.graphflow.bi.GraphflowBiDb;

import java.util.HashMap;
import java.util.Map;

public class LdbcSnbBiGraphflowTest extends LdbcSnbBiQueryTest {

	private final String queryDir = "queries/";

	public LdbcSnbBiGraphflowTest() throws DbException {
		super(new GraphflowBiDb());

//		GraphflowBiDb gdb = (GraphflowBiDb) db;
	}

	@Override
	protected final Map<String, String> getProperties() {
		Map<String, String> properties = new HashMap<>();
		properties.put("queryDir", queryDir);
		properties.put("printQueryNames", "true");
		properties.put("printQueryStrings", "false");
		properties.put("printQueryResults", "false");
		return properties;
	}

}
