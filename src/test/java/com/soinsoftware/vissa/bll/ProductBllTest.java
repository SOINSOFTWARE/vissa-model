// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.MeasurementUnit;
import com.soinsoftware.vissa.model.Product;
import com.soinsoftware.vissa.model.ProductCategory;
import com.soinsoftware.vissa.model.ProductType;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class ProductBllTest extends TestCase {

	private ProductBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = ProductBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<Product> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Product> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCodeNotExists() {
		final Product entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByCodeExists() {
		final Product entity = bll.select("TEST");
		assertNotNull(entity);
	}

	public void testSelectByProductCategory() throws IOException {
		ProductCategory productCategory = ProductCategoryBll.getInstance().select("Category Test");
		final List<Product> entities = bll.select(productCategory);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByProductType() throws IOException {
		ProductType productType = ProductTypeBll.getInstance().select("Type Test");
		final List<Product> entities = bll.select(productType);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByMeasurementUnit() throws IOException {
		MeasurementUnit measurementUnit = MeasurementUnitBll.getInstance().select("Measurement unit Test");
		final List<Product> entities = bll.select(measurementUnit);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByAllCriteria() throws IOException {
		ProductCategory productCategory = ProductCategoryBll.getInstance().select("Category Test");
		ProductType productType = ProductTypeBll.getInstance().select("Type Test");
		MeasurementUnit measurementUnit = MeasurementUnitBll.getInstance().select("TEST");
		final List<Product> entities = bll.select(productCategory, productType, measurementUnit);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCategoryAndType() throws IOException {
		ProductCategory productCategory = ProductCategoryBll.getInstance().select("Category Test");
		ProductType productType = ProductTypeBll.getInstance().select("Type Test");
		final List<Product> entities = bll.select(productCategory, productType, null);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCategoryAndUnit() throws IOException {
		ProductCategory productCategory = ProductCategoryBll.getInstance().select("Category Test");
		MeasurementUnit measurementUnit = MeasurementUnitBll.getInstance().select("TEST");
		final List<Product> entities = bll.select(productCategory, null, measurementUnit);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByTypeAndUnit() throws IOException {
		ProductType productType = ProductTypeBll.getInstance().select("Type Test");
		MeasurementUnit measurementUnit = MeasurementUnitBll.getInstance().select("TEST");
		final List<Product> entities = bll.select(null, productType, measurementUnit);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByAllCriteriaWithNulls() throws IOException {
		final List<Product> entities = bll.select(null, null, null);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSaveInvalid() {
		try {
			bll.save(Product.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() throws IOException {
		Product product = bll.select("TEST");
		if (product == null) {
			product = buildTestData();
			bll.save(product);
		}
		Product productWithClasses = bll.select("TEST-1");
		if (productWithClasses == null) {
			productWithClasses = buildTestDataWithAllChildrenClasses();
			bll.save(productWithClasses);
		}
	}

	private Product buildTestData() {
		return Product.builder().code("TEST").name("Product Test").build();
	}

	private Product buildTestDataWithAllChildrenClasses() throws IOException {
		ProductCategory productCategory = ProductCategoryBll.getInstance().select("Category Test");
		ProductType productType = ProductTypeBll.getInstance().select("Type Test");
		MeasurementUnit measurementUnit = MeasurementUnitBll.getInstance().select("Measurement unit Test");
		return Product.builder().code("TEST-1").name("Product with classes").category(productCategory).type(productType)
				.measurementUnit(measurementUnit).build();
	}
}