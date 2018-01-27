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
import com.hexacta.booster.codegeneration.view.ViewUtils;
import com.hexacta.booster.utilities.PathBuilder;
import com.hexacta.booster.utilities.StringUtils;
import com.hexacta.booster.utilities.VarNameBuilder;

/**
 * This class generates the text to be inserted in struts.xml.
 * 
 * Created on 15/04/2009
 * 
 * @author vmartz
 * 
 */
public class Struts2StrutsFileGenerator implements TextFileGenerator {

    /**
     * {@inheritDoc}
     */
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addModifiedFile(textFile.getPath() + textFile.getName());

    }

    /**
     * {@inheritDoc}
     */
    public TextFile generate(final Class entityClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {
        Class persistableClass = codeGeneratorConfiguration.getPersistableMetaModel().getClass(entityClass.getName());

        MetaTemplate strutsTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames.getName("STRUTS"));

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        metaVariablesValue.put(new MetaVariable("Entity"), persistableClass.getSimpleName());
        metaVariablesValue.put(new MetaVariable("entity"), VarNameBuilder.buildForEntity(persistableClass));
        metaVariablesValue.put(new MetaVariable("entities"), StringUtils.getStringPlural(VarNameBuilder
                .buildForEntity(persistableClass)));

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(strutsTemplate, metaVariablesValue);

        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String strutsText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(ViewUtils.buildXML(strutsText), "struts.xml", PathBuilder
                .buildForViewResources(codeGeneratorConfiguration.getDirectoryInfo()));

        return textFile;
    }

}
