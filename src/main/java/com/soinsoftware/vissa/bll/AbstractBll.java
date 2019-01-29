package com.soinsoftware.vissa.bll;

import java.util.List;

import org.hibernate.Transaction;

import com.soinsoftware.vissa.dao.DataAccessibleObject;
import com.soinsoftware.vissa.model.CommonData;

/**
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
public abstract class AbstractBll<T, P> {

	protected DataAccessibleObject<T, P> dao;

	protected AbstractBll(DataAccessibleObject<T, P> dao) {
		super();
		this.dao = dao;
	}

	public List<T> selectAll() {
		return dao.selectAll();
	}

	public List<T> selectAll(boolean enabled) {
		return dao.selectAll(enabled);
	}

	public T selectById(P pk) {
		return (T) dao.selectById(pk);
	}

	public void save(final T entity) {
		save(entity, true);
	}
	
	public void save(final T entity, final boolean mustCommit) {
		((CommonData) entity).validate();
		if (((CommonData) entity).isNew()) {
			System.out.println("persist entity" );
			if (mustCommit) {
				dao.persist(entity);
			} else {
				dao.persist(getCurrentTransaction(), entity);
			}
		} else {
			System.out.println("update entity");
			if (mustCommit) {
				dao.update(entity);
			} else {
				dao.update(getCurrentTransaction(), entity);
			}
		}
	}

	public void update(final T entity) {
		update(entity, true);
	}
	
	public void update(final T entity, final boolean mustCommit) {
		System.out.println("update entity");
		if (mustCommit) {
			dao.update(entity);
		} else {
			dao.update(getCurrentTransaction(), entity);
		}
	}

	public void delete(final T entity) {
		dao.delete(entity);
	}

	public void rollback() {
		dao.rollbackTransaction();
	}
	
	public void commit() {
		getCurrentTransaction().commit();
		dao.getSession().flush();
	}
	
	public Transaction getCurrentTransaction() {
		return dao.getSession().getTransaction();
	}

	public void closeDbConnection() {
		dao.close();
	}
}