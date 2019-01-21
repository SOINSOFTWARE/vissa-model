// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.vissa.dao.CityDao;
import com.soinsoftware.vissa.model.City;
import com.soinsoftware.vissa.model.State;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class CityBll extends AbstractBll<City, BigInteger> {

	private static CityBll instance;

	private CityBll() throws IOException {
		super(new CityDao());
	}

	public List<City> select(State state) {
		List<City> cities = ((CityDao) dao).select(state);
		return cities.stream().sorted(Comparator.comparing(City::getName)).collect(Collectors.toList());
	}

	public static CityBll getInstance() throws IOException {
		if (instance == null) {
			instance = new CityBll();
		}
		return instance;
	}
}