// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.City;
import com.soinsoftware.vissa.model.Country;
import com.soinsoftware.vissa.model.State;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class CityBllTest extends TestCase {

	private CityBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = CityBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<City> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<City> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByKnownState() throws IOException {
		State state = getDefaultState();
		final List<City> entities = bll.select(state);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNoKnownState() throws IOException {
		State state = State.builder().id(BigInteger.valueOf(0L)).name("Test").build();
		final List<City> entities = bll.select(state);
		assertNotNull(entities);
		assertSame(entities.size(), 0);
	}

	public void testSaveInvalid() {
		try {
			bll.save(City.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() throws IOException {
		List<City> cities = bll.selectAll(false);
		if (cities.isEmpty()) {
			City city = buildTestData();
			bll.save(city);
		}
	}

	private State getDefaultState() throws IOException {
		Country country = CountryBll.getInstance().select("Colombia");
		List<State> states = StateBll.getInstance().select(country);
		return states.stream().filter(state -> state.getName().equals("ATLÁNTICO")).findFirst().get();
	}

	private City buildTestData() throws IOException {
		State state = getDefaultState();
		return City.builder().name("Barranquilla").state(state).build();
	}
}