// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import org.hibernate.Session;

import com.soinsoftware.vissa.model.ProductCategory;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class ProductCategoryDao extends AbstractDataAccessibleObject<ProductCategory, BigInteger> {

	public ProductCategoryDao() throws IOException {
		super(ProductCategory.class);
	}
	
	public ProductCategory select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(ProductCategory.class).load(name);
	}
}