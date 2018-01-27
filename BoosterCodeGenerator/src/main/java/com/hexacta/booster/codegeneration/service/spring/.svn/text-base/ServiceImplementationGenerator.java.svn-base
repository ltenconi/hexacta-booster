package com.hexacta.booster.codegeneration.service.spring;

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
import com.hexacta.booster.utilities.JavaClassFileNameBuilder;
import com.hexacta.booster.utilities.JavaClassNameBuilder;
import com.hexacta.booster.utilities.PackageNameBuilder;
import com.hexacta.booster.utilities.PathBuilder;
import com.hexacta.booster.utilities.VarNameBuilder;

/**
 *
 */
public class ServiceImplementationGenerator implements TextFileGenerator {

    public ServiceImplementationGenerator() {
    }

    public TextFile generate(final Class entityClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        MetaTemplate serviceInterfaceTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("SERVICE_INTERFACE_IMPL"));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        metaVariablesValue.put(new MetaVariable("serviceImplPackage"), PackageNameBuilder
                .buildForServiceInterfaceImpl(entityClass));
        metaVariablesValue.put(new MetaVariable("entityPackage"), entityClass.getPackage());
        metaVariablesValue.put(new MetaVariable("entityClassName"), entityClass.getSimpleName());
        metaVariablesValue.put(new MetaVariable("entityVarName"), VarNameBuilder.buildForEntity(entityClass));
        metaVariablesValue.put(new MetaVariable("daoPackage"), PackageNameBuilder.buildForDAO(entityClass));
        metaVariablesValue.put(new MetaVariable("daoClassName"), JavaClassNameBuilder.buildForDAO(entityClass));
        metaVariablesValue.put(new MetaVariable("intefaceServicePackage"), PackageNameBuilder
                .buildForServiceInterface(entityClass));
        metaVariablesValue.put(new MetaVariable("interfaceServiceClassName"), JavaClassNameBuilder
                .buildForServiceInterface(entityClass));
        metaVariablesValue.put(new MetaVariable("intefaceDtoPackage"), PackageNameBuilder.buildForDTO(entityClass));
        metaVariablesValue.put(new MetaVariable("interfaceDtoClass"), JavaClassNameBuilder
                .buildForDTOInterface(entityClass));
        metaVariablesValue.put(new MetaVariable("serviceImplClassName"), JavaClassNameBuilder
                .buildForServiceInterfaceImpl(entityClass));
        metaVariablesValue.put(new MetaVariable("daoVarName"), VarNameBuilder.buildForDAO(entityClass));
        metaVariablesValue.put(new MetaVariable("dtoVarName"), VarNameBuilder.buildForDTO(entityClass));

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(serviceInterfaceTemplate,
                metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String serviceInterfaceText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(serviceInterfaceText, JavaClassFileNameBuilder
                .buildForServiceInterfaceImpl(entityClass), PathBuilder.buildForServiceInterfaceImpl(entityClass,
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
