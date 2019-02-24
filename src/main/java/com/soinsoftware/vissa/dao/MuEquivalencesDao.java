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
import com.soinsoftware.vissa.model.MuEquivalences;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
@SuppressWarnings("unchecked")
public class MuEquivalencesDao extends AbstractDataAccessibleObject<MuEquivalencesDao, BigInteger> {

	public MuEquivalencesDao() throws IOException {
		super(MuEquivalencesDao.class);
	}

	public List<MuEquivalences> selectByMuSource(MeasurementUnit muSource) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("muSource", muSource));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<MuEquivalences> selectByMuTarget(MeasurementUnit muTarget) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("muTarget", muTarget));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

}