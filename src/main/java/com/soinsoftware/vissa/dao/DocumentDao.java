// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.Document;
import com.soinsoftware.vissa.model.DocumentType;

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

	public List<Document> select(final List<DocumentType> documentTypes) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.in("documentType", documentTypes));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}