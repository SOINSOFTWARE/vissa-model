// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.Company;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class CompanyDao extends AbstractDataAccessibleObject<Company, BigInteger> {

	public CompanyDao() throws IOException {
		super(Company.class);
	}

	public Company select(final String nit) {
		return getSession().bySimpleNaturalId(Company.class).load(nit);
	}
}