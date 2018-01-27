package com.hexacta.booster.codegeneration.service.spring;

import java.util.HashMap;
import java.util.Map;

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
import com.hexacta.booster.utilities.JavaClassNameBuilder;
import com.hexacta.booster.utilities.PackageNameBuilder;
import com.hexacta.booster.utilities.PathBuilder;
import com.hexacta.booster.utilities.TestGenerationHelper;
import com.hexacta.booster.utilities.VarNameBuilder;

/**
 *
 */
public class SpringServiceTest4Generator implements TextFileGenerator {

    public TextFile generate(final Class aClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        Class persistableClass = codeGeneratorConfiguration.getPersistableMetaModel().getClass(aClass.getName());

        MetaTemplate serviceInterfaceTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("TEST4_SERVICE"));

        Field[] fieldsSet = persistableClass.getDeclaredFields();

        String modificablePropertyName = TestGenerationHelper.getModificablePropertyName(fieldsSet, persistableClass,
                codeGeneratorConfiguration, new JavaProjectType());

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        if (modificablePropertyName != null) {
            Type propertyType = TestGenerationHelper.getType(modificablePropertyName, fieldsSet);
            String setterMethod = TestGenerationHelper.getSetterMethod(modificablePropertyName);
            String getterMethod = TestGenerationHelper.getGetterMethod(modificablePropertyName, propertyType.getName());
            metaVariablesValue.put(new MetaVariable("modificablePropertyName"), modificablePropertyName);
            metaVariablesValue.put(new MetaVariable("fieldValue"), TestGenerationHelper.generateJavaFieldValue(
                    propertyType, codeGeneratorConfiguration));
            metaVariablesValue.put(new MetaVariable("anotherFieldValue"), TestGenerationHelper
                    .generateAnotherJavaFieldValue(propertyType, codeGeneratorConfiguration));
            metaVariablesValue.put(new MetaVariable("setterMethod"), setterMethod);
            metaVariablesValue.put(new MetaVariable("getterMethod"), getterMethod);
        } else {
            metaVariablesValue.put(new MetaVariable("modificable"), false);
        }

        metaVariablesValue.put(new MetaVariable("serviceTestPackage"), PackageNameBuilder
                .buildForServiceTest(persistableClass));
        metaVariablesValue.put(new MetaVariable("interfaceDtoPackage"), PackageNameBuilder
                .buildForDTO(persistableClass));
        metaVariablesValue.put(new MetaVariable("testClassName"), JavaClassNameBuilder
                .buildForServiceTest(persistableClass));
        metaVariablesValue.put(new MetaVariable("interfaceDtoClassName"), JavaClassNameBuilder
                .buildForDTOInterface(persistableClass));
        metaVariablesValue.put(new MetaVariable("interfaceDtoVarName"), VarNameBuilder.buildForDTO(persistableClass));
        metaVariablesValue.put(new MetaVariable("dtoVarName"), VarNameBuilder.buildForDTO(persistableClass));
        metaVariablesValue.put(new MetaVariable("serviceVarName"), VarNameBuilder.buildForService(persistableClass));
        metaVariablesValue.put(new MetaVariable("interfaceServiceClassName"), JavaClassNameBuilder
                .buildForServiceInterface(persistableClass));
        metaVariablesValue.put(new MetaVariable("interfaceServicePackage"), PackageNameBuilder
                .buildForServiceInterface(persistableClass));

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(serviceInterfaceTemplate,
                metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String serviceInterfaceText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(serviceInterfaceText, JavaClassFileNameBuilder
                .buildForServiceTest(persistableClass), PathBuilder.buildForServiceTest(persistableClass,
                codeGeneratorConfiguration.getDirectoryInfo()));
        return textFile;
    }

    /**
     * {@inheritDoc}
     */
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addService(textFile.getPath() + textFile.getName());
    }

}
