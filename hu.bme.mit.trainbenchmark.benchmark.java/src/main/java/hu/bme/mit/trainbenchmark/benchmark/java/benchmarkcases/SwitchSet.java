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
package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Signal;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

public class SwitchSet extends JavaBenchmarkCase<SwitchPosition> {

	@Override
	protected Collection<SwitchPosition> check() {
		results = new ArrayList<>();

		final TreeIterator<EObject> contents = container.eAllContents();
		while (contents.hasNext()) {
			final EObject eObject = contents.next();

			if (eObject instanceof Route) {
				final Route route = (Route) eObject;
				final Semaphore semaphore = route.getEntry();
				if (semaphore == null) {
					continue;
				}
				if (semaphore.getSignal() == Signal.GO) {
					for (final SwitchPosition swP : route.getFollows()) {
						final Switch sw = swP.getSwitch();
						if (sw.getCurrentPosition() != swP.getPosition()) {
							results.add(swP);
						}
					}
				}
			}
		}

		return results;
	}

}
