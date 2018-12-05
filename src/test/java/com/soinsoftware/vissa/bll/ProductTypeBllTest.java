// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.ProductType;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class ProductTypeBllTest extends TestCase {

	private ProductTypeBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = ProductTypeBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<ProductType> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<ProductType> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNameNotExists() {
		final ProductType entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByNameExists() {
		final ProductType entity = bll.select("Type Test");
		assertNotNull(entity);
	}

	public void testSaveInvalid() {
		try {
			bll.save(ProductType.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() {
		ProductType productType = bll.select("Type Test");
		if (productType == null) {
			productType = buildTestData();
			bll.save(productType);
		}
	}

	private ProductType buildTestData() {
		return ProductType.builder().name("Type Test").build();
	}
}