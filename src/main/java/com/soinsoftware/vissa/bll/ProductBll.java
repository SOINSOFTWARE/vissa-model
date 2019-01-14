// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.vissa.dao.ProductDao;
import com.soinsoftware.vissa.model.MeasurementUnit;
import com.soinsoftware.vissa.model.Product;
import com.soinsoftware.vissa.model.ProductCategory;
import com.soinsoftware.vissa.model.ProductType;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class ProductBll extends AbstractBll<Product, BigInteger> {

	private static ProductBll instance;

	private ProductBll() throws IOException {
		super(new ProductDao());
	}

	public Product select(final String code) {
		return ((ProductDao) dao).select(code);
	}

	public List<Product> select(ProductCategory productCategory) {
		List<Product> products = ((ProductDao) dao).select(productCategory);
		return sortedByName(products);
	}

	public List<Product> select(ProductType productType) {
		List<Product> products = ((ProductDao) dao).select(productType);
		return sortedByName(products);
	}

	public List<Product> select(MeasurementUnit measurementUnit) {
		List<Product> products = ((ProductDao) dao).select(measurementUnit);
		return sortedByName(products);
	}

	public List<Product> select(ProductCategory productCategory, ProductType productType,
			MeasurementUnit measurementUnit) {
		List<Product> products = ((ProductDao) dao).select(productCategory, productType, measurementUnit);
		return sortedByName(products);
	}

	private List<Product> sortedByName(List<Product> products) {
		return products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
	}
	
	public String selectNextProductCode() {
		return ((ProductDao) dao).selectNextProductCode();
	}

	public static ProductBll getInstance() throws IOException {
		if (instance == null) {
			instance = new ProductBll();
		}
		return instance;
	}
}