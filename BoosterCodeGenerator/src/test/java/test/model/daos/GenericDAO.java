package test.model.daos;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

/**
 * 
 * insert class description here.
 * 
 * Created on 09/03/2009
 * 
 * @author clopez
 * 
 * @param <T>
 */
public abstract class GenericDAO<T> {

    private Class<T> persistentClass = getDomainClass();

    private Session session;

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    protected abstract Class<T> getDomainClass();

    public void save(final T entity) {
        session.save(entity);
    }

    public void delete(final T entity) {
        session.delete(entity);
    }

    public void update(final T entity) {
        session.update(entity);
    }

    @SuppressWarnings("unchecked")
    public T findById(final Serializable id) {
        return (T) getSession().get(persistentClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return findByCriteria(new LinkedList());
    }

    public void setSession(final Session s) {
        this.session = s;
    }

    protected Session getSession() {
        if (session == null) {
            throw new IllegalStateException("Session has not been set on DAO before usage");
        }
        return session;
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(final List<Criterion> criteria) {
        Criteria crit = getSession().createCriteria(persistentClass);
        for (Criterion c : criteria) {
            crit.add(c);
        }
        return crit.list();
    }

    public void deleteById(final Serializable id) {
        T obj = this.findById(id);
        session.delete(obj);
    }

    public int count() {
        Query query = session.createQuery("select count(*) from " + persistentClass.getName() + " o");
        Long count = (Long) query.list().get(0);
        return count.intValue();

    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(final T aExampleInstance) {
        Criteria criteria = session.createCriteria(persistentClass);
        Example exampleRestriction = Example.create(aExampleInstance);
        criteria.add(exampleRestriction);
        return criteria.list();
    }

}