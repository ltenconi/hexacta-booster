/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template;

import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import com.hexacta.booster.codegeneration.template.exception.InvalidTemplateDataFileException;
import com.hexacta.booster.codegeneration.template.exception.TemplateDataNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateNotFoundException;
import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;
import com.hexacta.booster.codegeneration.template.model.Tag;
import com.hexacta.booster.codegeneration.template.search.ContentCriterion;
import com.hexacta.booster.codegeneration.template.search.NameCriterion;
import com.hexacta.booster.codegeneration.template.search.SearchCriteria;

/**
 * @author fmrodriguez
 * 
 */
public class MetaTemplateSystemTest extends TestCase {

    private static final String SRC_TEST_RESOURCES = "./src/test/resources/";

    public void testSearchByCriteria() throws TemplateNotFoundException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException {

        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();
        localTemplatePersister.init(SRC_TEST_RESOURCES);

        MetaTemplateSystem.init(new MetaTemplateSystemConfiguration(new VelocityMetaTemplateEngine(),
                localTemplatePersister));

        SearchCriteria searchCriteria = new SearchCriteria();

        searchCriteria.addSearhCriterion(new NameCriterion("testGet"));

        List<IMetaTemplate> result = MetaTemplateSystem.searchByCriteria(searchCriteria);

        assertEquals(result.size(), 1);

    }

    public void testAnotherSearchByCriteria() throws TemplateNotFoundException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException {

        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();
        localTemplatePersister.init(SRC_TEST_RESOURCES);

        MetaTemplateSystem.init(new MetaTemplateSystemConfiguration(new VelocityMetaTemplateEngine(),
                localTemplatePersister));

        SearchCriteria searchCriteria = new SearchCriteria();

        searchCriteria.addSearhCriterion(new NameCriterion("templateTest"));
        searchCriteria.addSearhCriterion(new ContentCriterion("testAdd"));

        List<IMetaTemplate> result = MetaTemplateSystem.searchByCriteria(searchCriteria);

        assertEquals(result.size(), 2);

    }

    public void testGetAllTags() throws TemplateNotFoundException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException {

        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();
        localTemplatePersister.init(SRC_TEST_RESOURCES);

        MetaTemplateSystem.init(new MetaTemplateSystemConfiguration(new VelocityMetaTemplateEngine(),
                localTemplatePersister));

        Set<Tag> tags = MetaTemplateSystem.getAllTags();
        assertTrue(tags.size() == 2);

        assertTrue(tags.contains(new Tag("booster")));
        assertTrue(tags.contains(new Tag("eclipse")));

    }

}
