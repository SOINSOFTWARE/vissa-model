// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.ProductTypeDao;
import com.soinsoftware.vissa.model.ProductType;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class ProductTypeBll extends AbstractBll<ProductType, BigInteger> {

	private static ProductTypeBll instance;

	private ProductTypeBll() throws IOException {
		super(new ProductTypeDao());
	}

	public ProductType select(final String name) {
		return ((ProductTypeDao) dao).select(name);
	}

	public static ProductTypeBll getInstance() throws IOException {
		if (instance == null) {
			instance = new ProductTypeBll();
		}
		return instance;
	}
}