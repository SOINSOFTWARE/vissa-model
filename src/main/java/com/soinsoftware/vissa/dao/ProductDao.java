// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.vissa.model.MeasurementUnit;
import com.soinsoftware.vissa.model.Product;
import com.soinsoftware.vissa.model.ProductCategory;
import com.soinsoftware.vissa.model.ProductType;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
@SuppressWarnings("unchecked")
public class ProductDao extends AbstractDataAccessibleObject<Product, BigInteger> {

	public ProductDao() throws IOException {
		super(Product.class);
	}

	public Product select(final String code) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Product.class).load(code);
	}

	public List<Product> select(ProductCategory productCategory) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("category", productCategory));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Product> select(ProductType productType) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("type", productType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Product> select(MeasurementUnit measurementUnit) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("measurementUnit", measurementUnit));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Product> select(ProductCategory productCategory, ProductType productType,
			MeasurementUnit measurementUnit) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		if (productCategory != null) {
			predicates.add(Restrictions.eq("category", productCategory));
		}
		if (productType != null) {
			predicates.add(Restrictions.eq("type", productType));
		}
		if (measurementUnit != null) {
			predicates.add(Restrictions.eq("measurementUnit", measurementUnit));
		}
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
	
	public String selectNextProductCode() {
		Query query = manager.createNativeQuery("SELECT max(cast(id as signed)) + 1 from product" );
		BigInteger code = (BigInteger)  query.getSingleResult();	
		return String.valueOf(code);
	}
}