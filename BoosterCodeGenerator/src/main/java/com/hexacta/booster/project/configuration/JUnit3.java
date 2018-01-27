/*
 * [legal notice here]
 */
package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator;
import com.hexacta.booster.codegeneration.service.ServiceTestGenerator;

/**
 * 
 * 
 * Created on 13/02/2009
 * 
 * @author fmrodriguez
 * 
 */
public class JUnit3 extends TestingTool {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.project.configuration.TestingTool#generateTestCodeIn
     * (com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator,
     * com.hexacta.booster.project.configuration.NoFramework,
     * com.hexacta.booster.project.configuration.Hibernate,
     * com.hexacta.booster.project.configuration.ProjectConfigurationTool,
     * com.hexacta
     * .booster.codegeneration.configuration.CodeGeneratorConfiguration,
     * com.hexacta.booster.codegeneration.GenerationInfo)
     */
    @Override
    public void generateTestCodeIn(final JavaDAOGenerator javaDAOGenerator, final NoFramework noFramework,
            final Hibernate hibernate, final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        javaDAOGenerator.generateTestCodeFor(this, noFramework, hibernate, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.project.configuration.TestingTool#generateTestCodeIn
     * (com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator,
     * com.hexacta.booster.project.configuration.NoFramework,
     * com.hexacta.booster.project.configuration.JpaHibernate,
     * com.hexacta.booster.project.configuration.ProjectConfigurationTool,
     * com.hexacta
     * .booster.codegeneration.configuration.CodeGeneratorConfiguration,
     * com.hexacta.booster.codegeneration.GenerationInfo)
     */
    @Override
    public void generateTestCodeIn(final JavaDAOGenerator javaDAOGenerator, final NoFramework noFramework,
            final JpaHibernate jpaHibernate, final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        javaDAOGenerator.generateTestCodeFor(this, noFramework, jpaHibernate, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.project.configuration.TestingTool#generateTestCodeIn
     * (com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator,
     * com.hexacta.booster.project.configuration.Spring,
     * com.hexacta.booster.project.configuration.Hibernate,
     * com.hexacta.booster.project.configuration.ProjectConfigurationTool,
     * com.hexacta
     * .booster.codegeneration.configuration.CodeGeneratorConfiguration,
     * com.hexacta.booster.codegeneration.GenerationInfo)
     */
    @Override
    public void generateTestCodeIn(final JavaDAOGenerator javaDAOGenerator, final Spring spring,
            final Hibernate hibernate, final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        javaDAOGenerator.generateTestCodeFor(this, spring, hibernate, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.project.configuration.TestingTool#generateTestCodeIn
     * (com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator,
     * com.hexacta.booster.project.configuration.Spring,
     * com.hexacta.booster.project.configuration.JpaHibernate,
     * com.hexacta.booster.project.configuration.ProjectConfigurationTool,
     * com.hexacta
     * .booster.codegeneration.configuration.CodeGeneratorConfiguration,
     * com.hexacta.booster.codegeneration.GenerationInfo)
     */
    @Override
    public void generateTestCodeIn(final JavaDAOGenerator javaDAOGenerator, final Spring spring,
            final JpaHibernate jpaHibernate, final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        javaDAOGenerator.generateTestCodeFor(this, spring, jpaHibernate, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateCodeIn(final ServiceTestGenerator serviceTestGenerator, final Spring spring,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        serviceTestGenerator.generateCodeFor(this, spring, projectConfigurationTool, codeGeneratorConfiguration,
                generationInfo);

    }

}
