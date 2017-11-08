package com.ldbc.impls.workloads.ldbc.snb.graphflow;

import com.ldbc.driver.DbException;
import com.ldbc.driver.Operation;
import com.ldbc.driver.ResultReporter;

import java.util.List;

public abstract class GraphflowListOperationHandler<OperationType extends Operation<List<OperationResult>>, OperationResult, QueryStore>
		extends GraphflowOperationHandler<OperationType, OperationResult, QueryStore> {

	@Override
	public void executeOperation(OperationType operation, GraphflowDriverConnectionStore<QueryStore> state,
			ResultReporter resultReporter) throws DbException {
//		Session session = state.getSession();
//		List<OperationResult> results = new ArrayList<OperationResult>();
//		int resultCount = 0;
//		results.clear();
//
//		final String queryString = getQueryString(state, operation);
//		state.logQuery(operation.getClass().getSimpleName(), queryString);
//		final StatementResult result = session.run(queryString);
//		while (result.hasNext()) {
//			final Record record = result.next();
//
//			resultCount++;
//
//			OperationResult tuple = convertSingleResult(result);
//			if (state.isPrintResults())
//				System.out.println(tuple.toString());
//			results.add(tuple);
//		}
//		session.close();
//		resultReporter.report(resultCount, results, operation);
	}

}
