// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.PaymentTypeDao;
import com.soinsoftware.vissa.model.PaymentType;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class PaymentTypeBll extends AbstractBll<PaymentType, BigInteger> {

	private static PaymentTypeBll instance;

	private PaymentTypeBll() throws IOException {
		super(new PaymentTypeDao());
	}

	public PaymentType select(final String code) {
		return ((PaymentTypeDao) dao).select(code);
	}

	public static PaymentTypeBll getInstance() throws IOException {
		if (instance == null) {
			instance = new PaymentTypeBll();
		}
		return instance;
	}
}