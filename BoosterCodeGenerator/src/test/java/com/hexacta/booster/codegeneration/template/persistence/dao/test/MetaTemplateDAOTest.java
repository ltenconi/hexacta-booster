package com.hexacta.booster.codegeneration.template.persistence.dao.test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.codegeneration.template.model.TemplateCategory;
import com.hexacta.booster.codegeneration.template.model.TemplateData;
import com.hexacta.booster.codegeneration.template.persistence.dao.MetaTemplateDAO;

/***/
public class MetaTemplateDAOTest extends TestCase {

    Configuration anHibernateConfiguration = new Configuration();

    SessionFactory aSessionFactory = anHibernateConfiguration.configure().buildSessionFactory();

    /**
     * {@inheritDoc}
     */

    public void testSave() {

        Session session = aSessionFactory.openSession();
        try {
            MetaTemplate metatemplate = new MetaTemplate();
            metatemplate.setId("templateID.vm");
            MetaTemplateDAO metatemplateDAO = new MetaTemplateDAO(session);
            metatemplateDAO.save(metatemplate);
            Field field = MetaTemplate.class.getDeclaredField("id");
            field.setAccessible(true);
            MetaTemplate metatemplateRecovered = metatemplateDAO.findById((Serializable) field.get(metatemplate));
            assertEquals(metatemplate, metatemplateRecovered);
            metatemplateDAO.delete(metatemplate);

        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public void testFindById() {

        Session session = aSessionFactory.openSession();
        try {
            MetaTemplate metatemplate = new MetaTemplate();
            metatemplate.setId("template.vm");
            MetaTemplateDAO metatemplateDAO = new MetaTemplateDAO(session);
            metatemplateDAO.save(metatemplate);
            Field field = MetaTemplate.class.getDeclaredField("id");
            field.setAccessible(true);
            MetaTemplate metatemplateRecovered = metatemplateDAO.findById((Serializable) field.get(metatemplate));
            assertEquals(metatemplate, metatemplateRecovered);
            metatemplateDAO.delete(metatemplate);

        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public void testDelete() {

        Session session = aSessionFactory.openSession();
        try {
            MetaTemplate metatemplate = new MetaTemplate();
            metatemplate.setId("template.vm");
            MetaTemplateDAO metatemplateDAO = new MetaTemplateDAO(session);
            metatemplateDAO.save(metatemplate);
            metatemplateDAO.delete(metatemplate);
            Field field = MetaTemplate.class.getDeclaredField("id");
            field.setAccessible(true);
            MetaTemplate metatemplateRecovered = metatemplateDAO.findById((Serializable) field.get(metatemplate));
            assertNull(metatemplateRecovered);

        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public void testUpdate() {

        assert true;
    }

    public void testFindAll() {

        Session session = aSessionFactory.openSession();
        MetaTemplate metatemplate1 = new MetaTemplate();
        metatemplate1.setId("template.vm");
        ArrayList<MetaVariable> metaVariables = new ArrayList<MetaVariable>();
        metaVariables.add(new MetaVariable("nombre"));
        metatemplate1.setTemplateData(new TemplateData(new TemplateCategory("una"), metaVariables, "nombre", "descrp"));
        MetaTemplate metatemplate2 = new MetaTemplate();
        metatemplate2.setId("template2.vm");
        MetaTemplate metatemplate3 = new MetaTemplate();
        metatemplate3.setId("template3.vm");
        MetaTemplateDAO metatemplateDAO = new MetaTemplateDAO(session);
        metatemplateDAO.save(metatemplate1);
        metatemplateDAO.save(metatemplate2);
        metatemplateDAO.save(metatemplate3);

        List<MetaTemplate> metatemplateList = metatemplateDAO.findAll();
        assertEquals(3, metatemplateList.size());

        metatemplateDAO.delete(metatemplate1);
        metatemplateDAO.delete(metatemplate2);
        metatemplateDAO.delete(metatemplate3);
        session.flush();
        session.close();
    }

    public void testDeleteById() {

        Session session = aSessionFactory.openSession();
        try {
            MetaTemplate metatemplate = new MetaTemplate();
            MetaTemplateDAO metatemplateDAO = new MetaTemplateDAO(session);
            metatemplate.setId("template.vm");
            metatemplateDAO.save(metatemplate);
            Field field = MetaTemplate.class.getDeclaredField("id");
            field.setAccessible(true);
            Serializable id = (Serializable) field.get(metatemplate);
            MetaTemplate metatemplateRecovered = metatemplateDAO.findById(id);
            assertEquals(metatemplate, metatemplateRecovered);

            metatemplateDAO.deleteById(id);
            metatemplateRecovered = metatemplateDAO.findById(id);
            assertNull(metatemplateRecovered);

        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public void testCount() {

        Session session = aSessionFactory.openSession();
        MetaTemplate metatemplate1 = new MetaTemplate();
        metatemplate1.setId("t1.vm");
        MetaTemplate metatemplate2 = new MetaTemplate();
        metatemplate2.setId("t2.vm");
        MetaTemplate metatemplate3 = new MetaTemplate();
        metatemplate3.setId("t3.vm");
        MetaTemplateDAO metatemplateDAO = new MetaTemplateDAO(session);
        metatemplateDAO.save(metatemplate1);
        metatemplateDAO.save(metatemplate2);
        metatemplateDAO.save(metatemplate3);

        int count = metatemplateDAO.count();
        assertEquals(3, count);
        metatemplateDAO.delete(metatemplate1);
        metatemplateDAO.delete(metatemplate2);
        metatemplateDAO.delete(metatemplate3);
        session.flush();
        session.close();
    }

    public void testFindByExample() {

        Session session = aSessionFactory.openSession();

        MetaTemplateDAO metatemplateDAO = new MetaTemplateDAO(session);
        MetaTemplate metatemplate1 = new MetaTemplate();
        metatemplate1.setId("template1.vm");
        MetaTemplate metatemplate2 = new MetaTemplate();
        metatemplate2.setId("template2.vm");
        MetaTemplate metatemplate3 = new MetaTemplate();
        metatemplate3.setId("template3.vm");
        metatemplateDAO.save(metatemplate1);
        metatemplateDAO.save(metatemplate2);
        metatemplateDAO.save(metatemplate3);

        MetaTemplate example = metatemplate1;
        List<MetaTemplate> list = metatemplateDAO.findByExample(example);
        assertTrue(list.contains(example));
        metatemplateDAO.delete(metatemplate1);
        metatemplateDAO.delete(metatemplate2);
        metatemplateDAO.delete(metatemplate3);
        session.flush();
        session.close();
    }
}