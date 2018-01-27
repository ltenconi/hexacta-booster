package com.hexacta.booster.codegeneration.service;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.BoosterGenerator;
import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.service.spring.ServiceImplementationGenerator;
import com.hexacta.booster.codegeneration.service.spring.ServiceInterfaceGenerator;
import com.hexacta.booster.project.configuration.DynaDto;
import com.hexacta.booster.project.configuration.NoDtoTool;
import com.hexacta.booster.project.configuration.NoFramework;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;
import com.hexacta.booster.project.configuration.Spring;
import com.hexacta.booster.utilities.CodeGeneratorHelper;
import com.hexacta.booster.utilities.SpringUtils;

/**
 * 
 */
public class ServiceGenerator implements BoosterGenerator {

    static final Logger logger = Logger.getLogger(ServiceGenerator.class.getSimpleName());

    public ServiceGenerator() {
    }

    public void generate(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        projectConfigurationTool.getFramework().generateCodeForServiceIn(this, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

    public void generateFor(final Spring spring, final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        projectConfigurationTool.getDtoTool().generateCodeForServiceIn(this, spring, codeGeneratorConfiguration,
                generationInfo);

    }

    public void generateFor(final DynaDto dynaDto, final Spring spring,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        generateInterfacesFor(dynaDto, spring, codeGeneratorConfiguration, generationInfo);
        generateInterfacesImplementationsFor(dynaDto, spring, codeGeneratorConfiguration, generationInfo);
        // generateInterfacesImplementationsMockFor(dynaDto, spring,
        // codeGeneratorConfiguration, generationInfo);

    }

    @SuppressWarnings("unused")
    public void generateFor(final NoFramework noFramework, final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

    }

    @SuppressWarnings("unused")
    public void generateFor(final NoDtoTool noDtoTool, final Spring spring,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

    }

    @SuppressWarnings("unused")
    private void generateInterfacesFor(final DynaDto dynaDto, final Spring spring,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        ServiceInterfaceGenerator serviceInterfaceGenerator = new ServiceInterfaceGenerator();
        CodeGeneratorHelper.generateForMetaModel(serviceInterfaceGenerator, codeGeneratorConfiguration, generationInfo);
    }

    @SuppressWarnings("unused")
    private void generateInterfacesImplementationsFor(final DynaDto dynaDto, final Spring spring,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        ServiceImplementationGenerator serviceInterfaceImplGenerator = new ServiceImplementationGenerator();
        CodeGeneratorHelper.generateForMetaModel(serviceInterfaceImplGenerator, codeGeneratorConfiguration,
                generationInfo);

        SpringUtils.addBeansOnServiceContextXML(codeGeneratorConfiguration.getGenerateCodeFor(),
                codeGeneratorConfiguration.getDirectoryInfo(), generationInfo);

    }

    // private void generateInterfacesImplementationsMockFor(final DynaDto
    // dynaDto, final Spring spring,
    // final CodeGeneratorConfiguration codeGeneratorConfiguration, final
    // GenerationInfo generationInfo) {
    //
    // ServiceMockImplementationGenerator interfaceImplMockGenerator = new
    // ServiceMockImplementationGenerator();
    // CodeGeneratorHelper
    // .generateForMetaModel(interfaceImplMockGenerator,
    // codeGeneratorConfiguration, generationInfo);
    //
    // }

}
