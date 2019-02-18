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
		List<MeasurementUnitProduct> products = ((MeasurementUnitProductDao) dao).select(product);
		return products;
	}

	public List<MeasurementUnit> selectMuByProduct(Product product) {
		List<MeasurementUnit> muList = ((MeasurementUnitProductDao) dao).selectMuByProduct(product);
		return muList;
	}

	public List<MeasurementUnitProduct> select(MeasurementUnit measurementUnit) {
		List<MeasurementUnitProduct> products = ((MeasurementUnitProductDao) dao).select(measurementUnit);
		return products;
	}

	public static MeasurementUnitProductBll getInstance() throws IOException {
		if (instance == null) {
			instance = new MeasurementUnitProductBll();
		}
		return instance;
	}
}