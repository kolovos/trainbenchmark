package com.ldbc.impls.workloads.ldbc.snb.graphflow;

import com.ldbc.driver.Operation;
import com.ldbc.driver.OperationHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class GraphflowOperationHandler<OperationType extends Operation, OperationResult, QueryStore>
        implements OperationHandler<OperationType, GraphflowDriverConnectionStore<QueryStore>> {

    public abstract String getQueryString(GraphflowDriverConnectionStore<QueryStore> state, OperationType operation);
    public OperationResult convertSingleResult(Object[] tuple, String[] columnNames) {
        final List<String> columnNamesList = Arrays.asList(columnNames);
        final Map<String, Integer> columnNamesMapping = IntStream.range(0, columnNamesList.size())
                .boxed()
                .collect(Collectors.toMap(columnNamesList::get, i -> i));
        return convertSingleResult(tuple, columnNamesMapping);
    };
    public abstract OperationResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap);

}
