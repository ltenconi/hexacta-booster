/*
 * [legal notice here]
 */
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
import com.hexacta.booster.utilities.PathBuilder;
import com.hexacta.booster.utilities.VarNameBuilder;

/**
 * Given an entity this class generates the text for the action-validation in
 * the view layer.
 * 
 * Created on 14/04/2009
 * 
 * @author vmartz
 * 
 */
public class Struts2ActionValidationGenerator implements TextFileGenerator {

    /**
     * {@inheritDoc}
     */
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addViews(textFile.getPath() + textFile.getName());

    }

    /**
     * {@inheritDoc}
     */
    public TextFile generate(final Class entityClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        Class persistableClass = codeGeneratorConfiguration.getPersistableMetaModel().getClass(entityClass.getName());

        MetaTemplate actionValidationTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("ACTION_VALIDATION"));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        metaVariablesValue.put(new MetaVariable("entity"), VarNameBuilder.buildForEntity(persistableClass));

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(actionValidationTemplate,
                metaVariablesValue);

        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String actionValidationText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(actionValidationText, JavaClassFileNameBuilder
                .buildForActionValidation(persistableClass), PathBuilder
                .buildForViewActionValidation(codeGeneratorConfiguration.getDirectoryInfo()));

        return textFile;
    }

}
