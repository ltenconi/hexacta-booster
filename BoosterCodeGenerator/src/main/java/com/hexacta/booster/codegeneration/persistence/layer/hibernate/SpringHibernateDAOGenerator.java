package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TemplatesNames;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.TextFileGenerator;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.template.IMetaTemplateEngine;
import com.hexacta.booster.codegeneration.template.MetaTemplateGeneration;
import com.hexacta.booster.codegeneration.template.MetaTemplateSystem;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.utilities.JavaClassFileNameBuilder;
import com.hexacta.booster.utilities.MetaModelUtils;
import com.hexacta.booster.utilities.PackageNameBuilder;
import com.hexacta.booster.utilities.PathBuilder;

/**
 * This class generates the text for a DAO.
 */

public class SpringHibernateDAOGenerator implements TextFileGenerator {

    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(SpringHibernateDAOGenerator.class);

    public SpringHibernateDAOGenerator() {
    }

    public TextFile generate(final Class aClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        MetaTemplate daoTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("SUBCLASS_HIBERNATE_SPRING_DAO"));
        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        String projectGroupId = MetaModelUtils.getGroupId(aClass);

        metaVariablesValue.put(new MetaVariable("daoPackage"), PackageNameBuilder.buildForDAO(aClass));
        metaVariablesValue.put(new MetaVariable("aClass"), aClass);
        metaVariablesValue.put(new MetaVariable("projectGroupid"), projectGroupId);

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(daoTemplate, metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String daoText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(daoText, JavaClassFileNameBuilder.buildForDAO(aClass), PathBuilder
                .buildForDao(aClass, codeGeneratorConfiguration.getDirectoryInfo()));

        return textFile;
    }

    /**
     * {@inheritDoc}
     */
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addDao(textFile.getPath() + textFile.getName());
    }

}