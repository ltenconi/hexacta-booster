package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.persistence.PersistenceLayerGenerator;

/**
 * 
 */
public abstract class ProjectType {

    public boolean isDotNet() {
        return false;
    }

    public boolean isJava() {
        return false;
    }

    public abstract void processIn(PersistenceLayerGenerator persistenceLayersGenerator, HibernateDaos hibernateDaos,
            ProjectConfigurationTool projectConfigurationTool, CodeGeneratorConfiguration codeGeneratorConfiguration,
            GenerationInfo generationInfo) throws NotSupportedClassException;
}
