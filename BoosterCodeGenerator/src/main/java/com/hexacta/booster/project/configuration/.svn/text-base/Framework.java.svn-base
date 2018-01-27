package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator;
import com.hexacta.booster.codegeneration.service.ServiceGenerator;
import com.hexacta.booster.codegeneration.service.ServiceTestGenerator;

/**
 * 
 */
public abstract class Framework {

    public boolean isSpring() {
        return false;
    }

    public abstract void generateCodeForServiceIn(ServiceGenerator serviceGenerator,
            ProjectConfigurationTool projectConfigurationTool, CodeGeneratorConfiguration codeGeneratorConfiguration,
            GenerationInfo generationInfo);

    /**
     * @param javaDAOGenerator
     * @param hibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public abstract void generateCodeIn(final JavaDAOGenerator javaDAOGenerator, final Hibernate hibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo);

    /**
     * @param javaDAOGenerator
     * @param jpaHibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public abstract void generateCodeIn(final JavaDAOGenerator javaDAOGenerator, final JpaHibernate jpaHibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo);

    /**
     * @param javaDAOGenerator
     * @param hibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public abstract void generateTestCodeIn(JavaDAOGenerator javaDAOGenerator, Hibernate hibernate,
            ProjectConfigurationTool projectConfigurationTool, CodeGeneratorConfiguration codeGeneratorConfiguration,
            GenerationInfo generationInfo);

    /**
     * @param javaDAOGenerator
     * @param jpaHibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public abstract void generateTestCodeIn(JavaDAOGenerator javaDAOGenerator, JpaHibernate jpaHibernate,
            ProjectConfigurationTool projectConfigurationTool, CodeGeneratorConfiguration codeGeneratorConfiguration,
            GenerationInfo generationInfo);

    /**
     * @param serviceTestGenerator
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public abstract void generateCodeIn(final ServiceTestGenerator serviceTestGenerator,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo);

}
