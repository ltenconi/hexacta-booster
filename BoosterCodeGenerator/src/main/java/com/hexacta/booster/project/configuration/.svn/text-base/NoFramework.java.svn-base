package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator;
import com.hexacta.booster.codegeneration.service.ServiceGenerator;
import com.hexacta.booster.codegeneration.service.ServiceTestGenerator;

/**
 * 
 */
public class NoFramework extends Framework {

    public NoFramework() {

    }

    @Override
    public boolean isSpring() {
        return false;
    }

    @Override
    public void generateCodeForServiceIn(final ServiceGenerator serviceGenerator,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        serviceGenerator.generateFor(this, projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.project.configuration.Framework#generateCodeIn(com
     * .hexacta.booster.codegeneration.persistence.JavaDAOGenerator,
     * com.hexacta.booster.project.configuration.Hibernate,
     * com.hexacta.booster.project.configuration.ProjectConfigurationTool,
     * com.hexacta
     * .booster.codegeneration.configuration.CodeGeneratorConfiguration,
     * com.hexacta.booster.codegeneration.GenerationInfo)
     */
    @Override
    public void generateCodeIn(final JavaDAOGenerator javaDAOGenerator, final Hibernate hibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        javaDAOGenerator.generateCodeFor(this, hibernate, projectConfigurationTool, codeGeneratorConfiguration,
                generationInfo);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.project.configuration.Framework#generateCodeIn(com
     * .hexacta.booster.codegeneration.persistence.JavaDAOGenerator,
     * com.hexacta.booster.project.configuration.JpaHibernate,
     * com.hexacta.booster.project.configuration.ProjectConfigurationTool,
     * com.hexacta
     * .booster.codegeneration.configuration.CodeGeneratorConfiguration,
     * com.hexacta.booster.codegeneration.GenerationInfo)
     */
    @Override
    public void generateCodeIn(final JavaDAOGenerator javaDAOGenerator, final JpaHibernate jpaHibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        javaDAOGenerator.generateCodeFor(this, jpaHibernate, projectConfigurationTool, codeGeneratorConfiguration,
                generationInfo);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.project.configuration.Framework#generateTestCodeIn
     * (com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator,
     * com.hexacta.booster.project.configuration.Hibernate,
     * com.hexacta.booster.project.configuration.ProjectConfigurationTool,
     * com.hexacta
     * .booster.codegeneration.configuration.CodeGeneratorConfiguration,
     * com.hexacta.booster.codegeneration.GenerationInfo)
     */
    @Override
    public void generateTestCodeIn(final JavaDAOGenerator javaDAOGenerator, final Hibernate hibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        projectConfigurationTool.getTestingTool().generateTestCodeIn(javaDAOGenerator, this, hibernate,
                projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.project.configuration.Framework#generateTestCodeIn
     * (com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator,
     * com.hexacta.booster.project.configuration.JpaHibernate,
     * com.hexacta.booster.project.configuration.ProjectConfigurationTool,
     * com.hexacta
     * .booster.codegeneration.configuration.CodeGeneratorConfiguration,
     * com.hexacta.booster.codegeneration.GenerationInfo)
     */
    @Override
    public void generateTestCodeIn(final JavaDAOGenerator javaDAOGenerator, final JpaHibernate jpaHibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        projectConfigurationTool.getTestingTool().generateTestCodeIn(javaDAOGenerator, this, jpaHibernate,
                projectConfigurationTool, codeGeneratorConfiguration, generationInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateCodeIn(final ServiceTestGenerator serviceTestGenerator,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

    }

}
