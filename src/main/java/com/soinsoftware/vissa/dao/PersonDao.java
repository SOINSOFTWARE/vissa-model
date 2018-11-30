// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.Person;
import com.soinsoftware.vissa.model.Supplier;

/**
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
public class PersonDao extends AbstractDataAccessibleObject<Person, BigInteger> {

	public PersonDao() throws IOException {
		super(Person.class);
	}

	public Supplier select(final String documentNumber) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		criteria.createAlias("person", "p");
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("p.documentNumber", documentNumber));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return (Supplier) criteria.uniqueResult();
	}
}