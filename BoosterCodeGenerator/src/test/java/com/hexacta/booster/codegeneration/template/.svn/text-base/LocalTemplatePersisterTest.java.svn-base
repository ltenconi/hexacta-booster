/**
 *
 */
package com.hexacta.booster.codegeneration.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import ar.com.hexacta.utils.reflection.ReflectionUtils;

import com.hexacta.booster.codegeneration.TemplatesNames;
import com.hexacta.booster.codegeneration.template.exception.InvalidTemplateDataFileException;
import com.hexacta.booster.codegeneration.template.exception.TemplateDataNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateNotFoundException;
import com.hexacta.booster.codegeneration.template.model.BoosterTemplate;
import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.codegeneration.template.model.TemplateCategory;
import com.hexacta.booster.codegeneration.template.model.TemplateData;

/**
 * @author ltenconi
 * 
 */
public class LocalTemplatePersisterTest extends TestCase {

    /**
     *
     */
    private static final String TEST_GET_VM = "testGet.vm";

    /**
     *
     */
    private static final String TEST_ADD_VM = "testAdd.vm";

    /**
     *
     */
    private static final String SRC_TEST_RESOURCES = "./src/test/resources/";

    private static final Logger log = Logger.getLogger(LocalTemplatePersisterTest.class);

    public void testGetAllTemplates() throws TemplateNotFoundException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException {

        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();
        localTemplatePersister.init(SRC_TEST_RESOURCES);

        List<IMetaTemplate> metaTemplates = localTemplatePersister.getAllMetaTemplates();
        assertEquals(2, metaTemplates.size());
        assertEquals("testGet.vm", metaTemplates.get(0).getId());
        assertEquals("testGetTemplateData.vm", metaTemplates.get(1).getId());

    }

    public void testGetBoosterTemplate() throws InvalidTemplateDataFileException {

        LocalTemplatePersister localTemplateLocator = new LocalTemplatePersister();
        MetaTemplate metaTemplate = (MetaTemplate) localTemplateLocator
                .get(TemplatesNames.getName("ABSTRACT_DAO_TEST"));

        assertNotNull(metaTemplate);
        assertNotNull(metaTemplate.getTemplate());

    }

    public void testGetTemplateWithData() throws TemplateNotFoundException, InvalidTemplateDataFileException {

        LocalTemplatePersister localTemplateLocator = new LocalTemplatePersister();
        localTemplateLocator.init(SRC_TEST_RESOURCES);
        MetaTemplate metaTemplate = (MetaTemplate) localTemplateLocator.get(TEST_GET_VM);

        assertNotNull(metaTemplate);
        assertNotNull(metaTemplate.getTemplate());
        assertEquals(metaTemplate.getTemplateData().getClass(), TemplateData.class);

    }

    public void testAddBoosterTemplate() throws IOException, TemplateNotFoundException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException {

        String templateId = TEST_ADD_VM;

        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();

        localTemplatePersister.init(SRC_TEST_RESOURCES);

        BoosterTemplate template = new BoosterTemplate(TEST_ADD_VM);
        TemplateData templateData = new TemplateData();
        ArrayList<MetaVariable> metaVariables = new ArrayList<MetaVariable>();
        metaVariables.add(new MetaVariable("a", "b", "c"));
        templateData.setMetaVariables(metaVariables);
        TemplateCategory category = new TemplateCategory("Booster");
        templateData.setCategory(category);
        templateData.setTemplateName("testAdd");
        templateData.setHits(Long.valueOf(12));

        MetaTemplate metaTemplate = new MetaTemplate(templateId, template, templateData);
        localTemplatePersister.add(metaTemplate);

        assertEquals(metaTemplate.getId(), localTemplatePersister.get(templateId).getId());

        assertEquals(metaTemplate.getTemplate().getText(), localTemplatePersister.get(templateId).getTemplate()
                .getText());
        assertEquals(metaTemplate.getTemplateData().getCategory().getName(), localTemplatePersister.get(templateId)
                .getTemplateData().getCategory().getName());
        assertEquals(metaTemplate.getTemplateData().getTemplateName(), localTemplatePersister.get(templateId)
                .getTemplateData().getTemplateName());
        assertEquals(metaTemplate.getTemplateData().getHits(), localTemplatePersister.get(templateId).getTemplateData()
                .getHits());
        assertEquals(Long.valueOf(12), localTemplatePersister.get(templateId).getTemplateData().getHits());

        try {
            localTemplatePersister.remove(localTemplatePersister.get(templateId));
        } catch (TemplateNotFoundException e) {
            log.error(e);
        }

    }

    public void testUpDateBoosterTemplate() throws TemplateDataNotFoundException, InvalidTemplateDataFileException,
            IOException, TemplateNotFoundException {

        String templateId = TEST_ADD_VM;

        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();

        localTemplatePersister.init(SRC_TEST_RESOURCES);

        BoosterTemplate template = new BoosterTemplate(TEST_ADD_VM);
        TemplateData templateData = new TemplateData();
        ArrayList<MetaVariable> metaVariables = new ArrayList<MetaVariable>();
        metaVariables.add(new MetaVariable("a", "b", "c"));
        templateData.setMetaVariables(metaVariables);
        MetaTemplate metaTemplate = new MetaTemplate(templateId, template, templateData);

        localTemplatePersister.add(metaTemplate);

        template.setText("change");

        localTemplatePersister.update(metaTemplate);

        assertEquals(metaTemplate.getId(), localTemplatePersister.get(templateId).getId());

        assertEquals(metaTemplate.getTemplate().getText(), localTemplatePersister.get(templateId).getTemplate()
                .getText());

        localTemplatePersister.remove(metaTemplate);

    }

    public void testGetTemplateData() {

        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();

        localTemplatePersister.init(SRC_TEST_RESOURCES);

        TemplateData templateData = new TemplateData();
        ArrayList<MetaVariable> metaVariables = new ArrayList<MetaVariable>();
        metaVariables.add(new MetaVariable("a", "b", "c"));
        templateData.setMetaVariables(metaVariables);

        Object[] args = new Object[] { "testGetTemplateData.vm" };
        TemplateData getTemplateDataResult = (TemplateData) ReflectionUtils.executePrivateMethod(
                localTemplatePersister, LocalTemplatePersister.class, "getTemplateData",
                new Class<?>[] { String.class }, args);

        assertEquals(templateData.getId(), getTemplateDataResult.getId());
    }

    public void testGetFromFile() {

        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();

        localTemplatePersister.init(SRC_TEST_RESOURCES);

        String filePath = SRC_TEST_RESOURCES + TEST_GET_VM;
        Object[] args = new Object[] { filePath };
        String getStringFromFileResult = (String) ReflectionUtils.executePrivateMethod(localTemplatePersister,
                LocalTemplatePersister.class, "getStringFromFile", new Class<?>[] { String.class }, args);

        assertEquals("testAdd", getStringFromFileResult);
    }

}
