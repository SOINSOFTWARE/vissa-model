// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.User;

/**
 * @author Carlos Rodriguez
 * @since 26/12/2018
 */
public class UserDao extends AbstractDataAccessibleObject<User, BigInteger> {

	public UserDao() throws IOException {
		super(User.class);
	}

	public User select(final String login) {
		return getSession().bySimpleNaturalId(User.class).load(login);
	}

	public User select(String login, String password) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("login", login));
		predicates.add(Restrictions.eq("password", password));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return (User) criteria.uniqueResult();
	}
}