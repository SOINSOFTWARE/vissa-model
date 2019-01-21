// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.MeasurementUnit;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class MeasurementUnitDao extends AbstractDataAccessibleObject<MeasurementUnit, BigInteger> {

	public MeasurementUnitDao() throws IOException {
		super(MeasurementUnit.class);
	}
	
	public MeasurementUnit select(final String name) {
		return getSession().bySimpleNaturalId(MeasurementUnit.class).load(name);
	}
}