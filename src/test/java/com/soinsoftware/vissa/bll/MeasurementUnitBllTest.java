// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.MeasurementUnit;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class MeasurementUnitBllTest extends TestCase {

	private MeasurementUnitBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = MeasurementUnitBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<MeasurementUnit> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<MeasurementUnit> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNameNotExists() {
		final MeasurementUnit entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByNameExists() {
		final MeasurementUnit entity = bll.select("Measurement unit Test");
		assertNotNull(entity);
	}

	public void testSaveInvalid() {
		try {
			bll.save(MeasurementUnit.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() {
		MeasurementUnit measurementUnit = bll.select("Measurement unit Test");
		if (measurementUnit == null) {
			measurementUnit = buildTestData();
			bll.save(measurementUnit);
		}
	}

	private MeasurementUnit buildTestData() {
		return MeasurementUnit.builder().name("Measurement unit Test").build();
	}
}