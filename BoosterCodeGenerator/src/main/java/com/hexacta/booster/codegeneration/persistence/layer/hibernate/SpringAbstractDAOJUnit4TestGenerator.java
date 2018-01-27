package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

import java.util.HashMap;
import java.util.Map;

import com.hexacta.booster.codegeneration.ArchetypeTextFileGenerator;
import com.hexacta.booster.codegeneration.GenerationInfo;
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
 * This class generates a fix AbstractDAOTest4 that is the superclass of all
 * JUnit4 DAO tests.
 * 
 * @author vmartz
 * 
 */
public class SpringAbstractDAOJUnit4TestGenerator implements ArchetypeTextFileGenerator {

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public TextFile generate(final String groupId, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        MetaTemplate daoTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames.getName("ABSTRACT_DAO_TEST4"));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        String daoTestPackage = PackageNameBuilder.buildForAbstractDAOTest(codeGeneratorConfiguration);

        metaVariablesValue.put(new MetaVariable("daoTestPackage"), daoTestPackage);

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(daoTemplate, metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String abstractDaoText = metaTemplateEngine.merge(metaTemplateGeneration);

        String name = "AbstractDAOTest4.java";
        String path = PathBuilder.buildForAbstractDAOTest(groupId, codeGeneratorConfiguration.getDirectoryInfo());

        return new TextFile(abstractDaoText, name, path);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addDao(textFile.getName() + textFile.getPath());

    }

}
