// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.Collection;
import com.soinsoftware.vissa.model.Document;
import com.soinsoftware.vissa.model.Person;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
@SuppressWarnings("unchecked")
public class CollectionDao extends AbstractDataAccessibleObject<Collection, BigInteger> {

	public CollectionDao() throws IOException {
		super(Collection.class);
	}

	public List<Collection> select(final Date collectionDate) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.in("collectionDate"));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Collection> select(final Document document) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("document", document));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Collection> select(final Person person) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		criteria.createAlias("document", "d");
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("d.person", person));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}