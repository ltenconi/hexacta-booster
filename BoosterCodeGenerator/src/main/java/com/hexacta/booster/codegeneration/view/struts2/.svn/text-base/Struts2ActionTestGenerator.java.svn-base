package com.hexacta.booster.codegeneration.view.struts2;

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
import com.hexacta.booster.utilities.MetaModelUtils;
import com.hexacta.booster.utilities.PackageNameBuilder;
import com.hexacta.booster.utilities.PathBuilder;
import com.hexacta.booster.utilities.VarNameBuilder;

public class Struts2ActionTestGenerator implements TextFileGenerator {

    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {

        generationInfo.addViews(textFile.getPath() + textFile.getName());

    }

    public TextFile generate(final Class entityClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        MetaTemplate serviceInterfaceTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("ACTION_TEST"));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        String groupId = MetaModelUtils.getGroupId(entityClass);
        metaVariablesValue.put(new MetaVariable("groupId"), groupId);
        metaVariablesValue.put(new MetaVariable("entityClassName"), entityClass.getSimpleName());
        metaVariablesValue
                .put(new MetaVariable("entityClassNameLowerCase"), VarNameBuilder.buildForEntity(entityClass));
        metaVariablesValue.put(new MetaVariable("intefaceServicePackage"), PackageNameBuilder
                .buildForServiceInterface(entityClass));
        metaVariablesValue.put(new MetaVariable("interfaceServiceClassName"), JavaClassNameBuilder
                .buildForServiceInterface(entityClass));
        metaVariablesValue.put(new MetaVariable("intefaceDtoPackage"), PackageNameBuilder.buildForDTO(entityClass));
        metaVariablesValue.put(new MetaVariable("interfaceDtoClass"), JavaClassNameBuilder
                .buildForDTOInterface(entityClass));

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(serviceInterfaceTemplate,
                metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String serviceInterfaceText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(serviceInterfaceText,
                JavaClassFileNameBuilder.buildForActionTest(entityClass), PathBuilder.buildforActionTest(groupId,
                        codeGeneratorConfiguration.getDirectoryInfo()));

        return textFile;
    }

}
