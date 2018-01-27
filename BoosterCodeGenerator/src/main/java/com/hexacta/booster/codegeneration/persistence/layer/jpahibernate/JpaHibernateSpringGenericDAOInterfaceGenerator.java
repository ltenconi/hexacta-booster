/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.persistence.layer.jpahibernate;

import java.util.HashMap;
import java.util.Map;

import com.hexacta.booster.codegeneration.ArchetypeTextFileGenerator;
import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TemplatesNames;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.template.IMetaTemplateEngine;
import com.hexacta.booster.codegeneration.template.MetaTemplateGeneration;
import com.hexacta.booster.codegeneration.template.MetaTemplateSystem;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.utilities.PathBuilder;

/**
 * Created on 09/06/2009.
 * 
 * @author fmrodriguez
 * 
 */
public class JpaHibernateSpringGenericDAOInterfaceGenerator implements ArchetypeTextFileGenerator {

    /**
     * 
     */
    private static final String JPA_GENERIC_DAO_JAVA = "JpaGenericDAO.java";

    public TextFile generate(final String groupId, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        MetaTemplate daoTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("JPA_HIBERNATE_SPRING_GENERIC_DAO"));
        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();

        metaVariablesValue.put(new MetaVariable("projectGroupId"), groupId);

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(daoTemplate, metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String genericDaoTest = metaTemplateEngine.merge(metaTemplateGeneration);

        String name = JPA_GENERIC_DAO_JAVA;

        String path = PathBuilder.buildForJPAHibernateSpringGenericDaoImpl(groupId, codeGeneratorConfiguration
                .getDirectoryInfo());

        return new TextFile(genericDaoTest, name, path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addDao(textFile.getPath() + textFile.getName());

    }

}
