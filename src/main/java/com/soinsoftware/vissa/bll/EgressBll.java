// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.vissa.dao.EgressDao;
import com.soinsoftware.vissa.model.Egress;
import com.soinsoftware.vissa.model.EgressType;
import com.soinsoftware.vissa.model.Person;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class EgressBll extends AbstractBll<Egress, BigInteger> {

	private static EgressBll instance;

	private EgressBll() throws IOException {
		super(new EgressDao());
	}

	public List<Egress> select(Date egressDate) {
		List<Egress> egresses =  ((EgressDao) dao).select(egressDate);
		return egresses.stream().sorted(Comparator.comparing(Egress::getEgressDate).reversed())
				.collect(Collectors.toList());
	}

	public List<Egress> select(Person person) {
		return ((EgressDao) dao).select(person);
	}

	public List<Egress> select(EgressType egresType) {
		return ((EgressDao) dao).select(egresType);
	}

	public static EgressBll getInstance() throws IOException {
		if (instance == null) {
			instance = new EgressBll();
		}
		return instance;
	}
}