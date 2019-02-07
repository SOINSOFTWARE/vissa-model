// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.EgressTypeDao;
import com.soinsoftware.vissa.model.EgressType;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class EgressTypeBll extends AbstractBll<EgressType, BigInteger> {

	private static EgressTypeBll instance;

	private EgressTypeBll() throws IOException {
		super(new EgressTypeDao());
	}

	public EgressType select(final String code) {
		return ((EgressTypeDao) dao).select(code);
	}

	public static EgressTypeBll getInstance() throws IOException {
		if (instance == null) {
			instance = new EgressTypeBll();
		}
		return instance;
	}
}