package com.hexacta.booster.codegeneration.persistence.layer.nhibernate;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TemplatesNames;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.TextFileGenerator;
import com.hexacta.booster.codegeneration.configuration.ClassFinder;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.Field;
import com.hexacta.booster.codegeneration.metamodel.Type;
import com.hexacta.booster.codegeneration.template.IMetaTemplateEngine;
import com.hexacta.booster.codegeneration.template.MetaTemplateGeneration;
import com.hexacta.booster.codegeneration.template.MetaTemplateSystem;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.project.configuration.DotNetProjectType;
import com.hexacta.booster.utilities.TestGenerationHelper;

/**
 * 
 */
public class NHibernateDAOTestGenerator implements TextFileGenerator {

    static final Logger logger = Logger.getLogger(NHibernateDAOTestGenerator.class);

    public NHibernateDAOTestGenerator() {

    }

    public TextFile generate(final Class aClass, final CodeGeneratorConfiguration aCodeGeneratorConfiguration)
            throws Exception {

        MetaTemplate nhDaoTestTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("DOTNET_TEST_DAO"));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        String idFieldName = aCodeGeneratorConfiguration.getClassIdMap().getId(aClass.getName());
        String propertyId = idFieldName.substring(0, 1).toUpperCase() + idFieldName.substring(1, idFieldName.length());
        String daoTestClassName = aClass.getSimpleName() + "DAOTest";

        metaVariablesValue.put(new MetaVariable("daoClassName"), aClass.getSimpleName() + "DAO");
        metaVariablesValue.put(new MetaVariable("daoTestClassName"), daoTestClassName);
        metaVariablesValue.put(new MetaVariable("daoVarName"), aClass.getSimpleName().toLowerCase() + "DAO");
        metaVariablesValue.put(new MetaVariable("propertyId"), propertyId);
        metaVariablesValue.put(new MetaVariable("aClass"), aClass);
        metaVariablesValue.put(new MetaVariable("modelClassList"), aCodeGeneratorConfiguration.getClassList()
                .iterator());

        Field[] fieldsSet = ClassFinder.find(aClass.getName(), aCodeGeneratorConfiguration.getClassList(),
                new DotNetProjectType()).getDeclaredFields();

        String modificablePropertyName = TestGenerationHelper.getModificablePropertyName(fieldsSet, aClass,
                aCodeGeneratorConfiguration, new DotNetProjectType());

        if (modificablePropertyName != null) {
            Type propertyType = TestGenerationHelper.getType(modificablePropertyName, fieldsSet);
            Object fieldValue = TestGenerationHelper.generateDotNetFieldValue(propertyType);
            metaVariablesValue.put(new MetaVariable("modificablePropertyName"), modificablePropertyName);
            metaVariablesValue.put(new MetaVariable("fieldValue"), fieldValue);
        } else {
            metaVariablesValue.put(new MetaVariable("modificable"), false);
        }

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(nhDaoTestTemplate,
                metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();
        String nhDaoTestText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(nhDaoTestText, daoTestClassName + ".cs", aCodeGeneratorConfiguration
                .getDirectoryInfo().getTestDaosPath());

        return textFile;
    }

    /**
     * {@inheritDoc}
     */
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addDao(textFile.getPath() + textFile.getName());
    }

}
