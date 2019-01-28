package com.soinsoftware.vissa.bll;

import java.util.List;

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
		((CommonData) entity).validate();
		if (((CommonData) entity).isNew()) {
			System.out.println("persist entity" );
			dao.persist(entity);
		} else {
			System.out.println("update entity");
			dao.update(entity);
		}
		
	}

	public void update(final T entity) {

		System.out.println("update entity");
		dao.update(entity);

	}

	public void delete(final T entity) {
		dao.delete(entity);
	}

	public void rollback() {
		dao.rollbackTransaction();
	}

	public void closeDbConnection() {
		dao.close();
	}
}