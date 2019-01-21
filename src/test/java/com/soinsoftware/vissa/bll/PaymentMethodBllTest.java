// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.PaymentMethod;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class PaymentMethodBllTest extends TestCase {

	private PaymentMethodBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = PaymentMethodBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<PaymentMethod> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<PaymentMethod> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCodeNotExists() {
		final PaymentMethod entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByCodeExists() {
		final PaymentMethod entity = bll.select("CASH");
		assertNotNull(entity);
	}

	public void testSaveInvalid() {
		try {
			bll.save(PaymentMethod.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() {
		PaymentMethod paymentMethod = bll.select("CASH");
		if (paymentMethod == null) {
			paymentMethod = buildTestData();
			bll.save(paymentMethod);
		}
	}

	private PaymentMethod buildTestData() {
		return PaymentMethod.builder().code("CASH").name("Efectivo").build();
	}
}