// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.vissa.dao.StateDao;
import com.soinsoftware.vissa.model.Country;
import com.soinsoftware.vissa.model.State;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class StateBll extends AbstractBll<State, BigInteger> {

	private static StateBll instance;

	private StateBll() throws IOException {
		super(new StateDao());
	}
	
	public List<State> select(Country country) {
		List<State> states = ((StateDao) dao).select(country);
		return states.stream().sorted(Comparator.comparing(State::getName)).collect(Collectors.toList());
	}

	public static StateBll getInstance() throws IOException {
		if (instance == null) {
			instance = new StateBll();
		}
		return instance;
	}
}