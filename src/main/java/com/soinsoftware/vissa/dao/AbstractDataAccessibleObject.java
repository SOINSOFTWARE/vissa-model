package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.vissa.manager.AbstractManagerFactory;
import com.soinsoftware.vissa.manager.VissaManagerFactory;

/**
 * 
 * @param <T>
 *            Class that represents the model.
 * @param <P>
 *            Class that represents the primary key.
 * 
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
@Transactional
@SuppressWarnings("unchecked")
public abstract class AbstractDataAccessibleObject<T, P> implements DataAccessibleObject<T, P> {

	protected final Logger log = Logger.getLogger(AbstractDataAccessibleObject.class);
	protected final EntityManager manager;
	private final Class<T> clazz;

	/**
	 * Default constructor that must be used for all DAO implementations.
	 * 
	 * @throws IOException
	 */
	public AbstractDataAccessibleObject(final Class<T> clazz) throws IOException {
		super();
		final AbstractManagerFactory factory = VissaManagerFactory.getInstance();
		this.manager = factory.createEntityManager();
		this.clazz = clazz;
	}

	@Override
	public T selectById(P pk) {
		return manager.find(clazz, pk);
	}

	@Override
	public List<T> selectAll() {
		final Criteria criteria = buildCriteria();
		return criteria.list();
	}

	@Override
	public List<T> selectAll(final boolean archived) {
		final Criteria criteria = buildCriteriaWithArchivedRestriction(archived);
		return criteria.list();
	}

	@Override
	public void persist(T record) {
		try {
			persist(manager.getTransaction(), record);
		} finally {
			manager.getTransaction().commit();
		}
	}

	@Override
	public void persist(final EntityTransaction transaction, final T record) {
		log.info("Persisting object: " + record.toString());
		if (!transaction.isActive()) {
			transaction.begin();
		}
		manager.persist(record);
	}

	@Override
	public void update(T record) {
		try {
			update(manager.getTransaction(), record);
		} finally {
			manager.getTransaction().commit();
		}
	}

	@Override
	public void update(final EntityTransaction transaction, final T record) {
		log.info("Updating object: " + record.toString());
		if (!transaction.isActive()) {
			transaction.begin();
		}
		T merged = manager.merge(record);
		persist(transaction, merged);
	}

	@Override
	public void delete(final T record) {
		log.info("Deleting object: " + record.toString());
		manager.remove(record);
		manager.getTransaction().commit();
	}

	@Override
	public void rollbackTransaction(final EntityTransaction transaction) {
		if (transaction != null) {
			transaction.rollback();
		}
	}

	@Override
	public void close() {
		manager.close();
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(clazz);
	}

	/**
	 * Builds a {@link Criteria} object with a restriction.
	 * 
	 * @param enabled
	 *            filter list of data using the archived column.
	 * @return {@link Criteria} object.
	 */
	public Criteria buildCriteriaWithArchivedRestriction(final boolean enabled) {
		final Criteria criteria = buildCriteria();
		final Criterion criterion = Restrictions.eq("archived", enabled);
		criteria.add(criterion);
		return criteria;
	}

	protected Criterion[] buildPredicates(final List<Criterion> predicates) {
		final Criterion[] predicateArray = new Criterion[predicates.size()];
		for (int i = 0; i < predicates.size(); i++) {
			predicateArray[i] = predicates.get(i);
		}
		return predicateArray;
	}
}