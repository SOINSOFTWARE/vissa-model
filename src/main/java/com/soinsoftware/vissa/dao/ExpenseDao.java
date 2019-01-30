// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.Expense;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class ExpenseDao extends AbstractDataAccessibleObject<Expense, BigInteger> {

	public ExpenseDao() throws IOException {
		super(Expense.class);
	}

	@SuppressWarnings("unchecked")
	public List<Expense> select(final String person) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("person", person));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}