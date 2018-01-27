package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.persistence.DotNetDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.OrmGenerator;

/**
 * 
 * ORMTOOL.
 * 
 * Created on 26/01/2009
 * 
 * @author clopez
 * 
 */
public abstract class OrmTool {

    public boolean isTopLink() {
        return false;
    }

    public boolean isHibernate() {
        return false;
    }

    public boolean isNoOrmTool() {
        return false;
    }

    public boolean isNHibernate() {
        return false;
    }

    public boolean isJpaHibernate() {
        return false;
    }

    public abstract void generateCodeIn(OrmGenerator ormGenerator, ProjectConfigurationTool projectConfigurationTool,
            CodeGeneratorConfiguration codeGeneratorConfiguration, GenerationInfo generationInfo)
            throws NotSupportedClassException;

    public abstract void generateCodeForDaosIn(DotNetDAOGenerator dotNetDAOGenerator,
            CodeGeneratorConfiguration codeGeneratorConfiguration, GenerationInfo generationInfo);

    public abstract void generateDaosTestsIn(DotNetDAOGenerator dotNetDAOGenerator,
            CodeGeneratorConfiguration codeGeneratorConfiguration, GenerationInfo generationInfo);

    /**
     * @param javaDAOGenerator
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public abstract void generateCodeIn(JavaDAOGenerator javaDAOGenerator,
            ProjectConfigurationTool projectConfigurationTool, CodeGeneratorConfiguration codeGeneratorConfiguration,
            GenerationInfo generationInfo);

    /**
     * @param javaDAOGenerator
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public abstract void generateTestsCodeIn(JavaDAOGenerator javaDAOGenerator,
            ProjectConfigurationTool projectConfigurationTool, CodeGeneratorConfiguration codeGeneratorConfiguration,
            GenerationInfo generationInfo);

}
