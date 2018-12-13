// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import org.hibernate.Session;

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
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(PaymentType.class).load(code);
	}
}