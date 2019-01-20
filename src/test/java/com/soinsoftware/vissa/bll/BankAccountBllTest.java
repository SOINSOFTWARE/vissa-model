// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.Bank;
import com.soinsoftware.vissa.model.BankAccount;
import com.soinsoftware.vissa.model.BankAccountStatus;
import com.soinsoftware.vissa.model.BankAccountType;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class BankAccountBllTest extends TestCase {

	private BankAccountBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = BankAccountBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<BankAccount> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<BankAccount> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByAccountNotExists() {
		final BankAccount entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByAccountExists() {
		final BankAccount entity = bll.select("1234-5678-9012");
		assertNotNull(entity);
	}

	public void testSaveInvalid() {
		try {
			bll.save(BankAccount.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() throws IOException {
		BankAccount bankAccount = bll.select("1234-5678-9012");
		if (bankAccount == null) {
			bankAccount = buildTestData();
		}
		bll.save(bankAccount);
	}

	private BankAccount buildTestData() throws IOException {
		Bank bank = BankBll.getInstance().select("test");
		return BankAccount.builder().account("1234-5678-9012").type(BankAccountType.SAVING).bank(bank).status(BankAccountStatus.ACTIVE).build();
	}
}