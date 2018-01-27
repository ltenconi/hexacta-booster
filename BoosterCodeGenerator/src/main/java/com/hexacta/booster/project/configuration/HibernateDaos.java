package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.persistence.PersistenceLayerGenerator;

/**
 * 
 */
public class HibernateDaos extends PersistenceLayerType {

    public HibernateDaos() {
    }

    @Override
    public void generateCodeIn(final PersistenceLayerGenerator persistenceLayersGenerator,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {
        persistenceLayersGenerator.generateCodeFor(this, projectConfigurationTool, codeGeneratorConfiguration,
                generationInfo);

    }

}
