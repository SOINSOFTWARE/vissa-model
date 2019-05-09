// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.Store;
import com.soinsoftware.vissa.model.User;
import com.soinsoftware.vissa.model.UserStore;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class UserStoreDao extends AbstractDataAccessibleObject<UserStore, BigInteger> {

	public UserStoreDao() throws IOException {
		super(UserStore.class);
	}

	@SuppressWarnings("unchecked")
	public List<UserStore> select(User user) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("user", user));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<UserStore> select(Store store) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("store", store));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

}