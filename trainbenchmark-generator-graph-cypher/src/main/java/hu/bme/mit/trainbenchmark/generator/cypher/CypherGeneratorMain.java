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

package hu.bme.mit.trainbenchmark.generator.cypher;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.cypher.config.CypherGeneratorConfig;

public class CypherGeneratorMain {

	public static void main(final String[] args) throws Exception {
		final CypherGeneratorConfig gc = GeneratorConfig.fromFile(args[0], CypherGeneratorConfig.class);
		final CypherSerializer cypherSerializer = new CypherSerializer(gc);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(cypherSerializer, gc);
		generator.generateModel();
	}

}
