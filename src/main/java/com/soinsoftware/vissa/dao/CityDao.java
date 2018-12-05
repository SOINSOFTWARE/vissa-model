// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.City;
import com.soinsoftware.vissa.model.State;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class CityDao extends AbstractDataAccessibleObject<City, BigInteger> {

	public CityDao() throws IOException {
		super(City.class);
	}

	@SuppressWarnings("unchecked")
	public List<City> select(State state) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("state", state));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}