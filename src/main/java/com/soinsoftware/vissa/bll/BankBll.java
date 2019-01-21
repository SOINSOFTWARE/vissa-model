// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.BankDao;
import com.soinsoftware.vissa.model.Bank;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class BankBll extends AbstractBll<Bank, BigInteger> {

	private static BankBll instance;

	private BankBll() throws IOException {
		super(new BankDao());
	}

	public Bank select(final String name) {
		return ((BankDao) dao).select(name);
	}

	public static BankBll getInstance() throws IOException {
		if (instance == null) {
			instance = new BankBll();
		}
		return instance;
	}
}