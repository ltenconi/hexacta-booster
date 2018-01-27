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
import com.hexacta.booster.codegeneration.metamodel.Field;
import com.hexacta.booster.codegeneration.metamodel.Type;
import com.hexacta.booster.codegeneration.template.IMetaTemplateEngine;
import com.hexacta.booster.codegeneration.template.MetaTemplateGeneration;
import com.hexacta.booster.codegeneration.template.MetaTemplateSystem;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.project.configuration.JavaProjectType;
import com.hexacta.booster.utilities.JavaClassFileNameBuilder;
import com.hexacta.booster.utilities.MetaModelUtils;
import com.hexacta.booster.utilities.PackageNameBuilder;
import com.hexacta.booster.utilities.PathBuilder;
import com.hexacta.booster.utilities.TestGenerationHelper;

/**
 * This class generates the test in Junit4 for a DAO file.
 * 
 * Created on 17/02/2009
 * 
 * @author vmartz
 * 
 */

public class SpringHibernateDaoTest4Generator implements TextFileGenerator {

    static final Logger logger = Logger.getLogger(SpringHibernateDaoTestGenerator.class);

    public SpringHibernateDaoTest4Generator() {

    }

    public TextFile generate(final Class aClass, final CodeGeneratorConfiguration aCodeGeneratorConfiguration)
            throws Exception {

        Class persistableClass = aCodeGeneratorConfiguration.getPersistableMetaModel().getClass(aClass.getName());

        String idContainerClass = TestGenerationHelper.getIdContainerClass(persistableClass);
        String entityId = aCodeGeneratorConfiguration.getClassIdMap().getId(persistableClass.getName());
        String projectGroupId = MetaModelUtils.getGroupId(persistableClass);

        MetaTemplate daoTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames.getName("TEST4_SPRING_DAO"));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        metaVariablesValue.put(new MetaVariable("aClass"), persistableClass);
        metaVariablesValue
                .put(new MetaVariable("daoTestPackage"), PackageNameBuilder.buildForDAOTest(persistableClass));
        metaVariablesValue.put(new MetaVariable("daoPackage"), PackageNameBuilder.buildForDAO(persistableClass));
        metaVariablesValue.put(new MetaVariable("projectGroupId"), projectGroupId);
        metaVariablesValue.put(new MetaVariable("idContainerClass"), idContainerClass);
        metaVariablesValue.put(new MetaVariable("entityId"), entityId);
        metaVariablesValue.put(new MetaVariable("modelClassList"), aCodeGeneratorConfiguration.getClassList()
                .iterator());
        metaVariablesValue.put(new MetaVariable("daoClassName"), persistableClass.getSimpleName() + "DAO");
        metaVariablesValue.put(new MetaVariable("daoTestClassName"), persistableClass.getSimpleName() + "DAOTest");
        metaVariablesValue.put(new MetaVariable("daoVarName"), persistableClass.getSimpleName().toLowerCase() + "DAO");

        Field[] fieldsSet = persistableClass.getDeclaredFields();

        String modificablePropertyName = TestGenerationHelper.getModificablePropertyName(fieldsSet, persistableClass,
                aCodeGeneratorConfiguration, new JavaProjectType());

        if (modificablePropertyName != null) {
            Type propertyType = TestGenerationHelper.getType(modificablePropertyName, fieldsSet);
            Object fieldValue = TestGenerationHelper.generateJavaFieldValue(propertyType, aCodeGeneratorConfiguration);
            metaVariablesValue.put(new MetaVariable("modificablePropertyName"), modificablePropertyName);
            metaVariablesValue.put(new MetaVariable("fieldValue"), fieldValue);
        } else {
            metaVariablesValue.put(new MetaVariable("modificable"), false);
        }

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(daoTemplate, metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String daoText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(daoText, JavaClassFileNameBuilder.buildForDAOTest(persistableClass),
                PathBuilder.buildForTestDao(persistableClass, aCodeGeneratorConfiguration.getDirectoryInfo()));

        return textFile;
    }

    /**
     * {@inheritDoc}
     */
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addService(textFile.getPath() + textFile.getName());
    }

}
