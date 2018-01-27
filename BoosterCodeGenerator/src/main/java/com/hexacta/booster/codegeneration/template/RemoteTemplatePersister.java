/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hexacta.booster.codegeneration.template.exception.InvalidTemplateDataFileException;
import com.hexacta.booster.codegeneration.template.exception.TemplateDataNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateInitErrorException;
import com.hexacta.booster.codegeneration.template.exception.TemplateNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateParseErrorException;
import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.persistence.dao.MetaTemplateDAO;

/**
 * @author fmrodriguez
 * 
 */
public class RemoteTemplatePersister implements ITemplatePersister {

    private MetaTemplateDAO metaTemplateDAO;

    private static final Logger log = Logger.getLogger(RemoteTemplatePersister.class);

    /**
     * 
     */
    public RemoteTemplatePersister() {
    }

    public void init(final Configuration configuration) throws TemplatePersisterInicializationException {
        try {
            SessionFactory aSessionFactory = configuration.buildSessionFactory();
            metaTemplateDAO = new MetaTemplateDAO(aSessionFactory.openSession());
        } catch (HibernateException e) {
            throw new TemplatePersisterInicializationException();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void add(final IMetaTemplate metaTemplate) throws IOException {

        MetaTemplate template = MetaTemplateBuilder.build(metaTemplate);
        if (existMetaTemplateWithSameName(metaTemplate)) {
            metaTemplate.getTemplateData().setTemplateName(
                    metaTemplate.getTemplateData().getTemplateName() + System.currentTimeMillis());
        }
        metaTemplateDAO.saveOrUpdate(template);

    }

    /**
     * @param metaTemplate
     * @return
     */
    private boolean existMetaTemplateWithSameName(final IMetaTemplate metaTemplate) {

        try {
            List<IMetaTemplate> allTemplates = getAllMetaTemplates();
            for (IMetaTemplate metaTemplateInRepository : allTemplates) {

                if (metaTemplateInRepository.getTemplateData().getCategory().equals(
                        metaTemplate.getTemplateData().getCategory())
                        && !metaTemplate.getId().equals(metaTemplateInRepository.getId())) {
                    if (metaTemplateInRepository.getTemplateData().getTemplateName().equals(
                            metaTemplate.getTemplateData().getTemplateName()))
                        return true;
                }

            }
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public IMetaTemplate get(final String templateId) throws TemplateParseErrorException, TemplateInitErrorException,
            TemplateDataNotFoundException, InvalidTemplateDataFileException {
        return metaTemplateDAO.findById(templateId);
    }

    /**
     * {@inheritDoc}
     */
    public List<IMetaTemplate> getAllMetaTemplates() throws TemplateNotFoundException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException {
        List<MetaTemplate> metaTemplates = metaTemplateDAO.findAll();
        List<IMetaTemplate> allTemplates = new ArrayList<IMetaTemplate>();
        for (IMetaTemplate metaTemplate : metaTemplates) {
            allTemplates.add(metaTemplate);
        }
        return allTemplates;

    }

    /**
     * {@inheritDoc}
     */
    public void remove(final IMetaTemplate metaTemplate) throws TemplateNotFoundException,
            TemplateDataNotFoundException, IOException {
        metaTemplateDAO.deleteById(metaTemplate.getId());
    }

    /**
     * {@inheritDoc}
     */
    public void update(final IMetaTemplate metaTemplate) throws IOException {
        metaTemplateDAO.update(MetaTemplateBuilder.build(metaTemplate));

    }

}
