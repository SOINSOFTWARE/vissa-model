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
import com.soinsoftware.vissa.model.PersonType;

/**
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
@SuppressWarnings("unchecked")
public class PersonDao extends AbstractDataAccessibleObject<Person, BigInteger> {

	public PersonDao() throws IOException {
		super(Person.class);
	}

	public Person select(final String documentNumber) {
		return getSession().bySimpleNaturalId(Person.class).load(documentNumber);
	}

	public List<Person> select(final PersonType personType) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("type", personType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Person> selectByName(String name) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.like("name", "%" + name + "%"));
		predicates.add(Restrictions.like("documentNumber", "%" + name + "%"));
		predicates.add(Restrictions.like("lastName", "%" + name + "%"));
		final Criterion criterion = Restrictions.or(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

}
