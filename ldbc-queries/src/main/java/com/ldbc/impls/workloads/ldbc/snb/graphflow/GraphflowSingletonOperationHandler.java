package com.ldbc.impls.workloads.ldbc.snb.graphflow;

import com.ldbc.driver.DbException;
import com.ldbc.driver.Operation;
import com.ldbc.driver.ResultReporter;

public abstract class GraphflowSingletonOperationHandler<OperationType extends Operation<OperationResult>, OperationResult, QueryStore>
        extends GraphflowOperationHandler<OperationType, OperationResult, QueryStore> {

    @Override
    public void executeOperation(OperationType operation, GraphflowDriverConnectionStore<QueryStore> state,
                                 ResultReporter resultReporter) throws DbException {
//	Session session = state.getSession();
//	OperationResult tuple = null;
//	int resultCount = 0;
//
//	final String queryString = getQueryString(state, operation);
//	state.logQuery(operation.getClass().getSimpleName(), queryString);
//	final StatementResult result = session.run(queryString);
//	if (result.hasNext()) {
//		final Record record = result.next();
//		resultCount++;
//
////		tuple = convertSingleResult(result);
////		if (state.isPrintResults())
////			System.out.println(tuple.toString());
//		if (state.isPrintResults()) {
//			System.out.println(record);
//		}
//	}
//	session.close();
//
//	resultReporter.report(resultCount, tuple, operation);
    }

}
