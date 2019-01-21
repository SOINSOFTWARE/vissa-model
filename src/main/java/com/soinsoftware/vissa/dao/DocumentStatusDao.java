// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.DocumentStatus;
import com.soinsoftware.vissa.model.DocumentType;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
@SuppressWarnings("unchecked")
public class DocumentStatusDao extends AbstractDataAccessibleObject<DocumentStatus, BigInteger> {

	public DocumentStatusDao() throws IOException {
		super(DocumentStatus.class);
	}
	
	public List<DocumentStatus> select(String name) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("name", name));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<DocumentStatus> select(DocumentType documentType) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("documentType", documentType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

}