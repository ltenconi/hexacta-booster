package com.hexacta.booster.codegeneration.service.spring;

import java.util.HashMap;
import java.util.Map;

import com.hexacta.booster.codegeneration.TemplatesNames;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.template.IMetaTemplateEngine;
import com.hexacta.booster.codegeneration.template.MetaTemplateGeneration;
import com.hexacta.booster.codegeneration.template.MetaTemplateSystem;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.utilities.PackageNameBuilder;
import com.hexacta.booster.utilities.PathBuilder;

/**
 * This class generates a fix AbstractServiceTest4 that is the superclass of all
 * JUnit4 service tests.
 * 
 * @author vmartz
 * 
 */
public class SpringAbstractServiceJUnit4TestGenerator {

    /**
     * @param groupId
     * @param codeGeneratorConfiguration
     * @param generationInfo
     * @return
     * @throws Exception
     */
    public TextFile generate(final String groupId, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        MetaTemplate abstractServiceTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("ABSTRACT_SERVICE_TEST4"));

        String serviceTestPackage = PackageNameBuilder.buildForAbstractServiceTest(codeGeneratorConfiguration);

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        metaVariablesValue.put(new MetaVariable("serviceTestPackage"), serviceTestPackage);

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(abstractServiceTemplate,
                metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String abstractServiceText = metaTemplateEngine.merge(metaTemplateGeneration);

        String name = "AbstractServiceTest4.java";
        String path = PathBuilder.buildForAbstractServiceTest(groupId, codeGeneratorConfiguration.getDirectoryInfo());

        return new TextFile(abstractServiceText, name, path);
    }

}
