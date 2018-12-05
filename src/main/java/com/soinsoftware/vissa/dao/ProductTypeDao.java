// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import org.hibernate.Session;

import com.soinsoftware.vissa.model.ProductType;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class ProductTypeDao extends AbstractDataAccessibleObject<ProductType, BigInteger> {

	public ProductTypeDao() throws IOException {
		super(ProductType.class);
	}
	
	public ProductType select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(ProductType.class).load(name);
	}
}