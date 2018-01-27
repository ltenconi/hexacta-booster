package com.hexacta.booster.codegeneration.template.persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import com.hexacta.booster.codegeneration.template.model.MetaTemplate;

/***/
public class MetaTemplateDAO {

    protected Session session;

    public void save(final MetaTemplate entity) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void delete(final MetaTemplate entity) {
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void deleteById(final Serializable id) {

        Transaction transaction = session.beginTransaction();
        try {
            MetaTemplate obj = findById(id);
            session.delete(obj);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void update(final MetaTemplate entity) {

        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @SuppressWarnings("unchecked")
    public MetaTemplate findById(final Serializable id) {

        return (MetaTemplate) getSession().get(MetaTemplate.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<MetaTemplate> findAll() {
        session.clear();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("from " + MetaTemplate.class.getName());
            List<MetaTemplate> list = query.list();
            transaction.commit();
            return list;
        } catch (Exception e) {
            transaction.rollback();
        }
        return new ArrayList<MetaTemplate>();

    }

    public void setSession(final Session s) {
        session = s;
    }

    protected Session getSession() {
        if (session == null) {
            throw new IllegalStateException("Session has not been set on DAO before usage");
        }
        return session;
    }

    public int count() {

        Query query = session.createQuery("select count(*) from " + MetaTemplate.class.getName());
        return ((Long) query.list().get(0)).intValue();

    }

    @SuppressWarnings("unchecked")
    public List<MetaTemplate> findByExample(final MetaTemplate exampleObject) {

        Criteria criteria = session.createCriteria(MetaTemplate.class);
        Example exampleRestriction = Example.create(exampleObject).excludeZeroes().enableLike().ignoreCase();
        criteria.add(exampleRestriction);
        return criteria.list();

    }

    @SuppressWarnings("unchecked")
    protected List<MetaTemplate> findByCriteria(final List<Criterion> criteria) {
        Criteria crit = getSession().createCriteria(MetaTemplate.class);
        for (Criterion c : criteria) {
            crit.add(c);
        }
        return crit.list();
    }

    public MetaTemplateDAO(final Session session) {
        this.session = session;
    }

    public void saveOrUpdate(final MetaTemplate metaTemplate) {

        if (findById(metaTemplate.getId()) == null) {
            save(metaTemplate);
        } else {
            update(metaTemplate);
        }

    }

}