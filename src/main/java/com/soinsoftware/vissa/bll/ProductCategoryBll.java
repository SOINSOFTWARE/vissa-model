// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.ProductCategoryDao;
import com.soinsoftware.vissa.model.ProductCategory;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class ProductCategoryBll extends AbstractBll<ProductCategory, BigInteger> {

	private static ProductCategoryBll instance;

	private ProductCategoryBll() throws IOException {
		super(new ProductCategoryDao());
	}

	public ProductCategory select(final String name) {
		return ((ProductCategoryDao) dao).select(name);
	}

	public static ProductCategoryBll getInstance() throws IOException {
		if (instance == null) {
			instance = new ProductCategoryBll();
		}
		return instance;
	}
}