// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.ProductStockDao;
import com.soinsoftware.vissa.model.Product;
import com.soinsoftware.vissa.model.ProductStock;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class ProductStockBll extends AbstractBll<ProductStock, BigInteger> {

	private static ProductStockBll instance;
	
	private ProductStockBll() throws IOException {
		super(new ProductStockDao());
	}

	public ProductStock select(final Product product) {
		return ((ProductStockDao) dao).select(product);
	}

	public static ProductStockBll getInstance() throws IOException {
		if (instance == null) {
			instance = new ProductStockBll();
		}
		return instance;
	}
}