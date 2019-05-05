// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.StoreDao;
import com.soinsoftware.vissa.model.Store;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class StoreBll extends AbstractBll<Store, BigInteger> {

	private static StoreBll instance;

	private StoreBll() throws IOException {
		super(new StoreDao());
	}

	public static StoreBll getInstance() throws IOException {
		if (instance == null) {
			instance = new StoreBll();
		}
		return instance;
	}
}