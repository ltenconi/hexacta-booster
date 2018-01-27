package com.hexacta.booster.codegeneration;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.persistence.OrmGenerator;
import com.hexacta.booster.codegeneration.persistence.PersistenceLayerGenerator;
import com.hexacta.booster.codegeneration.service.DtoGenerator;
import com.hexacta.booster.codegeneration.service.ServiceGenerator;
import com.hexacta.booster.codegeneration.service.ServiceTestGenerator;
import com.hexacta.booster.codegeneration.view.ViewGenerator;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;

/**
 * 
 */
public class CodeGenerator {

    private static final String LOG4J_PROPERTIES = "log4j.properties";

    private GenerationInfo generationInfo;

    public CodeGenerator() {

        URL url = Thread.currentThread().getContextClassLoader().getResource(LOG4J_PROPERTIES);
        PropertyConfigurator.configure(url);
        generationInfo = new GenerationInfo();

    }

    public GenerationInfo generatePersistenceLayer(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration) throws NotSupportedClassException {

        PersistenceLayerGenerator persistenceLayersGenerator = new PersistenceLayerGenerator();
        persistenceLayersGenerator.generate(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

        return generationInfo;
    }

    public GenerationInfo generateDto(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration) {

        DtoGenerator dtoGenerator = new DtoGenerator();
        dtoGenerator.generate(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

        return generationInfo;
    }

    public GenerationInfo generateMapping(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration) throws NotSupportedClassException {

        OrmGenerator ormGenerator = new OrmGenerator();
        ormGenerator.generate(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

        return generationInfo;
    }

    public GenerationInfo generateService(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration) throws NotSupportedClassException {

        generatePersistenceLayer(projectConfigurationTool, codeGeneratorConfiguration);

        DtoGenerator dtoGenerator = new DtoGenerator();
        dtoGenerator.generate(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

        ServiceGenerator serviceGenerator = new ServiceGenerator();
        serviceGenerator.generate(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

        ServiceTestGenerator serviceTestGenerator = new ServiceTestGenerator();
        serviceTestGenerator.generate(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

        return generationInfo;
    }

    public GenerationInfo generateView(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration) throws NotSupportedClassException {

        generateService(projectConfigurationTool, codeGeneratorConfiguration);

        ViewGenerator viewGenerator = new ViewGenerator();
        viewGenerator.generate(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

        return generationInfo;
    }

}
