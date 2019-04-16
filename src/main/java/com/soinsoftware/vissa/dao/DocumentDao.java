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

import com.soinsoftware.vissa.common.EComparatorType;
import com.soinsoftware.vissa.model.Document;
import com.soinsoftware.vissa.model.DocumentType;
import com.soinsoftware.vissa.model.PaymentType;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
@SuppressWarnings("unchecked")
public class DocumentDao extends AbstractDataAccessibleObject<Document, BigInteger> {

	public DocumentDao() throws IOException {
		super(Document.class);
	}

	public Document select(final String code) {
		return getSession().bySimpleNaturalId(Document.class).load(code);
	}

	public List<Document> select(final DocumentType documentType) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("documentType", documentType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public Document select(String code, DocumentType documentType) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("code", code));
		predicates.add(Restrictions.eq("documentType", documentType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return (Document) criteria.uniqueResult();
	}

	public Document select(String code, List<DocumentType> documentType) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("code", code));
		predicates.add(Restrictions.in("documentType", documentType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return (Document) criteria.uniqueResult();
	}

	public List<Document> select(final List<DocumentType> documentTypes) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.in("documentType", documentTypes));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Document> select(final Date documentDate, Date iniDate, Date finalDate) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.between("documentDate", iniDate, finalDate));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Document> select(final PaymentType paymentType) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("paymentType", paymentType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Document> selectByPaymentTypes(final List<PaymentType> paymentTypes) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.in("paymentType", paymentTypes));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Document> select(final List<DocumentType> documentTypes, List<PaymentType> paymentTypes) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.in("documentType", documentTypes));
		predicates.add(Restrictions.in("paymentType", paymentTypes));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Document> selectToExpire(final List<DocumentType> documentTypes,
			Date expirationDate, String paymentStatus, EComparatorType comparator) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.in("documentType", documentTypes));
		predicates.add(Restrictions.eq("paymentStatus", paymentStatus));
		if (comparator.equals(EComparatorType.EQ)) {
			predicates.add(Restrictions.eq("expirationDate", expirationDate));
		}
		if (comparator.equals(EComparatorType.LE)) {
			predicates.add(Restrictions.le("expirationDate", expirationDate));
		}
		if (comparator.equals(EComparatorType.GE)) {
			predicates.add(Restrictions.ge("expirationDate", expirationDate));
		}

		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Document> selectExpired(final List<DocumentType> documentTypes, String paymentStatus) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.in("documentType", documentTypes));
		predicates.add(Restrictions.eq("paymentStatus", paymentStatus));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}