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
import com.soinsoftware.vissa.model.MeasurementUnitProduct;
import com.soinsoftware.vissa.model.Product;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
@SuppressWarnings("unchecked")
public class MeasurementUnitProductDao extends AbstractDataAccessibleObject<MeasurementUnitProduct, BigInteger> {

	public MeasurementUnitProductDao() throws IOException {
		super(MeasurementUnitProduct.class);
	}

	public List<MeasurementUnitProduct> select(Product product) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("product", product));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<MeasurementUnit> selectMuByProduct(Product product) {
		List<MeasurementUnit> muList = new ArrayList<>();
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("product", product));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		List<MeasurementUnitProduct> list = criteria.list();
		for (MeasurementUnitProduct muProd : list) {
			muList.add(muProd.getMeasurementUnit());
		}
		return muList;
	}

	public List<MeasurementUnitProduct> select(MeasurementUnit measurementUnit) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("measurementUnit", measurementUnit));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

}