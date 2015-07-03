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
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ID;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

import java.sql.SQLException;
import java.util.Collection;

public class SQLTransformationInjectSwitchSensor extends SQLTransformationInject {

	public SQLTransformationInjectSwitchSensor(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<Long> switches) throws SQLException {
		for (final Long sw : switches) {
			// sensor
			final String deleteSensor = "" + //
					"DELETE `" + SENSOR + "` FROM `" + SENSOR + "` " + //
					"INNER JOIN `" + TRACKELEMENT + "` " + //
					"ON Sensor.id = TrackElement.sensor " + //
					"WHERE TrackElement.id = " + sw + ";";
			// trackElement
			final String deleteTrackElement = "" + //
					"DELETE FROM `" + TRACKELEMENT + "` " + //
					"WHERE `" + ID + "` = " + sw + ";";
			// definedBy
			final String deleteDefinedBy = "" + //
					"DELETE `" + DEFINED_BY + "` FROM `" + DEFINED_BY + "` " + //
					"INNER JOIN `" + TRACKELEMENT + "` " + //
					"ON definedBy.sensor_id = TrackElement.sensor " + //
					"WHERE TrackElement.id = " + sw + ";";

			sqlDriver.getConnection().createStatement().executeUpdate(deleteSensor);
			sqlDriver.getConnection().createStatement().executeUpdate(deleteTrackElement);
			sqlDriver.getConnection().createStatement().executeUpdate(deleteDefinedBy);
		}
	}

}
