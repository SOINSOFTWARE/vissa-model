// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.Country;
import com.soinsoftware.vissa.model.State;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class StateDao extends AbstractDataAccessibleObject<State, BigInteger> {

	public StateDao() throws IOException {
		super(State.class);
	}

	@SuppressWarnings("unchecked")
	public List<State> select(Country country) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("country", country));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}