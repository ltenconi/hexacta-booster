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
public class ServiceInterfaceGenerator implements TextFileGenerator {

    public ServiceInterfaceGenerator() {
    }

    public TextFile generate(final Class entityClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        MetaTemplate serviceInterfaceTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("SERVICE_INTERFACE"));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        metaVariablesValue.put(new MetaVariable("serviceIntefacePackage"), PackageNameBuilder
                .buildForServiceInterface(entityClass));
        metaVariablesValue.put(new MetaVariable("dtoIntefacePackage"), PackageNameBuilder.buildForDTO(entityClass));
        metaVariablesValue.put(new MetaVariable("className"), JavaClassNameBuilder
                .buildForServiceInterface(entityClass));
        metaVariablesValue.put(new MetaVariable("interfaceDtoClassName"), JavaClassNameBuilder
                .buildForDTOInterface(entityClass));
        metaVariablesValue.put(new MetaVariable("dtoVarName"), VarNameBuilder.buildForDTO(entityClass));

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(serviceInterfaceTemplate,
                metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String serviceInterfaceText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(serviceInterfaceText, JavaClassFileNameBuilder
                .buildForServiceInterface(entityClass), PathBuilder.buildForServiceInterface(entityClass,
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
