// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import org.hibernate.Session;

import com.soinsoftware.vissa.model.InventoryTransaction;
import com.soinsoftware.vissa.model.Product;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class InventoryTransactionDao extends AbstractDataAccessibleObject<InventoryTransaction, BigInteger> {

	public InventoryTransactionDao() throws IOException {
		super(InventoryTransaction.class);
	}

	public InventoryTransaction select(final Product product) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(InventoryTransaction.class).load(product);
	}
}