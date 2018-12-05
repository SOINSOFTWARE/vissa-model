// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.Country;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class CountryBllTest extends TestCase {

	private CountryBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = CountryBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<Country> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Country> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNameNotExists() {
		final Country entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByNameExists() {
		final Country entity = bll.select("Colombia");
		assertNotNull(entity);
	}

	public void testSaveInvalid() {
		try {
			bll.save(Country.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() {
		Country country = bll.select("Colombia");
		if (country == null) {
			country = buildTestData();
		}
		bll.save(country);
	}

	private Country buildTestData() {
		return Country.builder().name("Colombia").build();
	}
}