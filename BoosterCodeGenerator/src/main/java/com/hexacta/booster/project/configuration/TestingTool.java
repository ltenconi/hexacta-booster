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
public abstract class TestingTool {

    /**
     * @param javaDAOGenerator
     * @param noFramework
     * @param hibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public abstract void generateTestCodeIn(JavaDAOGenerator javaDAOGenerator, NoFramework noFramework,
            Hibernate hibernate, ProjectConfigurationTool projectConfigurationTool,
            CodeGeneratorConfiguration codeGeneratorConfiguration, GenerationInfo generationInfo);

    /**
     * @param javaDAOGenerator
     * @param noFramework
     * @param jpaHibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public abstract void generateTestCodeIn(JavaDAOGenerator javaDAOGenerator, NoFramework noFramework,
            JpaHibernate jpaHibernate, ProjectConfigurationTool projectConfigurationTool,
            CodeGeneratorConfiguration codeGeneratorConfiguration, GenerationInfo generationInfo);

    /**
     * @param javaDAOGenerator
     * @param spring
     * @param hibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    abstract void generateTestCodeIn(JavaDAOGenerator javaDAOGenerator, Spring spring, Hibernate hibernate,
            ProjectConfigurationTool projectConfigurationTool, CodeGeneratorConfiguration codeGeneratorConfiguration,
            GenerationInfo generationInfo);

    /**
     * @param javaDAOGenerator
     * @param spring
     * @param jpaHibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    abstract void generateTestCodeIn(JavaDAOGenerator javaDAOGenerator, Spring spring, JpaHibernate jpaHibernate,
            ProjectConfigurationTool projectConfigurationTool, CodeGeneratorConfiguration codeGeneratorConfiguration,
            GenerationInfo generationInfo);

    /**
     * @param serviceTestGenerator
     * @param spring
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public abstract void generateCodeIn(ServiceTestGenerator serviceTestGenerator, Spring spring,
            ProjectConfigurationTool projectConfigurationTool, CodeGeneratorConfiguration codeGeneratorConfiguration,
            GenerationInfo generationInfo);

}
