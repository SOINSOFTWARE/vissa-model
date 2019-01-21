// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.MeasurementUnitDao;
import com.soinsoftware.vissa.model.MeasurementUnit;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class MeasurementUnitBll extends AbstractBll<MeasurementUnit, BigInteger> {

	private static MeasurementUnitBll instance;

	private MeasurementUnitBll() throws IOException {
		super(new MeasurementUnitDao());
	}
	
	public MeasurementUnit select(final String name) {
		return ((MeasurementUnitDao) dao).select(name);
	}

	public static MeasurementUnitBll getInstance() throws IOException {
		if (instance == null) {
			instance = new MeasurementUnitBll();
		}
		return instance;
	}
}