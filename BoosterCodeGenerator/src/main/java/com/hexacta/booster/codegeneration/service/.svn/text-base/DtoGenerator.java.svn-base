package com.hexacta.booster.codegeneration.service;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.project.configuration.DynaDto;
import com.hexacta.booster.project.configuration.NoDtoTool;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;
import com.hexacta.booster.utilities.CodeGeneratorHelper;

/**
 * 
 */
public class DtoGenerator {

    static final Logger logger = Logger.getLogger(DtoGenerator.class);

    public DtoGenerator() {

    }

    public void generate(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        projectConfigurationTool.getDtoTool().generateIn(this, codeGeneratorConfiguration, generationInfo);

    }

    public void generateFor(@SuppressWarnings("unused") final DynaDto dynaDto,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        DynaDtoInterfaceGenerator dynaDtoInterfaceGenerator = new DynaDtoInterfaceGenerator();
        CodeGeneratorHelper.generateForMetaModel(dynaDtoInterfaceGenerator, codeGeneratorConfiguration, generationInfo);

    }

    @SuppressWarnings("unused")
    public void generateFor(final NoDtoTool noDtoTool, final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

    }

}
