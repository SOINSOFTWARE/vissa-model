// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.Lot;
import com.soinsoftware.vissa.model.Product;

/**
 * @author Carlos Rodriguez
 * @since 21/12/2018
 */
public class LotDao extends AbstractDataAccessibleObject<Lot, BigInteger> {

	public LotDao() throws IOException {
		super(Lot.class);
	}

	public Lot select(final String code) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Lot.class).load(code);
	}

	@SuppressWarnings("unchecked")
	public List<Lot> select(Product product) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("product", product));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}