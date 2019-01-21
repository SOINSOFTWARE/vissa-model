package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.vissa.manager.AbstractManagerFactory;
import com.soinsoftware.vissa.manager.VissaManagerFactory;

/**
 * 
 * @param <T> Class that represents the model.
 * @param <P> Class that represents the primary key.
 * 
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
@Transactional
@SuppressWarnings("unchecked")
public abstract class AbstractDataAccessibleObject<T, P> implements DataAccessibleObject<T, P> {

	protected final Logger log = Logger.getLogger(AbstractDataAccessibleObject.class);
	private final EntityManager manager;
	private final Class<T> clazz;
	private static Session session;

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
			persist(getSession().getTransaction(), record);
		} finally {
			getSession().getTransaction().commit();
			getSession().flush();
		}
	}

	@Override
	public void persist(final Transaction transaction, final T record) {
		log.info("Persisting object: " + record.toString());
		if (!transaction.getStatus().equals(TransactionStatus.ACTIVE)) {
			transaction.begin();
		}
		getSession().persist(record);
	}

	@Override
	public void update(T record) {
		try {
			update(getSession().getTransaction(), record);
		} finally {
			getSession().getTransaction().commit();
		}
	}

	@Override
	public void update(final Transaction transaction, final T record) {
		log.info("Updating object: " + record.toString());
		if (!transaction.getStatus().equals(TransactionStatus.ACTIVE)) {
			transaction.begin();
		}
		T merged = (T) getSession().merge(record);
		persist(transaction, merged);
	}

	@Override
	public void delete(final T record) {
		log.info("Deleting object: " + record.toString());
		getSession().delete(record);
		getSession().getTransaction().commit();
		session.flush();
	}

	@Override
	public void rollbackTransaction() {
		EntityTransaction transaction = manager.getTransaction();
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
		return getSession().createCriteria(clazz);
	}

	public Session getSession() {
		if (session == null || !session.isOpen()) {
			session = manager.unwrap(Session.class);
		}
		return session;
	}

	/**
	 * Builds a {@link Criteria} object with a restriction.
	 * 
	 * @param enabled filter list of data using the archived column.
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