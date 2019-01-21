// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.Currency;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
public class CurrencyDao extends AbstractDataAccessibleObject<Currency, BigInteger> {

	public CurrencyDao() throws IOException {
		super(Currency.class);
	}

	public Currency select(final String code) {
		return getSession().bySimpleNaturalId(Currency.class).load(code);
	}
}