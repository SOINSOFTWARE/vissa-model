// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.MeasurementUnit;
import com.soinsoftware.vissa.model.MuEquivalence;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
@SuppressWarnings("unchecked")
public class MuEquivalenceDao extends AbstractDataAccessibleObject<MuEquivalenceDao, BigInteger> {

	public MuEquivalenceDao() throws IOException {
		super(MuEquivalenceDao.class);
	}

	public List<MuEquivalence> selectByMuSource(MeasurementUnit muSource) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("muSource", muSource));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<MuEquivalence> selectByMuTarget(MeasurementUnit muTarget) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("muTarget", muTarget));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public MuEquivalence select(MeasurementUnit muSource, MeasurementUnit muTarget) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("muSource", muSource));
		predicates.add(Restrictions.eq("muTarget", muTarget));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return (MuEquivalence) criteria.uniqueResult();
	}

}