// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.SupplierDao;
import com.soinsoftware.vissa.model.Supplier;

/**
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
public class SupplierBll extends AbstractBll<Supplier, BigInteger> {
	
	private static SupplierBll instance;

	private SupplierBll() throws IOException {
		super(new SupplierDao());
	}

	public Supplier select(final String document) {
		return ((SupplierDao) dao).select(document);
	}
	
	public static SupplierBll getInstance() throws IOException {
		if (instance == null) {
			instance = new SupplierBll();
		}
		return instance;
	}
}