// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.EgressType;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class EgressTypeDao extends AbstractDataAccessibleObject<EgressType, BigInteger> {

	public EgressTypeDao() throws IOException {
		super(EgressType.class);
	}

	public EgressType select(final String code) {
		return getSession().bySimpleNaturalId(EgressType.class).load(code);
	}
}