// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.ProductCategory;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class ProductCategoryBllTest extends TestCase {

	private ProductCategoryBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = ProductCategoryBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<ProductCategory> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<ProductCategory> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNameNotExists() {
		final ProductCategory entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByNameExists() {
		final ProductCategory entity = bll.select("Category Test");
		assertNotNull(entity);
	}

	public void testSaveInvalid() {
		try {
			bll.save(ProductCategory.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() {
		ProductCategory productCategory = bll.select("Category Test");
		if (productCategory == null) {
			productCategory = buildTestData();
			bll.save(productCategory);
		}
	}

	private ProductCategory buildTestData() {
		return ProductCategory.builder().name("Category Test").build();
	}
}