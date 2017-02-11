package hu.bme.mit.trainbenchmark.benchmark.dgraph.operations;

import hu.bme.mit.trainbenchmark.benchmark.dgraph.driver.DgraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.matches.DgraphSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQuery;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQueryConnectedSegmentsInject;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQueryPosLengthInject;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQueryRouteSensorInject;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQuerySemaphoreNeighborInject;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQuerySwitchMonitoredInject;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.queries.DgraphQuerySwitchSetInject;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.DgraphTransformation;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.inject.DgraphTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.inject.DgraphTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.inject.DgraphTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.repair.DgraphTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.repair.DgraphTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.repair.DgraphTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.repair.DgraphTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.repair.DgraphTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.dgraph.transformations.repair.DgraphTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject.DgraphTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject.DgraphTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject.DgraphTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class DgraphModelOperationFactory<TDriver extends DgraphDriver> extends ModelOperationFactory<DgraphMatch, TDriver> {

	@Override
	public ModelOperation<? extends DgraphMatch, TDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
			final TDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final DgraphQuery<DgraphConnectedSegmentsMatch, TDriver> query = new DgraphQueryConnectedSegments<>(driver);
			final ModelOperation<DgraphConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final DgraphQuery<DgraphConnectedSegmentsInjectMatch, TDriver> query = new DgraphQueryConnectedSegmentsInject<>(driver);
			final DgraphTransformation<DgraphConnectedSegmentsInjectMatch, TDriver> transformation = new DgraphTransformationInjectConnectedSegments<>(
					driver);
			final ModelOperation<DgraphConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final DgraphQuery<DgraphConnectedSegmentsMatch, TDriver> query = new DgraphQueryConnectedSegments<>(driver);
			final DgraphTransformation<DgraphConnectedSegmentsMatch, TDriver> transformation = new DgraphTransformationRepairConnectedSegments<>(
					driver);
			final ModelOperation<DgraphConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final DgraphQuery<DgraphPosLengthMatch, TDriver> query = new DgraphQueryPosLength<>(driver);
			final ModelOperation<DgraphPosLengthMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final DgraphQuery<DgraphPosLengthInjectMatch, TDriver> query = new DgraphQueryPosLengthInject<>(driver);
			final DgraphTransformation<DgraphPosLengthInjectMatch, TDriver> transformation = new DgraphTransformationInjectPosLength<>(driver);
			final ModelOperation<DgraphPosLengthInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final DgraphQuery<DgraphPosLengthMatch, TDriver> query = new DgraphQueryPosLength<>(driver);
			final DgraphTransformation<DgraphPosLengthMatch, TDriver> transformation = new DgraphTransformationRepairPosLength<>(driver);
			final ModelOperation<DgraphPosLengthMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final DgraphQuery<DgraphRouteSensorMatch, TDriver> query = new DgraphQueryRouteSensor<>(driver);
			final ModelOperation<DgraphRouteSensorMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final DgraphQuery<DgraphRouteSensorInjectMatch, TDriver> query = new DgraphQueryRouteSensorInject<>(driver);
			final DgraphTransformation<DgraphRouteSensorInjectMatch, TDriver> transformation = new DgraphTransformationInjectRouteSensor<>(
					driver);
			final ModelOperation<DgraphRouteSensorInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final DgraphQuery<DgraphRouteSensorMatch, TDriver> query = new DgraphQueryRouteSensor<>(driver);
			final DgraphTransformation<DgraphRouteSensorMatch, TDriver> transformation = new DgraphTransformationRepairRouteSensor<>(driver);
			final ModelOperation<DgraphRouteSensorMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final DgraphQuery<DgraphSemaphoreNeighborMatch, TDriver> query = new DgraphQuerySemaphoreNeighbor<>(driver);
			final ModelOperation<DgraphSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final DgraphQuery<DgraphSemaphoreNeighborInjectMatch, TDriver> query = new DgraphQuerySemaphoreNeighborInject<>(driver);
			final DgraphTransformation<DgraphSemaphoreNeighborInjectMatch, TDriver> transformation = new DgraphTransformationInjectSemaphoreNeighbor<>(
					driver);
			final ModelOperation<DgraphSemaphoreNeighborInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final DgraphQuery<DgraphSemaphoreNeighborMatch, TDriver> query = new DgraphQuerySemaphoreNeighbor<>(driver);
			final DgraphTransformation<DgraphSemaphoreNeighborMatch, TDriver> transformation = new DgraphTransformationRepairSemaphoreNeighbor<>(
					driver);
			final ModelOperation<DgraphSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final DgraphQuery<DgraphSwitchMonitoredMatch, TDriver> query = new DgraphQuerySwitchMonitored<>(driver);
			final ModelOperation<DgraphSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final DgraphQuery<DgraphSwitchMonitoredInjectMatch, TDriver> query = new DgraphQuerySwitchMonitoredInject<>(driver);
			final DgraphTransformation<DgraphSwitchMonitoredInjectMatch, TDriver> transformation = new DgraphTransformationInjectSwitchMonitored<>(
					driver);
			final ModelOperation<DgraphSwitchMonitoredInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final DgraphQuery<DgraphSwitchMonitoredMatch, TDriver> query = new DgraphQuerySwitchMonitored<>(driver);
			final DgraphTransformation<DgraphSwitchMonitoredMatch, TDriver> transformation = new DgraphTransformationRepairSwitchMonitored<>(
					driver);
			final ModelOperation<DgraphSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final DgraphQuery<DgraphSwitchSetMatch, TDriver> query = new DgraphQuerySwitchSet<>(driver);
			final ModelOperation<DgraphSwitchSetMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final DgraphQuery<DgraphSwitchSetInjectMatch, TDriver> query = new DgraphQuerySwitchSetInject<>(driver);
			final DgraphTransformation<DgraphSwitchSetInjectMatch, TDriver> transformation = new DgraphTransformationInjectSwitchSet<>(driver);
			final ModelOperation<DgraphSwitchSetInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final DgraphQuery<DgraphSwitchSetMatch, TDriver> query = new DgraphQuerySwitchSet<>(driver);
			final DgraphTransformation<DgraphSwitchSetMatch, TDriver> transformation = new DgraphTransformationRepairSwitchSet<>(driver);
			final ModelOperation<DgraphSwitchSetMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		default:
			break;
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
