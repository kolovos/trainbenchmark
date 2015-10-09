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

package hu.bme.mit.trainbenchmark.benchmark.iqdcore;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.BenchmarkLogic;

public class IQDCoreBenchmarkLogic extends BenchmarkLogic {

	protected RDFBenchmarkConfig rbc;

	public IQDCoreBenchmarkLogic(final String[] args) throws ParseException {
		bc = rbc = new RDFBenchmarkConfig(args, "IQDCore");
	}

	public IQDCoreBenchmarkLogic(final RDFBenchmarkConfig rbc) {
		super(rbc);
		this.rbc = rbc;
	}

}