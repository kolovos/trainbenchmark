package hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations;

import hu.bme.mit.trainbenchmark.benchmark.graphflow.GraphflowConstants;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.driver.GraphflowDriver;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class GraphflowTransformation<TGraphflowMatch extends GraphflowMatch>
		extends ModelTransformation<TGraphflowMatch, GraphflowDriver> {

	protected final String transformationDefinition;

	public GraphflowTransformation(final GraphflowDriver driver, final String workspaceDir,
								   final RailwayOperation operation) throws IOException {
		super(driver);

		this.transformationDefinition = FileUtils.readFileToString(
				new File(workspaceDir + GraphflowConstants.CYPHER_DIR
					+ "transformations/" + operation + "Rhs." + GraphflowConstants.QUERY_EXTENSION),
				StandardCharsets.UTF_8);
	}

}
