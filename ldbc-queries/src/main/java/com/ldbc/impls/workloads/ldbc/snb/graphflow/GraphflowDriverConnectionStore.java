package com.ldbc.impls.workloads.ldbc.snb.graphflow;

import ca.waterloo.dsg.graphflow.query.QueryProcessor;
import com.ldbc.driver.DbConnectionState;

import java.io.IOException;
import java.util.Map;

public abstract class GraphflowDriverConnectionStore<DbQueryStore> extends DbConnectionState {

	protected final QueryProcessor queryProcessor = new QueryProcessor();
	private DbQueryStore queryStore;
	private boolean printNames;
	private boolean printStrings;
	private boolean printResults;

	public GraphflowDriverConnectionStore(Map<String, String> properties, DbQueryStore store) {
		super();

		queryStore = store;
		printNames = Boolean.valueOf(properties.get("printQueryNames"));
		printStrings = Boolean.valueOf(properties.get("printQueryStrings"));
		printResults = Boolean.valueOf(properties.get("printQueryResults"));
	}

	public final DbQueryStore getQueryStore() {
		return queryStore;
	}

	public final boolean isPrintResults() {
		return printResults;
	}

	public final void logQuery(String queryType, String query) {
		if (printNames) {
			System.out.println("########### " + queryType);
		}
		if (printStrings) {
			System.out.println(query);
		}
	}

	@Override
	public void close() throws IOException {
	}

	public QueryProcessor getQueryProcessor() {
		return queryProcessor;
	}
}
