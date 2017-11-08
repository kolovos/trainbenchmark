package com.ldbc.impls.workloads.ldbc.snb.graphflow;

import com.ldbc.impls.workloads.ldbc.snb.graphflow.bi.GraphflowBiQueryStore;

import java.io.IOException;
import java.util.Map;

public class GraphflowPoolingDbConnectionStore extends GraphflowDriverConnectionStore {

	public GraphflowPoolingDbConnectionStore(Map<String, String> properties, GraphflowBiQueryStore store) {
		super(properties, store);
	}

}
