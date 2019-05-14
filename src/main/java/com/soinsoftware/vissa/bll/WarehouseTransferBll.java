// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.WarehouseTransferDao;
import com.soinsoftware.vissa.model.Warehouse;
import com.soinsoftware.vissa.model.WarehouseTransfer;

/**
 * @author Carlos Rodriguez
 * @since 21/12/2018
 */
public class WarehouseTransferBll extends AbstractBll<WarehouseTransfer, BigInteger> {

	private static WarehouseTransferBll instance;

	private WarehouseTransferBll() throws IOException {
		super(new WarehouseTransferDao());
	}

	public Warehouse select(final String code) {
		return ((WarehouseTransferDao) dao).select(code);
	}

	public static WarehouseTransferBll getInstance() throws IOException {
		if (instance == null) {
			instance = new WarehouseTransferBll();
		}
		return instance;
	}
}