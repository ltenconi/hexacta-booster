/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hexacta.booster.codegeneration.template.exception.InvalidTemplateDataFileException;
import com.hexacta.booster.codegeneration.template.exception.TemplateDataNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateInitErrorException;
import com.hexacta.booster.codegeneration.template.exception.TemplateNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateParseErrorException;
import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;
import com.hexacta.booster.codegeneration.template.model.Tag;
import com.hexacta.booster.codegeneration.template.model.TemplateData;
import com.hexacta.booster.codegeneration.template.search.SearchCriteria;

/**
 * @author fmrodriguez
 * 
 */
public final class MetaTemplateSystem {

    private static ITemplatePersister templatePersister;

    private static IMetaTemplateEngine metaTemplateEngine;

    static {
        templatePersister = new LocalTemplatePersister();
        metaTemplateEngine = new VelocityMetaTemplateEngine();
    }

    /**
     *
     */
    private MetaTemplateSystem() {
    }

    public static void init(final MetaTemplateSystemConfiguration metaTemplateSystemConfiguration) {
        templatePersister = metaTemplateSystemConfiguration.getTemplatePersister();
        metaTemplateEngine = metaTemplateSystemConfiguration.getMetaTemplateEngine();
    }

    public static List<IMetaTemplate> getAllMetaTemplate() throws TemplateNotFoundException,
            InvalidTemplateDataFileException, TemplateDataNotFoundException {
        return templatePersister.getAllMetaTemplates();
    }

    public static IMetaTemplate get(final String templateId) throws TemplateNotFoundException,
            TemplateParseErrorException, TemplateInitErrorException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException {
        return templatePersister.get(templateId);
    }

    public static void add(final IMetaTemplate metaTemplate) throws IOException {
        templatePersister.add(metaTemplate);
    }

    public static void update(final IMetaTemplate metaTemplate) throws IOException {
        templatePersister.update(metaTemplate);
    }

    public static void remove(final IMetaTemplate metaTemplate) throws TemplateNotFoundException,
            TemplateDataNotFoundException, IOException {
        templatePersister.remove(metaTemplate);
    }

    public static IMetaTemplateEngine getMetaTemplateEngine() {
        return metaTemplateEngine;
    }

    public static void addHit(final String templateId) throws TemplateNotFoundException, TemplateParseErrorException,
            TemplateInitErrorException, TemplateDataNotFoundException, InvalidTemplateDataFileException, IOException {
        IMetaTemplate metaTemplate = get(templateId);
        TemplateData templateData = metaTemplate.getTemplateData();
        templateData.addHit();
        update(metaTemplate);
    }

    public static List<IMetaTemplate> getTop10() throws TemplateNotFoundException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException {
        List<IMetaTemplate> allMetaTemplates = templatePersister.getAllMetaTemplates();
        Collections.sort(allMetaTemplates, new MetaTemplateHitsComparator());
        if (allMetaTemplates.size() > 9)
            return allMetaTemplates.subList(0, 10);
        else
            return allMetaTemplates;
    }

    public static void export(final IMetaTemplate metaTemplate, final String directoryPath, final String templateId)
            throws IOException {
        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();
        localTemplatePersister.init(directoryPath);
        metaTemplate.setId(templateId);
        localTemplatePersister.add(metaTemplate);
    }

    public static IMetaTemplate get(final String templateId, final String directory) throws TemplateNotFoundException,
            InvalidTemplateDataFileException {
        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();
        localTemplatePersister.init(directory);
        return localTemplatePersister.get(templateId);
    }

    public static List<IMetaTemplate> searchByCriteria(final SearchCriteria searchCriteria)
            throws TemplateNotFoundException, TemplateDataNotFoundException, InvalidTemplateDataFileException {
        List<IMetaTemplate> allMetaTemplate = getAllMetaTemplate();
        return searchCriteria.result(allMetaTemplate);
    }

    public static Set<Tag> getAllTags() throws TemplateNotFoundException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException {
        List<IMetaTemplate> allTemplates = templatePersister.getAllMetaTemplates();
        Set<Tag> tags = new HashSet<Tag>();
        for (IMetaTemplate metaTemplate : allTemplates) {
            tags.addAll(metaTemplate.getTemplateData().getTags());
        }
        return tags;
    }
}