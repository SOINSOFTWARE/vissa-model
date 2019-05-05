// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.Store;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class StoreDao extends AbstractDataAccessibleObject<Store, BigInteger> {

	public StoreDao() throws IOException {
		super(Store.class);
	}

}