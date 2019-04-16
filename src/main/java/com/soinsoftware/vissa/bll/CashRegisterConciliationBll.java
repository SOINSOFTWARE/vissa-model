// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.soinsoftware.vissa.dao.CashRegisterConciliationDao;
import com.soinsoftware.vissa.model.CashConciliation;
import com.soinsoftware.vissa.model.Person;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class CashRegisterConciliationBll extends AbstractBll<CashConciliation, BigInteger> {

	private static CashRegisterConciliationBll instance;

	private CashRegisterConciliationBll() throws IOException {
		super(new CashRegisterConciliationDao());
	}

	public List<CashConciliation> select(Person person) {
		return ((CashRegisterConciliationDao) dao).select(person);		
	}
	
	public List<CashConciliation> select(Date conciliationDate) {
		return ((CashRegisterConciliationDao) dao).select(conciliationDate);		
	}

	public List<CashConciliation> select(Person person, Date conciliationDate) {
		return ((CashRegisterConciliationDao) dao).select(person, conciliationDate);
	}

	public static CashRegisterConciliationBll getInstance() throws IOException {
		if (instance == null) {
			instance = new CashRegisterConciliationBll();
		}
		return instance;
	}
}