/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.view;

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
import com.hexacta.booster.utilities.PathBuilder;
import com.hexacta.booster.utilities.StringUtils;
import com.hexacta.booster.utilities.VarNameBuilder;

/**
 * Given an entity this class generates the text in spanish for the
 * ApplicationResources in the view layer
 * 
 * Created on 05/06/2009
 * 
 * @author vmartz
 * 
 */
public class ApplicationResourcesSpanishGenerator implements TextFileGenerator {
    /**
     * {@inheritDoc}
     */
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addModifiedFile(textFile.getPath() + textFile.getName());

    }

    /**
     * @return
     */
    public TextFile generate(final Class entityClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        Class persistableClass = codeGeneratorConfiguration.getPersistableMetaModel().getClass(entityClass.getName());

        MetaTemplate appResourcesTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("APPLICATION_RESOURCES_ES"));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        metaVariablesValue.put(new MetaVariable("fields"), ViewUtils.getInfoFields(persistableClass,
                codeGeneratorConfiguration));
        metaVariablesValue.put(new MetaVariable("Entity"), persistableClass.getSimpleName());
        metaVariablesValue.put(new MetaVariable("entity"), VarNameBuilder.buildForEntity(persistableClass));
        metaVariablesValue.put(new MetaVariable("Entities"), StringUtils.getStringPlural(persistableClass
                .getSimpleName()));
        metaVariablesValue.put(new MetaVariable("entities"), StringUtils.getStringPlural(VarNameBuilder
                .buildForEntity(persistableClass)));

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(appResourcesTemplate,
                metaVariablesValue);

        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String appResourcesText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(appResourcesText, "ApplicationResources_es.properties", PathBuilder
                .buildForViewResources(codeGeneratorConfiguration.getDirectoryInfo()));

        return textFile;
    }

}
