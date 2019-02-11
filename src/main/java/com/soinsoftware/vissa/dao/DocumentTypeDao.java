// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.DocumentType;
import com.soinsoftware.vissa.model.ETransactionType;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
@SuppressWarnings("unchecked")
public class DocumentTypeDao extends AbstractDataAccessibleObject<DocumentType, BigInteger> {

	public DocumentTypeDao() throws IOException {
		super(DocumentType.class);
	}

	public DocumentType select(final String code) {
		return getSession().bySimpleNaturalId(DocumentType.class).load(code);
	}

	public List<DocumentType> select(ETransactionType transactionType) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("transactionType", transactionType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
	
	

}