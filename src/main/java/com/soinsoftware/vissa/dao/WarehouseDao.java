// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.Warehouse;

/**
 * @author Lina Florez
 * @since 21/01/2017
 */
public class WarehouseDao extends AbstractDataAccessibleObject<Warehouse, BigInteger> {

	public WarehouseDao() throws IOException {
		super(Warehouse.class);
	}

	public Warehouse select(final String code) {
		return getSession().bySimpleNaturalId(Warehouse.class).load(code);
	}

}