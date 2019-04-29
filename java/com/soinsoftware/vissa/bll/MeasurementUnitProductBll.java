// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.vissa.dao.MeasurementUnitProductDao;
import com.soinsoftware.vissa.model.MeasurementUnit;
import com.soinsoftware.vissa.model.MeasurementUnitProduct;
import com.soinsoftware.vissa.model.Product;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class MeasurementUnitProductBll extends AbstractBll<MeasurementUnitProduct, BigInteger> {

	private static MeasurementUnitProductBll instance;

	private MeasurementUnitProductBll() throws IOException {
		super(new MeasurementUnitProductDao());
	}

	public List<MeasurementUnitProduct> select(Product product) {
		List<MeasurementUnitProduct> muProducts = ((MeasurementUnitProductDao) dao).select(product);
		return muProducts;
	}

	public List<MeasurementUnit> selectMuByProduct(Product product) {
		List<MeasurementUnit> muList = ((MeasurementUnitProductDao) dao).selectMuByProduct(product);
		return muList;
	}

	public List<MeasurementUnitProduct> select(MeasurementUnit measurementUnit) {
		List<MeasurementUnitProduct> muProducts = ((MeasurementUnitProductDao) dao).select(measurementUnit);
		return muProducts;
	}

	public List<MeasurementUnitProduct> select(MeasurementUnit measurementUnit, Product product) {
		List<MeasurementUnitProduct> muProducts = ((MeasurementUnitProductDao) dao).select(measurementUnit, product);
		return muProducts;
	}

	public List<MeasurementUnitProduct> selectPrincipal(Product product) {
		List<MeasurementUnitProduct> muProducts = ((MeasurementUnitProductDao) dao).selectPrincipal(product);
		return muProducts;
	}

	public List<MeasurementUnitProduct> selectMuEquivalence(MeasurementUnit measurementUnit, Product product) {
		List<MeasurementUnitProduct> muProducts = ((MeasurementUnitProductDao) dao).selectMuEquivalence(measurementUnit,
				product);
		return muProducts;
	}

	public List<MeasurementUnitProduct> selectMuEquivalence(MeasurementUnit measurementUnitSource,
			MeasurementUnit measurementUnitTarget, Product product) {
		List<MeasurementUnitProduct> muProducts = ((MeasurementUnitProductDao) dao)
				.selectMuEquivalence(measurementUnitSource, measurementUnitTarget, product);
		return muProducts;
	}

	public static MeasurementUnitProductBll getInstance() throws IOException {
		if (instance == null) {
			instance = new MeasurementUnitProductBll();
		}
		return instance;
	}
}