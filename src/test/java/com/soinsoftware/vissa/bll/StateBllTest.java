// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.Country;
import com.soinsoftware.vissa.model.State;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class StateBllTest extends TestCase {

	private StateBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = StateBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<State> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<State> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByKnownCountry() throws IOException {
		Country country = getDefaultCountry();
		final List<State> entities = bll.select(country);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNoKnownCountry() throws IOException {
		Country country = Country.builder().id(BigInteger.valueOf(0L)).name("Test").build();
		final List<State> entities = bll.select(country);
		assertNotNull(entities);
		assertSame(entities.size(), 0);
	}

	public void testSaveInvalid() {
		try {
			bll.save(State.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() throws IOException {
		List<State> states = bll.selectAll(false);
		if (states.isEmpty()) {
			State state = buildTestData();
			bll.save(state);
		}
	}

	private Country getDefaultCountry() throws IOException {
		return CountryBll.getInstance().select("Colombia");
	}

	private State buildTestData() throws IOException {
		Country country = getDefaultCountry();
		return State.builder().name("ATLÁNTICO").country(country).build();
	}
}