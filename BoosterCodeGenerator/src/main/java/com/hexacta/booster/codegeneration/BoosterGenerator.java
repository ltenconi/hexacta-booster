package com.hexacta.booster.codegeneration;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;

/**
 * 
 */
public interface BoosterGenerator {

    void generate(ProjectConfigurationTool projectConfigurationTool,
            CodeGeneratorConfiguration codeGeneratorConfiguration, GenerationInfo generationInfo)
            throws NotSupportedClassException;

}
