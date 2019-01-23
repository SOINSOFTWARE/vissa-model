// Soin Software, 2019
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.Menu;
import com.soinsoftware.vissa.model.Permission;
import com.soinsoftware.vissa.model.Role;

/**
 * @author Carlos Rodriguez
 * @since 23/01/2019
 */
@SuppressWarnings("unchecked")
public class PermissionDao extends AbstractDataAccessibleObject<Permission, BigInteger> {

	public PermissionDao() throws IOException {
		super(Permission.class);
	}
	
	public List<Permission> select(Role role) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("role", role));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
	
	public List<Permission> select(Menu menu) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("menu", menu));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
	
	public Permission select(Role role, Menu menu) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("role", role));
		predicates.add(Restrictions.eq("menu", menu));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return (Permission) criteria.uniqueResult();
	}
}