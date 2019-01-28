// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.DocumentDetail;
import com.soinsoftware.vissa.model.DocumentDetailLot;
import com.soinsoftware.vissa.model.Lot;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class DocumentDetailLotDao extends AbstractDataAccessibleObject<DocumentDetailLot, BigInteger> {

	public DocumentDetailLotDao() throws IOException {
		super(DocumentDetailLot.class);
	}
	
	
	public DocumentDetailLot select(final DocumentDetail documentDetail) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("documentDetail", documentDetail));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return (DocumentDetailLot) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentDetailLot> select(final Lot lot) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("lot", lot));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}