// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.InventoryTransactionDao;
import com.soinsoftware.vissa.model.InventoryTransaction;
import com.soinsoftware.vissa.model.Product;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class InventoryTransactionBll extends AbstractBll<InventoryTransaction, BigInteger> {

	private static InventoryTransactionBll instance;

	private InventoryTransactionBll() throws IOException {
		super(new InventoryTransactionDao());
	}

	public InventoryTransaction select(final Product product) {
		return ((InventoryTransactionDao) dao).select(product);
	}

	public static InventoryTransactionBll getInstance() throws IOException {
		if (instance == null) {
			instance = new InventoryTransactionBll();
		}
		return instance;
	}
}