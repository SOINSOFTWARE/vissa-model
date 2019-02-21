// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.vissa.dao.LotDao;
import com.soinsoftware.vissa.model.Lot;
import com.soinsoftware.vissa.model.Product;
import com.soinsoftware.vissa.model.Warehouse;

/**
 * @author Carlos Rodriguez
 * @since 21/12/2018
 */
public class LotBll extends AbstractBll<Lot, BigInteger> {

	private static LotBll instance;

	private LotBll() throws IOException {
		super(new LotDao());
	}

	public Lot select(final String code) {
		return ((LotDao) dao).select(code);
	}

	public List<Lot> select(Product product) {
		List<Lot> lots = ((LotDao) dao).select(product);
		return lots;
	}

	public Lot select(String code, Product product) {
		Lot lot = ((LotDao) dao).select(code, product);
		return lot;
	}

	public List<Lot> select(Warehouse warehouse) {
		List<Lot> lots = ((LotDao) dao).select(warehouse);
		return lots;
	}

	/*
	 * private List<Lot> sortedByExpiration(List<Lot> lots) { return
	 * lots.stream().sorted(Comparator.comparing(Lot::getExpirationDate)).collect(
	 * Collectors.toList()); }
	 ****/

	public Lot getLastLotByProduct(Product product) {
		Lot lot = null;
		List<Lot> lots = ((LotDao) dao).select(product);
		List<Lot> lotsByCode = lots.stream().sorted(Comparator.comparing(Lot::getCode)).collect(Collectors.toList());
		int size = lotsByCode.size();
		if (size > 0) {
			lot = lotsByCode.get(size - 1);
		}
		return lot;
	}

	public static LotBll getInstance() throws IOException {
		if (instance == null) {
			instance = new LotBll();
		}
		return instance;
	}
}