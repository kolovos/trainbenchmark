package com.ldbc.impls.workloads.ldbc.snb.graphflow.bi;

import com.ldbc.driver.DbException;
import com.ldbc.impls.workloads.ldbc.snb.bi.BiQueryStore;
import com.ldbc.impls.workloads.ldbc.snb.util.Converter;

public class GraphflowBiQueryStore extends BiQueryStore {

	public GraphflowBiQueryStore(String path) throws DbException {
		super(path, "bi-", ".cypher");
	}

	@Override
	protected Converter getConverter() {
		return new Converter();
	}
}
