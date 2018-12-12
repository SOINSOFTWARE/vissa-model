// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.CurrencyDao;
import com.soinsoftware.vissa.model.Currency;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
public class CurrencyBll extends AbstractBll<Currency, BigInteger> {

	private static CurrencyBll instance;

	private CurrencyBll() throws IOException {
		super(new CurrencyDao());
	}
	
	public Currency select(final String code) {
		return ((CurrencyDao) dao).select(code);
	}

	public static CurrencyBll getInstance() throws IOException {
		if (instance == null) {
			instance = new CurrencyBll();
		}
		return instance;
	}
}