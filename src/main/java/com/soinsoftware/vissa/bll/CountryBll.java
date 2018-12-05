// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.CountryDao;
import com.soinsoftware.vissa.model.Country;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class CountryBll extends AbstractBll<Country, BigInteger> {

	private static CountryBll instance;

	private CountryBll() throws IOException {
		super(new CountryDao());
	}
	
	public Country select(final String name) {
		return ((CountryDao) dao).select(name);
	}

	public static CountryBll getInstance() throws IOException {
		if (instance == null) {
			instance = new CountryBll();
		}
		return instance;
	}
}