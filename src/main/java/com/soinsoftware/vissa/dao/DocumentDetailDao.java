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
import com.soinsoftware.vissa.model.Product;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class DocumentDetailDao extends AbstractDataAccessibleObject<DocumentDetail, BigInteger> {

	public DocumentDetailDao() throws IOException {
		super(DocumentDetail.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentDetail> select(final Product product) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("product", product));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}