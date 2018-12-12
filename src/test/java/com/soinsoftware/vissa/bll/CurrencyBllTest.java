// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.Currency;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class CurrencyBllTest extends TestCase {

	private CurrencyBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = CurrencyBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<Currency> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Currency> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCodeNotExists() {
		final Currency entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByCodeExists() {
		final Currency entity = bll.select("COP");
		assertNotNull(entity);
	}

	public void testSaveInvalid() {
		try {
			bll.save(Currency.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() {
		Currency currency = bll.select("COP");
		if (currency == null) {
			currency = buildTestData();
			bll.save(currency);
		}
	}

	private Currency buildTestData() {
		return Currency.builder().code("COP").name("Pesos").build();
	}
}