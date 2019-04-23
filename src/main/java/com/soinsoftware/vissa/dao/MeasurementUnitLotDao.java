// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.Lot;
import com.soinsoftware.vissa.model.MeasurementUnitLot;
import com.soinsoftware.vissa.model.MeasurementUnitProduct;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
@SuppressWarnings("unchecked")
public class MeasurementUnitLotDao extends AbstractDataAccessibleObject<MeasurementUnitLot, BigInteger> {

	public MeasurementUnitLotDao() throws IOException {
		super(MeasurementUnitLot.class);
	}

	public List<MeasurementUnitLot> select(Lot lot) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("lot", lot));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<MeasurementUnitLot> select(MeasurementUnitProduct muProduct) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("muProduct", muProduct));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<MeasurementUnitProduct> select(MeasurementUnitProduct muProduct, Lot lot) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("muProduct", muProduct));
		predicates.add(Restrictions.eq("lot", lot));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

}