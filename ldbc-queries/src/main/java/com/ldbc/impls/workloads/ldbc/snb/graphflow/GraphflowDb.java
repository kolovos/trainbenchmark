package com.ldbc.impls.workloads.ldbc.snb.graphflow;

import ca.waterloo.dsg.graphflow.query.QueryProcessor;
import com.ldbc.driver.DbConnectionState;
import com.ldbc.driver.DbException;
import com.ldbc.impls.workloads.ldbc.snb.SnbDb;
import com.ldbc.impls.workloads.ldbc.snb.graphflow.bi.GraphflowBiQueryStore;

public abstract class GraphflowDb extends SnbDb<GraphflowBiQueryStore> {

	protected GraphflowDriverConnectionStore dbs;
	protected QueryProcessor processor;

	@Override
	protected DbConnectionState getConnectionState() throws DbException {
		return dbs;
	}

}
