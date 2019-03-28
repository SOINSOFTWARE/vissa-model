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
import com.soinsoftware.vissa.model.PaymentDocumentType;
import com.soinsoftware.vissa.model.PaymentType;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
@SuppressWarnings("unchecked")
public class PaymentDocumentTypeDao extends AbstractDataAccessibleObject<PaymentDocumentType, BigInteger> {

	public PaymentDocumentTypeDao() throws IOException {
		super(PaymentDocumentType.class);
	}

	public List<DocumentType> select(PaymentType paymentType) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("paymentType", paymentType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}


	
	public List<PaymentDocumentType> select(DocumentType documentType) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("documentType", documentType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
	
	public PaymentDocumentType select(DocumentType documentType, PaymentType paymentType) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("documentType", documentType));
		predicates.add(Restrictions.eq("paymentType", paymentType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return (PaymentDocumentType)criteria.uniqueResult();
	}
}