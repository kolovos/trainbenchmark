/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.driver;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class DatabaseDriver<M, T> {

	public void beginTransaction() throws IOException {
	}

	public void finishTransaction() throws IOException {
	}

	public abstract void read(String modelPath) throws IOException;

	// public abstract Collection<M> runQuery() throws IOException;

	public abstract Comparator<M> getMatchComparator();

	public void destroy() throws IOException {
	}

	// read

	public abstract List<T> collectVertices(final String type) throws IOException;

	// create

	// public abstract void insertVertexWithEdge(final List<T> sourceVertices, final String sourceVertexType, final String targetVertexType,
	// final String edgeType) throws IOException;
	//
	// public abstract T insertVertexWithEdge(T sourceVertex, final String sourceVertexType, final String targetVertexType,
	// final String edgeType) throws IOException;
	//
	// public abstract void insertEdge(final T sourceVertex, final String sourceVertexType, final T targetVertex, final String edgeType)
	// throws IOException;

	// read

	// public abstract List<T> collectOutgoingConnectedVertices(final T sourceVertex, final String sourceVertexType,
	// final String targetVertexType, final String edgeType) throws IOException;

	// public abstract Map<T, Object> getProperties(final Collection<T> vertices, final String vertexType, final String propertyName) throws
	// IOException;
	//
	// public abstract Map<T, Object> getReferences(final Collection<T> vertices, final String vertexType, final String edgeType) throws
	// IOException;

	// update

	// public abstract void updateProperties(final List<T> vertices, final String vertexType, final String propertyName,
	// final PropertyOperation propertyOperation) throws IOException;

	// public abstract void setProperties(final Map<Object, Object> properties, final String vertexType, final String propertyName) throws
	// IOException;

	// delete

	// public abstract void deleteIncomingEdge(final Collection<T> vertices, final String sourceVertexType, final String edgeType)
	// throws IOException;
	//
	// public abstract void deleteAllOutgoingEdges(final Collection<T> vertices, final String vertexType, final String edgeType)
	// throws IOException;
	//
	// public abstract void deleteOneOutgoingEdge(final Collection<T> vertices, final String vertexType, final String edgeType)
	// throws IOException;
	//
	// public abstract void deleteSingleOutgoingEdge(final Collection<T> vertices, final String vertexType, final String edgeType)
	// throws IOException;
	//
	// public abstract void deleteVertex(final T vertex, final String vertexType) throws IOException;
	//
	// public abstract void deleteVertex(final Long vertex) throws IOException;

	// repair
	public abstract void posLengthRepair(final Collection<M> matches) throws IOException;

	public abstract void routeSensorRepair(final Collection<M> matches) throws IOException;

	public abstract void semaphoreNeighborRepair(final Collection<M> matches) throws IOException;

	public abstract void switchSensorRepair(final Collection<M> matches) throws IOException;

	public abstract void switchSetRepair(final Collection<M> matches) throws IOException;

	// user
	public abstract void posLengthUser(final Collection<T> segments) throws IOException;

	public abstract void routeSensorUser(final Collection<T> routes) throws IOException;

	public abstract void semaphoreNeighborUser(final Collection<T> routes) throws IOException;

	public abstract void switchSensorUser(final Collection<T> switches) throws IOException;

	public abstract void switchSetUser(final Collection<T> switches) throws IOException;

}
