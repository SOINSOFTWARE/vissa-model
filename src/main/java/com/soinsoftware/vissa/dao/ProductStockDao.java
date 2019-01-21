// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.Product;
import com.soinsoftware.vissa.model.ProductStock;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class ProductStockDao extends AbstractDataAccessibleObject<ProductStock, BigInteger> {

	public ProductStockDao() throws IOException {
		super(ProductStock.class);
	}

	public ProductStock select(final Product product) {
		return getSession().bySimpleNaturalId(ProductStock.class).load(product);
	}
}