/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.cfg.Configuration;

import com.hexacta.booster.codegeneration.template.exception.InvalidTemplateDataFileException;
import com.hexacta.booster.codegeneration.template.exception.TemplateDataNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateInitErrorException;
import com.hexacta.booster.codegeneration.template.exception.TemplateNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateParseErrorException;
import com.hexacta.booster.codegeneration.template.model.BoosterTemplate;
import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.codegeneration.template.model.Tag;
import com.hexacta.booster.codegeneration.template.model.TemplateCategory;
import com.hexacta.booster.codegeneration.template.model.TemplateData;

/**
 * @author fmrodriguez
 * 
 */
public class RemoteTemplatePeristerTest extends TestCase {

    public void testAdd() throws IOException, TemplateNotFoundException, TemplateParseErrorException,
            TemplateInitErrorException, TemplateDataNotFoundException, InvalidTemplateDataFileException,
            TemplatePersisterInicializationException {
        MetaTemplate metaTemplate = new MetaTemplate("template.vm", new BoosterTemplate("test"));
        MetaVariable metaVariable = new MetaVariable("dafaultValue", "meaning", "name");
        MetaVariable metaVariable2 = new MetaVariable("dafaultValue", "meaning", "name2");
        List<MetaVariable> metaVariables = new ArrayList<MetaVariable>();
        metaVariables.add(metaVariable);
        metaVariables.add(metaVariable2);
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag("test"));
        TemplateData templateData = new TemplateData(new TemplateCategory("Booster"), "description", Long.valueOf(1),
                metaVariables, tags, "RemoteTest");
        metaTemplate.setTemplateData(templateData);

        Configuration anHibernateConfiguration = new Configuration().configure();

        RemoteTemplatePersister remoteTemplatePerister = new RemoteTemplatePersister();
        remoteTemplatePerister.init(anHibernateConfiguration);

