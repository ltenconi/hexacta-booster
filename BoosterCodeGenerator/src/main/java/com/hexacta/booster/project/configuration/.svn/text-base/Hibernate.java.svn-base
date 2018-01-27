package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.persistence.DotNetDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.OrmGenerator;

/**
 * 
 */
public class Hibernate extends OrmTool {

    public Hibernate() {

    }

    @Override
    public boolean isHibernate() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.project.configuration.OrmTool#generateCodeIn(com.
     * hexacta.booster.codegeneration.persistence.JavaDAOGenerator,
     * com.hexacta.booster.project.configuration.ProjectConfigurationTool,
     * com.hexacta
     * .booster.codegeneration.configuration.CodeGeneratorConfiguration,
     * com.hexacta.booster.codegeneration.GenerationInfo)
     */
    @Override
    public void generateCodeIn(final JavaDAOGenerator javaDAOGenerator,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        projectConfigurationTool.getFramework().generateCodeIn(javaDAOGenerator, this, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.project.configuration.OrmTool#generateTestsCodeIn
     * (com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator,
     * com.hexacta.booster.project.configuration.ProjectConfigurationTool,
     * com.hexacta
     * .booster.codegeneration.configuration.CodeGeneratorConfiguration,
     * com.hexacta.booster.codegeneration.GenerationInfo)
     */
    @Override
    public void generateTestsCodeIn(final JavaDAOGenerator javaDAOGenerator,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        projectConfigurationTool.getFramework().generateTestCodeIn(javaDAOGenerator, this, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

    @Override
    public void generateCodeIn(final OrmGenerator ormGenerator,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        ormGenerator.generateCodeFor(this, projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

    }

    @Override
    public void generateCodeForDaosIn(final DotNetDAOGenerator dotNetDAOGenerator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

    }

    @Override
    public void generateDaosTestsIn(final DotNetDAOGenerator dotNetDAOGenerator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

    }

}
