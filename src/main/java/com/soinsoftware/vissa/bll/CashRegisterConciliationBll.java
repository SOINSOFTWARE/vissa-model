// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.vissa.dao.CashRegisterConciliationDao;
import com.soinsoftware.vissa.model.CashRegisterConciliation;
import com.soinsoftware.vissa.model.Person;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class CashRegisterConciliationBll extends AbstractBll<CashRegisterConciliation, BigInteger> {

	private static CashRegisterConciliationBll instance;

	private CashRegisterConciliationBll() throws IOException {
		super(new CashRegisterConciliationDao());
	}

	public List<CashRegisterConciliation> select(Person person) {
		List<CashRegisterConciliation> balances = ((CashRegisterConciliationDao) dao).select(person);
		return balances.stream().sorted(Comparator.comparing(CashRegisterConciliation::getConciliationDate))
				.collect(Collectors.toList());
	}

	public static CashRegisterConciliationBll getInstance() throws IOException {
		if (instance == null) {
			instance = new CashRegisterConciliationBll();
		}
		return instance;
	}
}