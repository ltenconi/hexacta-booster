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
public class ServiceMockImplementationGenerator implements TextFileGenerator {

    public ServiceMockImplementationGenerator() {
    }

    public TextFile generate(final Class entityClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        Class persistableClass = codeGeneratorConfiguration.getPersistableMetaModel().getClass(entityClass.getName());

        MetaTemplate serviceInterfaceTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("SERVICE_INTERFACE_IMPL_MOCK"));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        metaVariablesValue.put(new MetaVariable("serviceImplementationMockPackage"), PackageNameBuilder
                .buildForServiceImplementationMock(persistableClass));
        metaVariablesValue.put(new MetaVariable("entityPackage"), persistableClass.getPackage());
        metaVariablesValue.put(new MetaVariable("entityClassName"), persistableClass.getSimpleName());
        metaVariablesValue.put(new MetaVariable("entityVarName"), VarNameBuilder.buildForEntity(persistableClass));
        metaVariablesValue.put(new MetaVariable("daoPackage"), PackageNameBuilder.buildForDAO(persistableClass));
        metaVariablesValue.put(new MetaVariable("daoClassName"), JavaClassNameBuilder.buildForDAO(persistableClass));
        metaVariablesValue.put(new MetaVariable("intefaceServicePackage"), PackageNameBuilder
                .buildForServiceInterface(persistableClass));
        metaVariablesValue.put(new MetaVariable("interfaceServiceClassName"), JavaClassNameBuilder
                .buildForServiceInterface(persistableClass));
        metaVariablesValue.put(new MetaVariable("dtoPackage"), PackageNameBuilder.buildForDTO(persistableClass));
        metaVariablesValue.put(new MetaVariable("dtoClassName"), JavaClassNameBuilder
                .buildForDTOInterface(persistableClass));
        metaVariablesValue.put(new MetaVariable("serviceImplementationClassName"), JavaClassNameBuilder
                .buildForServiceInterfaceImpl(persistableClass));
        metaVariablesValue.put(new MetaVariable("daoVarName"), VarNameBuilder.buildForDAO(persistableClass));
        metaVariablesValue.put(new MetaVariable("dtoVarName"), VarNameBuilder.buildForDTO(persistableClass));

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(serviceInterfaceTemplate,
                metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();
        String serviceInterfaceText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(serviceInterfaceText, JavaClassFileNameBuilder
                .buildForServiceInterfaceImpl(persistableClass), PathBuilder.buildForServiceImplementationMock(
                persistableClass, codeGeneratorConfiguration.getDirectoryInfo()));

        return textFile;
    }

    /**
     * {@inheritDoc}
     */
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addService(textFile.getPath() + textFile.getName());
    }

}
