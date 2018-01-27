/**
 * 
 */
package com.hexacta.booster.codegeneration.template;

import java.util.List;

import junit.framework.TestCase;

import com.hexacta.booster.codegeneration.TemplatesNames;
import com.hexacta.booster.codegeneration.template.exception.InvalidTemplateDataFileException;
import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;

/**
 * @author ltenconi
 * 
 */
public class MetaTemplateBuilderTest extends TestCase {

    public void testBuild() throws InvalidTemplateDataFileException {
        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();

        List<String> templatesNames = TemplatesNames.getAllNames();

        for (String templateName : templatesNames) {
            IMetaTemplate metaTemplate = localTemplatePersister.get(templateName);
            MetaTemplateBuilder.build(metaTemplate);
        }

    }

}
