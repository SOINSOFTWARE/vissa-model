// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.Bank;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class BankBllTest extends TestCase {

	private BankBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = BankBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<Bank> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Bank> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNameNotExists() {
		final Bank entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByNameExists() {
		final Bank entity = bll.select("test");
		assertNotNull(entity);
	}

	public void testSaveInvalid() {
		try {
			bll.save(Bank.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() {
		Bank bank = bll.select("test");
		if (bank == null) {
			bank = buildTestData();
		}
		bll.save(bank);
	}

	private Bank buildTestData() {
		return Bank.builder().name("test").build();
	}
}