package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.DotNetDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.JavaDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.OrmGenerator;

/**
 * 
 */
public class NoOrmTool extends OrmTool {

    public NoOrmTool() {

    }

    @Override
    public boolean isNoOrmTool() {
        return true;
    }

    @Override
    public void generateCodeIn(final OrmGenerator ormGenerator,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        ormGenerator.generateCodeFor(this, codeGeneratorConfiguration, generationInfo);

    }

    @Override
    public void generateCodeForDaosIn(final DotNetDAOGenerator dotNetDAOGenerator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

    }

    @Override
    public void generateDaosTestsIn(final DotNetDAOGenerator dotNetDAOGenerator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

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

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.project.configuration.OrmTool#generateTestsIn(com
     * .hexacta.booster.codegeneration.persistence.JavaDAOGenerator,
     * com.hexacta.booster.project.configuration.ProjectConfigurationTool,
     * com.hexacta
     * .booster.codegeneration.configuration.CodeGeneratorConfiguration,
     * com.hexacta.booster.codegeneration.GenerationInfo)
     */
    @Override
    public void generateTestsCodeIn(final JavaDAOGenerator javaDAOGenerator,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

    }

}
