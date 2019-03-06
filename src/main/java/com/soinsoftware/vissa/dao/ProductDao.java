// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
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
		return getSession().bySimpleNaturalId(Product.class).load(code);
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

	public List<Product> selectByName(String name) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(false);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.like("name", name + "%"));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

}