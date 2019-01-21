// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.BankAccountDao;
import com.soinsoftware.vissa.model.BankAccount;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class BankAccountBll extends AbstractBll<BankAccount, BigInteger> {

	private static BankAccountBll instance;

	private BankAccountBll() throws IOException {
		super(new BankAccountDao());
	}

	public BankAccount select(final String account) {
		return ((BankAccountDao) dao).select(account);
	}

	public static BankAccountBll getInstance() throws IOException {
		if (instance == null) {
			instance = new BankAccountBll();
		}
		return instance;
	}
}