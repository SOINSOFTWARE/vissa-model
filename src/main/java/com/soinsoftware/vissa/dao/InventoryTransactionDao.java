// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

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
		return getSession().bySimpleNaturalId(InventoryTransaction.class).load(product);
	}
}