// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.PaymentMethodDao;
import com.soinsoftware.vissa.model.PaymentMethod;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class PaymentMethodBll extends AbstractBll<PaymentMethod, BigInteger> {

	private static PaymentMethodBll instance;

	private PaymentMethodBll() throws IOException {
		super(new PaymentMethodDao());
	}

	public PaymentMethod select(final String code) {
		return ((PaymentMethodDao) dao).select(code);
	}

	public static PaymentMethodBll getInstance() throws IOException {
		if (instance == null) {
			instance = new PaymentMethodBll();
		}
		return instance;
	}
}