// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.PaymentMethod;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class PaymentMethodDao extends AbstractDataAccessibleObject<PaymentMethod, BigInteger> {

	public PaymentMethodDao() throws IOException {
		super(PaymentMethod.class);
	}

	public PaymentMethod select(final String code) {
		return getSession().bySimpleNaturalId(PaymentMethod.class).load(code);
	}
}