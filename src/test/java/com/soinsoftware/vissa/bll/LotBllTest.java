// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.Lot;
import com.soinsoftware.vissa.model.Product;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 21/12/2018
 */
public class LotBllTest extends TestCase {

	private LotBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = LotBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<Lot> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Lot> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCodeNotExists() {
		final Lot entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByCodeExists() {
		final Lot entity = bll.select("TEST");
		assertNotNull(entity);
	}

	public void testSelectByProduct() throws IOException {
		Product product = ProductBll.getInstance().select("TEST");
		final List<Lot> entities = bll.select(product);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSaveInvalid() {
		try {
			bll.save(Lot.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() throws IOException {
		Lot lot = bll.select("TEST");
		if (lot == null) {
			lot = buildTestData();
		}
		bll.save(lot);
	}

	private Lot buildTestData() throws IOException {
		Product product = ProductBll.getInstance().select("TEST");
		return Lot.builder().code("TEST").product(product).quantity(1).build();
	}
}