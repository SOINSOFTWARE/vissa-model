// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.DocumentType;
import com.soinsoftware.vissa.model.PaymentType;
import com.soinsoftware.vissa.model.Person;
import com.soinsoftware.vissa.model.PersonType;
import com.soinsoftware.vissa.model.Supplier;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
public class SupplierBllTest extends TestCase {

	private SupplierBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = SupplierBll.getInstance();
		saveSupplierTestData();
	}

	public void testSelectAll() {
		final List<Supplier> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Supplier> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByDocumentNotExists() {
		final Supplier entity = bll.select("12345");
		assertNull(entity);
	}

	public void testSelectByDocumentExists() {
		final Supplier entity = bll.select("09876");
		assertNotNull(entity);
	}

	public void testUpdateSupplier() {
		Supplier entity = bll.select("09876");
		assertNotNull(entity);
		assertSame("30 Dias", entity.getPaymentTerm());

		final Supplier toUpdate = Supplier.builder(entity).paymentTerm("60 Dias").build();
		bll.save(toUpdate);

		entity = bll.select("09876");
		assertNotNull(entity);
		assertSame("60 Dias", entity.getPaymentTerm());
	}

	public void testUpdatePerson() {
		Supplier entity = bll.select("09876");
		assertNotNull(entity);
		assertEquals("Test", entity.getPerson().getName());

		final Person person = Person.builder(entity.getPerson()).name("Updated").build();
		final Supplier toUpdate = Supplier.builder(entity).person(person).build();
		bll.save(toUpdate);

		entity = bll.select("09876");
		assertNotNull(entity);
		assertEquals("Updated", entity.getPerson().getName());
	}

	public void testSaveInvalid() {
		try {
			bll.save(Supplier.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveSupplierTestData() {
		Supplier supplier = bll.select("09876");
		if (supplier == null) {
			supplier = buildSupplier();
		} else {
			final Person person = Person.builder(supplier.getPerson()).name("Test").build();
			supplier = Supplier.builder(supplier).paymentTerm("30 Dias").person(person).build();
		}
		bll.save(supplier);
	}

	private Supplier buildSupplier() {
		Person person = Person.builder().documentType(DocumentType.CC).documentNumber("09876").name("Test")
				.lastName("Test").type(PersonType.COMMON).build();
		return Supplier.builder().person(person).paymentType(PaymentType.PAID).paymentTerm("30 Dias").build();
	}
}