        remoteTemplatePerister.add(metaTemplate);
        MetaTemplate recoveredMetaTemplate = (MetaTemplate) remoteTemplatePerister.get(metaTemplate.getId());
        assertEquals(metaTemplate, recoveredMetaTemplate);
        assertEquals(2, recoveredMetaTemplate.getTemplateData().getMetaVariables().size());
        remoteTemplatePerister.remove(metaTemplate);
    }

    public void testGet() throws IOException, TemplateNotFoundException, TemplateParseErrorException,
            TemplateInitErrorException, TemplateDataNotFoundException, InvalidTemplateDataFileException,
            TemplatePersisterInicializationException {
        MetaTemplate metaTemplate = new MetaTemplate("template2.vm", new BoosterTemplate("test"));
        TemplateData templateData = new TemplateData(new TemplateCategory("Booster"), "description", Long.valueOf(1),
                new ArrayList<MetaVariable>(), new ArrayList<Tag>(), "RemoteTest3");
        metaTemplate.setTemplateData(templateData);

        MetaTemplate anotherMetaTemplate = new MetaTemplate("template3.vm", new BoosterTemplate("test"));
        TemplateData anothertemplateData = new TemplateData(new TemplateCategory("Booster"), "description", Long
                .valueOf(1), new ArrayList<MetaVariable>(), new ArrayList<Tag>(), "RemoteTest2");
        anotherMetaTemplate.setTemplateData(anothertemplateData);

        Configuration anHibernateConfiguration = new Configuration().configure();

        RemoteTemplatePersister remoteTemplatePerister = new RemoteTemplatePersister();
        remoteTemplatePerister.init(anHibernateConfiguration);

        remoteTemplatePerister.add(metaTemplate);
        remoteTemplatePerister.add(anotherMetaTemplate);

        MetaTemplate recoveredMetaTemplate = (MetaTemplate) remoteTemplatePerister.get(metaTemplate.getId());
        assertEquals(metaTemplate, recoveredMetaTemplate);
        remoteTemplatePerister.remove(metaTemplate);
        remoteTemplatePerister.remove(anotherMetaTemplate);
    }

    public void testGetAll() throws IOException, TemplateNotFoundException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException, TemplatePersisterInicializationException {
        MetaTemplate metaTemplate = new MetaTemplate("template2.vm", new BoosterTemplate("test"));
        TemplateData templateData = new TemplateData(new TemplateCategory("Booster"), "description", Long.valueOf(1),
                new ArrayList<MetaVariable>(), new ArrayList<Tag>(), "RemoteTest3");
        metaTemplate.setTemplateData(templateData);

        MetaTemplate anotherMetaTemplate = new MetaTemplate("template3.vm", new BoosterTemplate("test"));
        TemplateData anothertemplateData = new TemplateData(new TemplateCategory("Booster"), "description", Long
                .valueOf(1), new ArrayList<MetaVariable>(), new ArrayList<Tag>(), "RemoteTest2");
        anotherMetaTemplate.setTemplateData(anothertemplateData);

        Configuration anHibernateConfiguration = new Configuration().configure();

        RemoteTemplatePersister remoteTemplatePerister = new RemoteTemplatePersister();
        remoteTemplatePerister.init(anHibernateConfiguration);

        remoteTemplatePerister.add(metaTemplate);
        remoteTemplatePerister.add(anotherMetaTemplate);
        List<IMetaTemplate> allTemplates = remoteTemplatePerister.getAllMetaTemplates();
        assertEquals(2, allTemplates.size());
        remoteTemplatePerister.remove(metaTemplate);
        remoteTemplatePerister.remove(anotherMetaTemplate);
    }

    public void testRemove() throws IOException, TemplateNotFoundException, TemplateParseErrorException,
            TemplateInitErrorException, TemplateDataNotFoundException, InvalidTemplateDataFileException,
            TemplatePersisterInicializationException {
        MetaTemplate metaTemplate = new MetaTemplate("template.vm", new BoosterTemplate("test"));
        TemplateData templateData = new TemplateData(new TemplateCategory("Booster"), "description", Long.valueOf(1),
                new ArrayList<MetaVariable>(), new ArrayList<Tag>(), "RemoteTest");
        metaTemplate.setTemplateData(templateData);

        Configuration anHibernateConfiguration = new Configuration().configure();

        RemoteTemplatePersister remoteTemplatePerister = new RemoteTemplatePersister();
        remoteTemplatePerister.init(anHibernateConfiguration);

        remoteTemplatePerister.add(metaTemplate);

        remoteTemplatePerister.remove(metaTemplate);
        MetaTemplate recoveredMetaTemplate = (MetaTemplate) remoteTemplatePerister.get(metaTemplate.getId());
        assertEquals(recoveredMetaTemplate, null);

    }

    public void testUpdate() throws IOException, TemplateNotFoundException, TemplateParseErrorException,
            TemplateInitErrorException, TemplateDataNotFoundException, InvalidTemplateDataFileException,
            TemplatePersisterInicializationException {

        MetaTemplate metaTemplate = new MetaTemplate("template.vm", new BoosterTemplate("test"));
        TemplateData templateData = new TemplateData(new TemplateCategory("Booster"), "description", Long.valueOf(1),
                new ArrayList<MetaVariable>(), new ArrayList<Tag>(), "RemoteTest");
        metaTemplate.setTemplateData(templateData);

        Configuration anHibernateConfiguration = new Configuration().configure();

        RemoteTemplatePersister remoteTemplatePerister = new RemoteTemplatePersister();
        remoteTemplatePerister.init(anHibernateConfiguration);

        remoteTemplatePerister.add(metaTemplate);

        metaTemplate.setTemplate(new BoosterTemplate(" new text"));

        remoteTemplatePerister.update(metaTemplate);

        MetaTemplate recoveredMetaTemplate = (MetaTemplate) remoteTemplatePerister.get(metaTemplate.getId());
        assertEquals(recoveredMetaTemplate.getTemplate().getText(), " new text");
        remoteTemplatePerister.remove(metaTemplate);

    }

}
