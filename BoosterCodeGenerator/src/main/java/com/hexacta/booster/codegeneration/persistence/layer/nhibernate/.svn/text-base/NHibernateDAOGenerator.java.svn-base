package com.hexacta.booster.codegeneration.persistence.layer.nhibernate;

import java.util.HashMap;
import java.util.Map;

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

/**
 *
 */
public class NHibernateDAOGenerator implements TextFileGenerator {

    public NHibernateDAOGenerator() {

    }

    public TextFile generate(final Class aNetClass, final CodeGeneratorConfiguration aCodeGeneratorConfiguration)
            throws Exception {

        MetaTemplate nhDaoTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("DOT_NET_SUBCLASS_DAO"));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        String daoClassName = aNetClass.getSimpleName() + "DAO";
        metaVariablesValue.put(new MetaVariable("modelNamespace"), aNetClass.getPackage());
        metaVariablesValue.put(new MetaVariable("daoClassName"), daoClassName);
        metaVariablesValue.put(new MetaVariable("entityClassName"), aNetClass.getSimpleName());

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(nhDaoTemplate, metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();
        String nhDaoText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(nhDaoText, daoClassName + ".cs", aCodeGeneratorConfiguration
                .getDirectoryInfo().getDaosPath());

        return textFile;
    }

    /**
     * {@inheritDoc}
     */
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addDao(textFile.getPath() + textFile.getName());
    }
}
