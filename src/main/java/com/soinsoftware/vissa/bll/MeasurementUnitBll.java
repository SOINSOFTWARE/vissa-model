// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	@Override
	public List<MeasurementUnit> selectAll(boolean enabled) {
		List<MeasurementUnit> measurementUnits = super.selectAll(enabled);
		return measurementUnits.stream().sorted(Comparator.comparing(MeasurementUnit::getName)).collect(Collectors.toList());
	}

	public static MeasurementUnitBll getInstance() throws IOException {
		if (instance == null) {
			instance = new MeasurementUnitBll();
		}
		return instance;
	}
}