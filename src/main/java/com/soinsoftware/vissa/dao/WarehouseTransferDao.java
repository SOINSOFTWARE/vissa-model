// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.WarehouseTransfer;

/**
 * @author Lina Florez
 * @since 21/01/2017
 */
public class WarehouseTransferDao extends AbstractDataAccessibleObject<WarehouseTransfer, BigInteger> {

	public WarehouseTransferDao() throws IOException {
		super(WarehouseTransfer.class);
	}

}