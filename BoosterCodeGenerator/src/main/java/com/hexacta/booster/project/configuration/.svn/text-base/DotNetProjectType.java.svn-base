package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.persistence.PersistenceLayerGenerator;

/**
 * 
 */
public class DotNetProjectType extends ProjectType {

    public DotNetProjectType() {

    }

    @Override
    public boolean isDotNet() {
        return true;
    }

    @Override
    public void processIn(final PersistenceLayerGenerator persistenceLayersGenerator,
            final HibernateDaos hibernateDaos, final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        persistenceLayersGenerator.generateCodeFor(hibernateDaos, this, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

}
