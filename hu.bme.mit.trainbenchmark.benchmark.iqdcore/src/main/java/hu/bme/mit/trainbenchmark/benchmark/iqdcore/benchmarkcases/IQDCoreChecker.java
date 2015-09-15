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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.benchmarkcases;

import hu.bme.mit.IQDcore.WildcardInput;
import hu.bme.mit.IQDcore.trainbenchmark.ConnectedSegments;
import hu.bme.mit.IQDcore.trainbenchmark.PosLength;
import hu.bme.mit.IQDcore.trainbenchmark.RouteSensor;
import hu.bme.mit.IQDcore.trainbenchmark.SemaphoreNeighbor;
import hu.bme.mit.IQDcore.trainbenchmark.SwitchSensor;
import hu.bme.mit.IQDcore.trainbenchmark.SwitchSet;
import hu.bme.mit.IQDcore.trainbenchmark.TrainbenchmarkQuery;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.rdf.checkers.RDFChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import scala.collection.Iterator;
import scala.collection.immutable.Vector;

public class IQDCoreChecker extends RDFChecker<IQDCoreMatch> {

	protected WildcardInput iqdInput;
	protected TrainbenchmarkQuery checker;

	public IQDCoreChecker(final WildcardInput iqdDriver,
			final RDFBenchmarkConfig rdfbc) throws IOException {
		super(rdfbc);
		this.iqdInput = iqdDriver;
		switch (rdfbc.getQuery()) {
		case CONNECTEDSEGMENTS:
			this.checker = new ConnectedSegments();
			break;
		case POSLENGTH:
			this.checker = new PosLength();
			break;
		case ROUTESENSOR:
			this.checker = new RouteSensor();
			break;
		case SEMAPHORENEIGHBOR:
			this.checker = new SemaphoreNeighbor();
			break;
		case SWITCHSENSOR:
			this.checker = new SwitchSensor();
			break;
		case SWITCHSET:
			this.checker = new SwitchSet();
			break;
		default:
			break;
		}
		iqdInput.subscribe(checker.inputLookup());
	}

	@Override
	public Collection<IQDCoreMatch> check() throws IOException {
		final List<IQDCoreMatch> matches = new ArrayList<>();
		
		Iterator<Vector<Object>> resultIterator = checker.getResults()
				.iterator();
		while (resultIterator.hasNext()) {
			final Vector<Object> qs = resultIterator.next();
			final IQDCoreMatch match = IQDCoreMatch.createMatch(query, qs);
			matches.add(match);
		}
		return matches;
	}
}
