// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.PaymentType;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class PaymentTypeBllTest extends TestCase {

	private PaymentTypeBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = PaymentTypeBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<PaymentType> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<PaymentType> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCodeNotExists() {
		final PaymentType entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByCodeExists() {
		final PaymentType entity = bll.select("PAID");
		assertNotNull(entity);
	}

	public void testSaveInvalid() {
		try {
			bll.save(PaymentType.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() {
		PaymentType paymentType = bll.select("PAID");
		if (paymentType == null) {
			paymentType = buildTestData();
			bll.save(paymentType);
		}
	}

	private PaymentType buildTestData() {
		return PaymentType.builder().code("PAID").name("Pago en el momento").build();
	}
}