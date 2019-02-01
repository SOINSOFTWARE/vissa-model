// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.CompanyDao;
import com.soinsoftware.vissa.model.Company;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class CompanyBll extends AbstractBll<Company, BigInteger> {

	private static CompanyBll instance;

	private CompanyBll() throws IOException {
		super(new CompanyDao());
	}

	public Company select(final String nit) {
		return ((CompanyDao) dao).select(nit);
	}

	public static CompanyBll getInstance() throws IOException {
		if (instance == null) {
			instance = new CompanyBll();
		}
		return instance;
	}
}