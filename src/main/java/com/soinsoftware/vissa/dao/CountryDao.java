// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.Country;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class CountryDao extends AbstractDataAccessibleObject<Country, BigInteger> {

	public CountryDao() throws IOException {
		super(Country.class);
	}
	
	public Country select(final String name) {
		return getSession().bySimpleNaturalId(Country.class).load(name);
	}
}