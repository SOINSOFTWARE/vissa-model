// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.CashRegisterConciliation;
import com.soinsoftware.vissa.model.Person;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class CashRegisterConciliationDao extends AbstractDataAccessibleObject<CashRegisterConciliation, BigInteger> {

	public CashRegisterConciliationDao() throws IOException {
		super(CashRegisterConciliation.class);
	}

	@SuppressWarnings("unchecked")
	public List<CashRegisterConciliation> select(final Person person) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("person", person));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}