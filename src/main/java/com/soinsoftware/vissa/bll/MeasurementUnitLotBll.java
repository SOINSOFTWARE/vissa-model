// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.vissa.dao.MeasurementUnitLotDao;
import com.soinsoftware.vissa.model.Lot;
import com.soinsoftware.vissa.model.MeasurementUnitLot;
import com.soinsoftware.vissa.model.MeasurementUnitProduct;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class MeasurementUnitLotBll extends AbstractBll<MeasurementUnitLot, BigInteger> {

	private static MeasurementUnitLotBll instance;

	private MeasurementUnitLotBll() throws IOException {
		super(new MeasurementUnitLotDao());
	}

	public List<MeasurementUnitLot> select(Lot lot) {
		List<MeasurementUnitLot> muLots = ((MeasurementUnitLotDao) dao).select(lot);
		return muLots;
	}

	public List<MeasurementUnitLot> select(MeasurementUnitProduct muProduct) {
		List<MeasurementUnitLot> muLots = ((MeasurementUnitLotDao) dao).select(muProduct);
		return muLots;
	}

	public static MeasurementUnitLotBll getInstance() throws IOException {
		if (instance == null) {
			instance = new MeasurementUnitLotBll();
		}
		return instance;
	}
}