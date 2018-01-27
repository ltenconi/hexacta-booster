package com.hexacta.model.daos;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

/**
 * 
 * insert class description here.
 * 
 * Created on 09/03/2009
 * @author clopez
 *
 * @param <T>
 */
public abstract class GenericDAO<T> {

	protected Class<T> persistentClass;
	private Session session;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void save(T entity){
		session.save(entity);		
	}

	public void delete(T entity){
		session.delete(entity);		
	}

	public void update(T entity){		
		session.update(entity);
	}

	@SuppressWarnings("unchecked")
	public T findById(Serializable id){
		return ((T) getSession().get(this.persistentClass, id));
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		return findByCriteria(new LinkedList());
	}

	public void setSession(Session s){
		this.session = s;
	}

	protected Session getSession() {
		if (session == null)
			throw new IllegalStateException("Session has not been set on DAO before usage");
		return session;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(List<Criterion> criteria) {
		Criteria crit = getSession().createCriteria(this.persistentClass);
		for (Criterion c : criteria) {
			crit.add(c);
		}
		return crit.list();
	}

}