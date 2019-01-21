// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.PaymentType;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class PaymentTypeDao extends AbstractDataAccessibleObject<PaymentType, BigInteger> {

	public PaymentTypeDao() throws IOException {
		super(PaymentType.class);
	}

	public PaymentType select(final String code) {
		return getSession().bySimpleNaturalId(PaymentType.class).load(code);
	}
}