// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.WarehouseDao;
import com.soinsoftware.vissa.model.Warehouse;

/**
 * @author Carlos Rodriguez
 * @since 21/12/2018
 */
public class WarehouseBll extends AbstractBll<Warehouse, BigInteger> {

	private static WarehouseBll instance;

	private WarehouseBll() throws IOException {
		super(new WarehouseDao());
	}

	public Warehouse select(final String code) {
		return ((WarehouseDao) dao).select(code);
	}

	public static WarehouseBll getInstance() throws IOException {
		if (instance == null) {
			instance = new WarehouseBll();
		}
		return instance;
	}
}