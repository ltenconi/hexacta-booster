package com.hexacta.booster.codegeneration.service;

import java.util.HashMap;
import java.util.Map;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TemplatesNames;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.TextFileGenerator;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.Field;
import com.hexacta.booster.codegeneration.template.IMetaTemplateEngine;
import com.hexacta.booster.codegeneration.template.MetaTemplateGeneration;
import com.hexacta.booster.codegeneration.template.MetaTemplateSystem;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.utilities.DtoGeneratorHelper;
import com.hexacta.booster.utilities.JavaClassFileNameBuilder;
import com.hexacta.booster.utilities.MetaModelUtils;
import com.hexacta.booster.utilities.PackageNameBuilder;
import com.hexacta.booster.utilities.PathBuilder;

/**
 * 
 * @author clopez
 * 
 */
public class DynaDtoInterfaceGenerator implements TextFileGenerator {

    public TextFile generate(final Class entityClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        MetaTemplate dynaDtoInterfaceTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("DYNA_DTO_INTERFACE"));

        Field[] fields = MetaModelUtils.getAllInheritedFields(codeGeneratorConfiguration.getPersistableMetaModel()
                .getClass(entityClass.getName()));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        metaVariablesValue.put(new MetaVariable("dtoPackage"), PackageNameBuilder.buildForDTO(entityClass));
        metaVariablesValue.put(new MetaVariable("imports"), DtoGeneratorHelper.getImportsFor(fields,
                codeGeneratorConfiguration.getClassList()));
        metaVariablesValue.put(new MetaVariable("entityClass"), entityClass);
        metaVariablesValue.put(new MetaVariable("getters"), DtoGeneratorHelper.getGetters(fields,
                codeGeneratorConfiguration.getClassList()));
        metaVariablesValue.put(new MetaVariable("setters"), DtoGeneratorHelper.getSetters(fields,
                codeGeneratorConfiguration.getClassList()));

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(dynaDtoInterfaceTemplate,
                metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String dynaDtoInterfaceText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(dynaDtoInterfaceText, JavaClassFileNameBuilder
                .buildForDTOInterface(entityClass), PathBuilder.buildForDto(entityClass, codeGeneratorConfiguration
                .getDirectoryInfo()));

        return textFile;
    }

    /**
     * {@inheritDoc}
     */
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addDto(textFile.getPath() + textFile.getName());

    }

}
